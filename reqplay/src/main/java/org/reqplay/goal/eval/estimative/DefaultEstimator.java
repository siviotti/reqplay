package org.reqplay.goal.eval.estimative;

import org.reqplay.model.ReqContext;
import org.reqplay.model.ReqItem;
import org.reqplay.model.element.scenario.Scenario;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 22/07/2013
 *
 */
public class DefaultEstimator implements Estimator {

    public void estimate(ReqContext context) {
        for (ReqItem reqItem : context.getItems()) {
            if (reqItem instanceof Scenario) {
                estimate((Scenario)reqItem);
            }
        }
    }

    private void estimate(Scenario scenario) {
        int calcSize = scenario.getSize();
        scenario.setCalcSize(calcSize);
    }
}
