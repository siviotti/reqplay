package org.reqplay.model.element.scenario;

import org.reqplay.ReqPlayException;
import org.reqplay.model.AlternativeFlow;
import org.reqplay.model.ExceptionFlow;
import org.reqplay.model.ExtensionPoint;
import org.reqplay.model.element.Actor;

/**
 * Represents a Use Case.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 09/06/2013
 * 
 */
public abstract class UseCase extends Scenario {

    public UseCase() {
        super();
    }
    
    @Override
    public String asText() {
        StringBuilder sb = new StringBuilder(super.asText());
        if (!actors.isEmpty()) {
            sb.append("\n  User ");
            for (Actor actor : actors) {
                sb.append(actor.getName());
                sb.append(" or ");
            }
            sb.delete(sb.length() - 4, sb.length() );
        }
        if (goal != null){
            sb.append("\n  Goal ").append(goal);
        }
        sb.append(mainFlow.astext());
        if (!alternativeFlows.isEmpty()){
            for (AlternativeFlow flow: alternativeFlows){
                sb.append(flow.astext());
            }
        }
        if (!exceptionFlows.isEmpty()){
            for (ExceptionFlow flow: exceptionFlows){
                sb.append(flow.astext());
            }
        }
        if (!extensionPoints.isEmpty()){
            for (ExtensionPoint ep: extensionPoints){
                sb.append(ep.asText());
            }
        }
        return sb.toString();
    }

    public Scenario goal(String goal) {
        if (getGoal() != null) {
            throw new ReqPlayException("Goal already defined.");
        }
        setGoal(goal);
        return this;
    }

    public Scenario preCondition(String preCondition) {
        if (getPreCondition() != null) {
            throw new ReqPlayException("Precondition already defined.");
        }
        setPreCondition(preCondition);
        return this;
    }

    public Scenario postCondition(String postCondition) {
        if (getPostCondition() != null) {
            throw new ReqPlayException("Postcondition already defined.");
        }
        setPostCondition(postCondition);
        return this;
    }

    public Scenario alternative(String id, String title, Scenario scenario,
            String action) {
        return super.alternative(id, title, scenario, action);
    }

    public Scenario exception(String id, String title, Scenario scenario,
            Object... objects) {
        return super.exception(id, title, scenario, objects);
    }

    public Scenario extension(String id, String title, Scenario scenario,
            String action, Class<?> extendScenario) {
        return super.extension(id, title, action, extendScenario);
    }

}
