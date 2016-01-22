package org.reqplay.model.element;

import org.reqplay.model.ReqItem;
import org.reqplay.model.ReqType;

/**
 * Represents the messages sent to the user.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 07/06/2013
 * 
 */
public class Message extends ReqItem{
	
	
	public static final String INFO = "info";
	public static final String WARN = "warn";
	public static final String ERROR = "error";
	
	private String severity;
	
    public Message() {
        super();
        setType(ReqType.MESSAGE.name);
    }
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
    @Override
    public String asText() {
        return super.asText().concat(" - ").concat(getDescription());
    }

   

}
