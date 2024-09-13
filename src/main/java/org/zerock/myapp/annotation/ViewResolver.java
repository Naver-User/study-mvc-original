package org.zerock.myapp.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.SOURCE;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;


@Retention(SOURCE)
@Target(TYPE)
public @interface ViewResolver {
	String description() default "Resolve View Path with Prefix & Suffex";

} // end annotation
