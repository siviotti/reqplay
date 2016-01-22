package org.reqplay.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The set of Steps.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 07/06/2013
 * 
 */
public class Flow {

    private String id;
    private String prefix;
    private List<Step> steps = new ArrayList<Step>();
    
    public Flow(String id) {
        super();
        this.id = id;
    }
    
    public String link(){
        return "[".concat(id).concat("]");
    }
    
    public Step add(Object... objects) {
    	return add(createId(), objects);
    }
    private String createId() {
    	String stepId = "" + nextIndex();
    	if (prefix != null){
    		stepId = prefix + stepId;
    	}
		return stepId;
	}
	public Step add(String stepId, Object... objects) {
        Step step;
        if (stepId == null) {
            step = new Step(createId(), objects);
        } else {
            step = new Step(stepId, objects);
        }
        step.setIndex(nextIndex());
        steps.add(step);
        return step;
    }

    public Step get(String id){
    	for (Step step: steps){
    		if (step.getId().equals(id)){
    			return step;
    		}
    	}
    	return null;
    }
    public String getId() {
        return id;
    }
    
    public int nextIndex(){
        return steps.size()+1;
    }
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public Step get(int index) {
		return steps.get(index);
	}
	
	public boolean isEmpty(){
	    return steps.isEmpty();
	}
    public List<Step> getSteps() {
        return steps;
    }

    public String astext() {
        StringBuilder sb = new StringBuilder();
        for (Step step: steps){
            sb.append("\n");
            sb.append(step.asText());
        }
        return sb.toString();
    }

    public void afterPlay(ReqContext context) {
        for (Step step: steps){
            step.afterPlay(context);
        }
    }

}
