package org.jbehave.core.annotations.guice;

import com.google.inject.Module;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface UsingGuice {

    Class<? extends Module>[] modules() default {};

    boolean inheritModules() default true;

}
