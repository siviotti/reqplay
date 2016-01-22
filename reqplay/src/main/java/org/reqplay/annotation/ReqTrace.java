package org.reqplay.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.reqplay.model.ReqItem;

/**
 * 
 * @author Douglas Siviotti (073.116.317-69)
 * @version 1.0.0 25/06/q2013
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.METHOD,
        ElementType.CONSTRUCTOR, ElementType.LOCAL_VARIABLE,
        ElementType.PACKAGE, ElementType.PARAMETER })
public @interface ReqTrace {

    Class<? extends ReqItem>[] reqItem() default {};

    Class<?>[] specItem() default {};
    
}
