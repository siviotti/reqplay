package org.reqplay.model;

import java.util.ArrayList;
import java.util.List;

import org.reqplay.model.element.scenario.Scenario;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 17/07/2013
 * 
 */
public class ExtensionPoint {

    private String id;
    private String title;
    private List<Step> steps;
    private String action;
    private Class<?> extendScenarioClass;
    private Scenario extendScenario;

    public ExtensionPoint(String id, String title, List<Step> steps,
            String action, Class<?> extendScenarioClass) {
        super();
        this.id = id;
        this.title = title;
        this.steps = new ArrayList<Step>(steps);
        this.action = action;
        this.extendScenarioClass = extendScenarioClass;
        for (Step step : steps) {
            step.add(link());
        }
    }

    public void afterPlay(ReqContext context) {
        if (extendScenarioClass != null) {
            extendScenario = (Scenario) context.getByClass(extendScenarioClass);
        }
    }

    public String link() {
        return "[".concat(id).concat("]");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public Object asText() {
        String s =  "\n  " + getId() + " " + getTitle() + "\n    At " + steps + " "
                + action;
        if (extendScenario != null){
            s = s + " extends " + extendScenario.link();
        }
        return s;
    }

    public Class<?> getExtendScenarioClass() {
        return extendScenarioClass;
    }

    public void setExtendScenarioClass(Class<?> extendScenarioClass) {
        this.extendScenarioClass = extendScenarioClass;
    }

}
