package org.reqplay.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 09/06/2013
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface AlternativeScenario {

	String id();
	String title();
	String [] steps();
	String action();
}
