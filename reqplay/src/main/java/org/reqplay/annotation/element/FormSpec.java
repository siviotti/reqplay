package org.reqplay.annotation.element;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specificaton to forms.
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 08/06/2013
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface FormSpec {

    Class<?> value() default Object.class;
    String className() default "";
}
