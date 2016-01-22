package org.reqplay.model;

import java.util.List;

/**
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 08/06/2013
 * 
 */
public class AlternativeFlow extends SubFlow {

    private String action;

    public AlternativeFlow(String id, String title, List<Step> steps,
            String action) {
        super(id, title, steps);
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String astext() {
        return "\n  " + getId() + " " + getTitle() + super.astext();
    }

}
