package org.reqplay.annotation.element;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.reqplay.model.Benefit;

/**
 * Annotation for Need element.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 13/07/2013
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface NeedSpec {
	Benefit benefit();
}
