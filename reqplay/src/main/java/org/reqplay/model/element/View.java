package org.reqplay.model.element;

import org.reqplay.model.ReqItem;
import org.reqplay.model.ReqType;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 08/06/2013
 * 
 */
public class View extends ReqItem {

    public View() {
        super();
        setType(ReqType.VIEW.name);
    }

}
