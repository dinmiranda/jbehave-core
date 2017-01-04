package org.jbehave.core.steps.groovy;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.groovy.GroovyContext;
import org.jbehave.core.steps.AbstractStepsFactory;

import java.util.ArrayList;
import java.util.List;

public class GroovyStepsFactory extends AbstractStepsFactory {

    private final GroovyContext context;

    public GroovyStepsFactory(Configuration configuration, GroovyContext context) {
        super(configuration);
        this.context = context;
    }

    @Override
    protected List<Class<?>> stepsTypes() {
        List<Class<?>> types = new ArrayList<Class<?>>();
        for (Object object : context.getInstances()) {
            if (hasAnnotatedMethods(object.getClass())) {
                types.add(object.getClass());
            }
        }
        return types;
    }

    public Object createInstanceOfType(Class<?> type) {
        return context.getInstanceOfType(type);
    }

}