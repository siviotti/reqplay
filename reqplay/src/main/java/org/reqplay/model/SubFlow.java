package org.reqplay.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This flow can be called from any step(s).
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 09/06/2013
 * 
 */
public class SubFlow extends Flow {

	private String title;
	private List<Step> steps;

	public SubFlow(String id, String title, List<Step> steps) {
		super(id);
		this.title = title;
		this.steps = new ArrayList<Step>(steps);
		for (Step step: steps){
            step.add(link());
        }
	}

	public List<Step> getSteps() {
		return steps;
	}

	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
