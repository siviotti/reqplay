package org.reqplay.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specification for field on a form.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 08/06/2013
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface FieldSpec {

	int size() default -1;
    String [] domain() default {};
    String format() default "";
    boolean required() default false;
    String requiredCondition() default "";
    String obs() default "";
}
