package org.zerock.myapp.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.SOURCE;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;


@Retention(SOURCE)
@Target(TYPE)
public @interface Service {
	String description() default "Service Object defined in the WEB 3-tier";

} // end annotation
