package org.reqplay.goal.eval.validation;
import org.reqplay.model.ReqContext;
import org.reqplay.model.ReqItem;
import org.reqplay.model.element.scenario.Scenario;
/**
 * Default Validator for the goal Validate.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 13/07/2013
 * 
 */
public class DefaultValidator implements ReqValidator {

    public void validate(ReqContext context) {
        for (ReqItem reqItem: context.getItems()){
            validate(reqItem);
        }
    }
    private void validate(ReqItem reqItem) {
        if (reqItem instanceof Scenario){
            
        }
    }

}
