package org.reqplay.model.element;

import org.reqplay.model.ReqItem;
import org.reqplay.model.ReqType;

/**
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 13/07/2013
 * 
 */
public class NonFunctional extends ReqItem{

	public NonFunctional() {
		super();
		setType(ReqType.NONFUNCTIONAL.name);
	}

	
}
