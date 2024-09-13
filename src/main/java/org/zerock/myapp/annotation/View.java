package org.zerock.myapp.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.SOURCE;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;


@Retention(SOURCE)
@Target(TYPE)
public @interface View {
	String description() default "Invoke the specified view name's component";

} // end annotation
