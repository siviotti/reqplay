package org.reqplay.model.element.scenario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.reqplay.Executable;
import org.reqplay.ReqPlayException;
import org.reqplay.annotation.element.ScenarioSpec;
import org.reqplay.model.AlternativeFlow;
import org.reqplay.model.Condition;
import org.reqplay.model.ContainerItem;
import org.reqplay.model.ExceptionFlow;
import org.reqplay.model.ExtensionPoint;
import org.reqplay.model.Flow;
import org.reqplay.model.ReqContext;
import org.reqplay.model.ReqItem;
import org.reqplay.model.ReqType;
import org.reqplay.model.Step;
import org.reqplay.model.StepAPI;
import org.reqplay.model.element.Actor;
import org.reqplay.model.element.Feature;
import org.reqplay.text.Text;

/**
 * Represents the Use Cases and Scenarios in the system.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 07/06/2013
 * 
 */
public abstract class Scenario extends ContainerItem implements Executable {

    static final String EXTENDED_SCENARIO_ALREADY_DEFINED = "Extended Scenario already defined";
    static final String NONE_EXCEPTION_FLOW = "None Exception Flow created. Call IF()";
    static final String EXCEPTION_FLOW_IS_EMPTY = "Exception Flow is Empty. Call then() before";

    // Before Play
    Class<?> actorClass;
    Set<Class<?>> actorClasses = new HashSet<Class<?>>();
    Class<?> extendedClass;
    List<Object> obs = new ArrayList<Object>();

    // After Play
    Feature feature;
    Actor primaryActor;
    Set<Actor> actors = new HashSet<Actor>();
    Scenario extended;
    Text obsText;

    // String Attributes
    String preCondition;
    String postCondition;
    String goal;
    String benefit;
    Flow mainFlow = new Flow("main");
    List<ExceptionFlow> exceptionFlows = new ArrayList<ExceptionFlow>();
    List<AlternativeFlow> alternativeFlows = new ArrayList<AlternativeFlow>();
    List<ExtensionPoint> extensionPoints = new ArrayList<ExtensionPoint>();
    Map<String, Step> steps = new HashMap<String, Step>();

    // Current elements
    Step currentStep;
    Flow currentFlow = mainFlow;
    ExceptionFlow currentExceptionFlow;
    AlternativeFlow currentAlternativeFlow;
    List<Step> selectedSteps = new ArrayList<Step>();

    public Scenario() {
        super();
        setType(ReqType.SCENARIO.name);

        main();
    }

    @Override
    public void afterPlay(ReqContext context) {
        super.afterPlay(context);
        ScenarioSpec spec = getClass().getAnnotation(ScenarioSpec.class);
        if (spec == null) {
            throw new ReqPlayException(
                    "Scenarios must be annotated with @ScenarioSpec: "
                            + getClass().getCanonicalName());
        }
        feature = (Feature) context.getAsFeature(spec.feature());
        if (actorClass != null) {
            primaryActor = (Actor) context.getAsActor(actorClass);
        }
        for (Class<?> c : actorClasses) {
            actors.add((Actor) context.getAsActor(c));
        }
        mainFlow.afterPlay(context);
        if (!alternativeFlows.isEmpty()) {
            for (AlternativeFlow flow : alternativeFlows) {
                flow.afterPlay(context);
            }
        }
        if (!exceptionFlows.isEmpty()) {
            for (ExceptionFlow flow : exceptionFlows) {
                flow.afterPlay(context);
            }
        }
        if (!extensionPoints.isEmpty()){
            for (ExtensionPoint ep: extensionPoints){
                ep.afterPlay(context);
            }
        }
    }

    public boolean isAbstract() {
        return actorClass == null;
    }

    // ******************** Main Flow Methods ********************

    /**
     * Main flow definition.
     * 
     * @return The instance.
     */
    public abstract void main();

    public Scenario soThat(String benefit) {
        if (this.benefit != null) {
            throw new ReqPlayException("Benefit already defined.");
        }
        this.benefit = benefit;
        return this;
    }

    public Scenario as(Class<?> actorClass) {
        this.actorClass = actorClass;
        actorClasses.add(actorClass);
        return this;
    }

