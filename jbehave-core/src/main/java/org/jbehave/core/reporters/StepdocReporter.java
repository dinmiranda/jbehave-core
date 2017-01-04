package org.jbehave.core.reporters;

import org.jbehave.core.steps.Stepdoc;

import java.util.List;

public interface StepdocReporter {

    void stepdocs(List<Stepdoc> stepdocs, List<Object> stepsInstances);

    void stepdocsMatching(String stepAsString, List<Stepdoc> matching, List<Object> stepsIntances);

}
