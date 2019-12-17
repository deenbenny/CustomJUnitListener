package com.servicenow.test.automation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Report {
	String testCaseName() default "";

	String featureName() default "";

	String testCaseDescription() default "";

	String status() default "";

	String exception() default "";

}
