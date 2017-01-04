package org.jbehave.core.steps.scala;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.scala.ScalaContext;
import org.jbehave.core.steps.AbstractStepsFactory;

import java.util.ArrayList;
import java.util.List;

public class ScalaStepsFactory extends AbstractStepsFactory {

    private final ScalaContext context;

    public ScalaStepsFactory(Configuration configuration, ScalaContext context) {
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