package org.reqplay.model;

import java.util.HashMap;
import java.util.Map;

import org.reqplay.ReqPlayException;
import org.reqplay.model.element.Message;

/**
 * The messages present on Context. The key attribute is '
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 16/07/2013
 * 
 */
public class ContextMessages {

    private Map<String, Message> messages = new HashMap<String, Message>();

    public ContextMessages put(Message message) {
        if (messages.containsKey(message.getDescription())) {
            throw new ReqPlayException("Duplicated message:"
                    + message.longLink() + " = " + message.getDescription());
        }
        messages.put(message.getDescription(), message);
        return this;
    }
    
    public Message get(String description){
        return messages.get(description);
    }
    
    public Map<String, Message> getMessages(){
        return messages;
    }
    
    public boolean contains(String description){
        return messages.containsKey(description);
    }

}
