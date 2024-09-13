package org.zerock.myapp.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.SOURCE;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;


@Retention(SOURCE)
@Target(TYPE)
public @interface DTO {
	String description() default "Mutable Value Object, Data Transfer Object";

} // end annotation
