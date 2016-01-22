package org.reqplay.model;

import org.reqplay.ReqPlayException;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 02/07/2013
 * 
 */
public class StepAPI {

    private Step step;

    public StepAPI(Step step) {
        super();
        this.step = step;
    }

    // API
    public StepAPI view(Class<?> viewClass) {
        if (step.getViewClass() != null) {
            throw new ReqPlayException("View already defined: "
                    + step.getViewClass());
        }
        step.setViewClass(viewClass);
        return this;
    }

    public StepAPI include(Class<?> scenario) {
        if (step.getIncludedClass() != null) {
            throw new ReqPlayException("Included Scenario already defined: "
                    + step.getIncludedClass());
        }
        step.setIncludedClass(scenario);
        return this;
    }
    
    public StepAPI extend (Class<?> scenario){
        
        return this;
    }

    private void validateTransaction() {
        if (step.getTransactionClass() != null) {
            throw new ReqPlayException(
                    "This step already has a transaction defined: "
                            + step.getTransactionClass());
        }
    }

    public StepAPI input(Class<?> form) {
        validateTransaction();
        step.setTransactionClass(form);
        return this;
    }

    public StepAPI output(Class<?> form) {
        validateTransaction();
        step.setTransactionClass(form);
        return this;
    }

    public StepAPI inquire(Class<?> form) {
        validateTransaction();
        step.setTransactionClass(form);
        return this;
    }

    // ******************** Templates ********************

    public StepAPI returnTo(String stepNumber) {
        this.step.add("Return to step ".concat(stepNumber));
        return this;
    }

    public StepAPI returnTo(double stepNumber) {
        returnTo(new Double(stepNumber).toString());
        return this;
    }

}
