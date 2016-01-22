package org.reqplay.annotation.element;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })

public @interface TermSpec {

	String id();
	
	Class<?> entity() default Void.class;
	
	String url() default "";

}
