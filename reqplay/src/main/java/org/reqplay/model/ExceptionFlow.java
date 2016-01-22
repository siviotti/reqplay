package org.reqplay.model;

import java.util.List;

/**
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 08/06/2013
 * 
 */
public class ExceptionFlow extends SubFlow {

	private Condition condition;

	public ExceptionFlow(String id, String title, List<Step> steps,
			Condition condition) {
		super(id, title, steps);
		this.condition = condition;

	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}


    public Object asHowever() {
        return "\n    If " + condition.asText() + super.astext();
    }
    
    @Override
    public void afterPlay(ReqContext context) {
        super.afterPlay(context);
        condition.afterPlay(context);
    }

    @Override
    public String astext() {
        return "\n  "+getId() + " " + getTitle() +  super.astext();
    }

}
