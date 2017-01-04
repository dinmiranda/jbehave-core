package org.jbehave.core.failures;

import org.jbehave.core.steps.StepCreator.PendingStep;

import java.util.ArrayList;
import java.util.List;

/**
 * Thrown when a pending step is found
 */
@SuppressWarnings("serial")
public class PendingStepsFound extends UUIDExceptionWrapper {

    public PendingStepsFound(List<PendingStep> steps) {
        super(asString(steps));
    }

    private static String asString(List<PendingStep> steps) {
        List<String> list = new ArrayList<String>();
        for (PendingStep step : steps) {
            list.add(step.stepAsString());
        }
        return list.toString();
    }

}
