package org.reqplay.annotation.group;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.reqplay.model.element.LogEvent;

/**
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 15/07/2013
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface LogEvents {

    Class<? extends LogEvent> logEventClass() default LogEvent.class;
}
