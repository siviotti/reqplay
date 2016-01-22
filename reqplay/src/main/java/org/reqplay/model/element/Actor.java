package org.reqplay.model.element;

import org.reqplay.model.ReqItem;
import org.reqplay.model.ReqType;


/**
 * Represents the Actor who uses the system. Each actor in the system must have a corresponding class.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 07/06/2013
 * 
 */
public class Actor extends ReqItem{
	
	private String role;
	private String inputs;
	private String representative;

    public Actor() {
        super();
        setType(ReqType.ACTOR.name);
    }
    
    @Override
    public String link() {
        return longLink();
    }
    
    @Override
    public String asText() {
        StringBuilder sb = new StringBuilder(super.asText());
        sb.append(" - role:");
        sb.append(role);
        if (inputs.length()>0){
            sb.append(" inputs:");
            sb.append(inputs);            
        }
        if (representative.length()>0){
            sb.append(" representative:");
            sb.append(representative);            
        }

        return sb.toString();
    }

     
    public Actor representedBy(String representative){
    	this.representative = representative;
    	return this;
    }
    
    public Actor role(String role){
    	this.role = role;
    	return this;
    }
    
    public Actor inputs(String inputs){
    	this.inputs = inputs;
    	return this;
    }


    // ******************** GET / SET ********************

	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getInputs() {
		return inputs;
	}


	public void setInputs(String inputs) {
		this.inputs = inputs;
	}


	public String getRepresentative() {
		return representative;
	}


	public void setRepresentative(String representative) {
		this.representative = representative;
	}


       

}