    public Scenario orAs(Class<?>... actorClasses) {
        if (actorClass == null) {
            throw new ReqPlayException("Use 'as' before 'orAs'");
        }
        for (Class<?> c : actorClasses) {
            this.actorClasses.add(c);
        }
        return this;
    }

    public Scenario after(Class<?> extendedScenario) {
        if (this.extendedClass != null) {
            throw new ReqPlayException(EXTENDED_SCENARIO_ALREADY_DEFINED);
        }
        this.extendedClass = extendedScenario;
        return this;
    }

    public StepAPI step(String id, Object... objects) {
        currentStep = currentFlow.add(id, objects);
        if (steps.containsKey(id)) {
            throw new ReqPlayException("Step already exists:" + id);
        }
        steps.put(currentStep.getId(), currentStep);
        return new StepAPI(currentStep);
    }

    protected StepAPI step(double id, Object... objects) {
        return step("" + id, objects);
    }

    public StepAPI step(Object... objects) {
        return step(null, objects);
    }

    // ******************** Alternative ********************
    Scenario alternative(String id, String title, Scenario scenario,
            String action) {
        if (id == null) {
            id = "A" + (alternativeFlows.size()+1);
        }
        currentAlternativeFlow = new AlternativeFlow(id, title, selectedSteps,
                action);
        currentFlow = currentAlternativeFlow;
        currentFlow.setPrefix(currentFlow.getId().concat("."));
        alternativeFlows.add(currentAlternativeFlow);
        selectedSteps.clear();
        return this;
    }

    // ******************** Exception ********************

    Scenario exception(String id, String title, Scenario scenario,
            Object... objects) {
        Condition condition = new Condition(objects);
        if (id == null) {
            id = "E" + (exceptionFlows.size()+1);
        }
        currentExceptionFlow = new ExceptionFlow(id, title, selectedSteps,
                condition);
        currentFlow = currentExceptionFlow;
        currentFlow.setPrefix(currentFlow.getId().concat("."));
        exceptionFlows.add(currentExceptionFlow);
        selectedSteps.clear();
        return this;
    }

    // ******************** Extension ********************

    Scenario extension(String id, String title, String action, Class<?> extendScenario) {
        String stepNumber = "";
        if (!selectedSteps.isEmpty()) {
            stepNumber = selectedSteps.get(0).getId();
        }
        ExtensionPoint ep = new ExtensionPoint(id, title, selectedSteps, action, extendScenario);
        extensionPoints.add(ep);
        selectedSteps.clear();
        return this;
    }

    // ******************** Info ********************
    public Scenario obs(Object... obs) {
        for (Object object : obs) {
            this.obs.add(object);
        }
        return this;
    }

    // ******************** Step Methods ********************

    public Scenario at(String id) {
        selectedSteps.clear();
        selectedSteps.add(mainFlow.get(id));
        return this;
    }

    public Scenario orAt(String id) {
        if (selectedSteps.isEmpty()) {
            throw new ReqPlayException("Call at() before orAt()");
        }
        selectedSteps.add(mainFlow.get(id));
        return this;
    }

    public Scenario at(double index) {
        return at(""+index);
    }

    public Scenario orAt(double index) {
        return orAt(""+index);
    }

    // ******************** ETC ********************

    public List<ReqItem> getReferences() {
        List<ReqItem> list = new ArrayList<ReqItem>();
        if (feature != null) {
            list.add(feature);
        }
        if (primaryActor != null) {
            list.add(primaryActor);
        }
        for (Actor actor : actors) {
            list.add(actor);
        }
        if (extended != null) {
            list.add(extended);
        }
        for (Step step : steps.values()) {
            list.addAll(step.getReferences());
        }
        return list;
    }

    // ******************** GET / SET ********************

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    public Actor getPrimaryActor() {
        return primaryActor;
    }

    public void setPrimaryActor(Actor primaryActor) {
        this.primaryActor = primaryActor;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    public String getPreCondition() {
        return preCondition;
    }

    public void setPreCondition(String preCondition) {
        this.preCondition = preCondition;
    }

    public String getPostCondition() {
        return postCondition;
    }

    public void setPostCondition(String postCondition) {
        this.postCondition = postCondition;
    }

    public String getBenefit() {
        return benefit;
    }

    public void setBenefit(String benefit) {
        this.benefit = benefit;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public Map<String, Step> getSteps() {
        return steps;
    }
}
