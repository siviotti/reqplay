package org.reqplay.model.element.scenario;

import org.reqplay.ReqPlayException;
import org.reqplay.model.ExceptionFlow;
import org.reqplay.model.Step;
import org.reqplay.model.StepAPI;
import org.reqplay.model.element.Actor;

/**
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 09/06/2013
 * 
 */
public abstract class UseScenario extends Scenario {

    public UseScenario() {
        super();
        however();
    }

    public abstract void however();

    @Override
    public String asText() {
        StringBuilder sb = new StringBuilder(super.asText());
        if (!actors.isEmpty()) {
            sb.append("\n  As ");
            for (Actor actor : actors) {
                sb.append(actor.getName());
                sb.append(" or ");
            }
            sb.delete(sb.length() - 4, sb.length() );
        }
        if (goal != null){
            sb.append("\n  I want ").append(goal).append(" then");
        }
        sb.append(mainFlow.astext());
        if (!exceptionFlows.isEmpty()){
            sb.append("\n  However");
            for (ExceptionFlow flow: exceptionFlows){
                sb.append(flow.asHowever());
            }
        }
        return sb.toString();
    }

    public Scenario iWant(String goal) {
        if (getGoal() != null) {
            throw new ReqPlayException("Goal already defined.");
        }
        setGoal(goal);
        return this;
    }

    public Scenario IF(Scenario scenario, Object... objects) {
        return exception(null, null, this, objects);
    }

    public Scenario IF(Object... condition) {
        IF(null, condition);
        return this;
    }

    public StepAPI then(Object... objects) {
        currentFlow = currentExceptionFlow;
        if (currentFlow == null) {
            throw new ReqPlayException(NONE_EXCEPTION_FLOW);
        }
        return step(null, objects);
    }

    public StepAPI and(Object... objects) {
        currentFlow = currentExceptionFlow;
        if (currentFlow == null) {
            throw new ReqPlayException(NONE_EXCEPTION_FLOW);
        }
        if (currentFlow.isEmpty()) {
            throw new ReqPlayException(EXCEPTION_FLOW_IS_EMPTY);
        }
        return step(null, objects);
    }

}
