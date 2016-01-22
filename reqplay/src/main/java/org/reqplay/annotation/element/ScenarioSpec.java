package org.reqplay.annotation.element;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 16/07/2013
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ScenarioSpec {
    

    Class<?> feature();
    
    String level() default "System"; 
    
}
