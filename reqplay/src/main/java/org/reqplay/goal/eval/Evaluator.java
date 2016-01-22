package org.reqplay.goal.eval;

import org.reqplay.goal.eval.analize.Analizer;
import org.reqplay.goal.eval.estimative.Estimator;
import org.reqplay.goal.eval.rate.Rater;
import org.reqplay.goal.eval.trace.Tracer;
import org.reqplay.goal.eval.validation.ReqValidator;
import org.reqplay.model.ReqContext;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 02/07/2013
 * 
 */
public interface Evaluator {

    /**
     * Evaluates the ReqItems and set tha rating, size and complexity atributes.
     * 
     * @param context
     *            The ReqContext.
     */
	public void eval(ReqContext context, Tracer tracer, Estimator estimator, Analizer analizer, Rater rater, ReqValidator validator);
}
