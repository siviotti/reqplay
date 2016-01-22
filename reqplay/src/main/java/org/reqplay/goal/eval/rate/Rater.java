package org.reqplay.goal.eval.rate;

import org.reqplay.model.ReqContext;

/**
 * Claculates the rating.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 21/07/2013
 * 
 */
public interface Rater {

	public void rate(ReqContext context);

}
