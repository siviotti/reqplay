package org.reqplay.model.element;

import org.reqplay.model.ReqItem;
import org.reqplay.model.ReqType;

/**
 * Represents a log event.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 15/07/2013
 * 
 */
public class LogEvent extends ReqItem{

    public LogEvent() {
        super();
        setType(ReqType.LOGEVENT.name);
    }
    
}
