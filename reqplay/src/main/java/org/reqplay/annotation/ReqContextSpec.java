package org.reqplay.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.reqplay.goal.compile.DefaultCompiler;
import org.reqplay.goal.compile.ReqCompiler;
import org.reqplay.goal.decompile.Decompiler;
import org.reqplay.goal.decompile.DefaultDecompiler;
import org.reqplay.goal.eval.DefaultEvaluator;
import org.reqplay.goal.eval.Evaluator;
import org.reqplay.goal.eval.analize.Analizer;
import org.reqplay.goal.eval.analize.DefaultAnalizer;
import org.reqplay.goal.eval.estimative.DefaultEstimator;
import org.reqplay.goal.eval.estimative.Estimator;
import org.reqplay.goal.eval.rate.DefaultRater;
import org.reqplay.goal.eval.rate.Rater;
import org.reqplay.goal.eval.trace.DefaultTracer;
import org.reqplay.goal.eval.trace.Tracer;
import org.reqplay.goal.eval.validation.DefaultValidator;
import org.reqplay.goal.eval.validation.ReqValidator;
import org.reqplay.goal.play.DefaultPlayer;
import org.reqplay.goal.play.Player;
import org.reqplay.goal.recode.DefaultRecoder;
import org.reqplay.goal.recode.Recoder;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 02/07/2013
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface ReqContextSpec {

	/**
	 * The name of context. This atribute is used in artfacts generated by the
	 * compiler.
	 */
	String name();

	/**
	 * The system name to group itens.
	 */
	String system();

	// Goal 1 - play (evaluate / trace / estimate / analize)

	Class<? extends Player> player() default DefaultPlayer.class;

	Class<? extends Evaluator> evaluator() default DefaultEvaluator.class;

	Class<? extends Tracer> tracer() default DefaultTracer.class;

	Class<? extends Estimator> estimator() default DefaultEstimator.class;

	Class<? extends Analizer> analizer() default DefaultAnalizer.class;
	
	Class<? extends Rater> rater() default DefaultRater.class;
	
	Class<? extends ReqValidator> validator() default DefaultValidator.class;

	// Goal 2 - recode

	Class<? extends Recoder> recoder() default DefaultRecoder.class;

	// Goal 3 - compile

	Class<? extends ReqCompiler> compiler() default DefaultCompiler.class;

	// Goal 4 - Decompiler

	Class<? extends Decompiler> decompiler() default DefaultDecompiler.class;

	// Force calculation of complexity
	/**
	 * Force calculation of the complexity attribute even if it has a value. If
	 * it has no value, it is always calculated by the Analizer.
	 */
	boolean recalculateComplexity() default false; // calculated by Analizer

	// Code Traceability 
	boolean traceCode() default true;
	int indirectLinks() default 0;
	
	// ETC
	int idFormatSize() default 10;

}