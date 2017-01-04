package org.jbehave.core.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.jbehave.core.annotations.ToContext.RetentionLevel.EXAMPLE;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface ToContext {

    String value();

    RetentionLevel retentionLevel() default EXAMPLE;

    enum RetentionLevel {
        STORY, SCENARIO, EXAMPLE
    }
}