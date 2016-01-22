package org.reqplay.goal.eval.analize;

import org.reqplay.model.ReqContext;
import org.reqplay.model.ReqItem;
import org.reqplay.model.element.scenario.Scenario;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 04/07/2013
 * 
 */
public class DefaultAnalizer implements Analizer {

    public void analize(ReqContext context) {
        for (ReqItem reqItem : context.getItems()) {
            if (reqItem instanceof Scenario) {
                analize((Scenario) reqItem);
            }
        }
    }

    private void analize(Scenario scenario) {
        int complexity = scenario.getSize() + scenario.getDependencies().size();
        scenario.setComplexity(complexity);
    }

}
