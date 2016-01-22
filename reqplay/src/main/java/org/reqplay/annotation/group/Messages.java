package org.reqplay.annotation.group;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for group classes (Messages etc).
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 14/07/2013
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface Messages {
	
	String resourceBundleName() default "";

}
