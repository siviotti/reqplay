package org.reqplay.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 07/06/2013
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface ReqItemSpec {

    /**
     * Defines the value considered none value.
     */
    public static final int NO_VALUE = Integer.MIN_VALUE;
    
    public static final String MISSING = "Missing ReqItemSpec annotation";

    String id();

    String name();

    int priority() default NO_VALUE;

    int risk() default NO_VALUE;

    int complexity() default NO_VALUE;

    int size() default NO_VALUE;

}
