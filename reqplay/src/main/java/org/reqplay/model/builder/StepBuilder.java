package org.reqplay.model.builder;

import org.reqplay.model.Step;

/**
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 09/06/2013
 * 
 */
public class StepBuilder {
	
	private Step step;

	private StepBuilder() {
		super();

	}

	public Step getStep() {
		return step;
	}

	public void setStep(Step step) {
		this.step = step;
	}

}
