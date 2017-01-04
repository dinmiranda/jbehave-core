package org.jbehave.core.embedder;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.jbehave.core.model.StepPattern;
import org.jbehave.core.steps.DelegatingStepMonitor;
import org.jbehave.core.steps.StepMonitor;
import org.jbehave.core.steps.StepType;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchingStepMonitor extends DelegatingStepMonitor {

    private Map<String, StepMatch> matched = new HashMap<String, StepMatch>();

    public MatchingStepMonitor(StepMonitor delegate) {
        super(delegate);
    }

    public List<StepMatch> matched() {
        return new ArrayList<StepMatch>(this.matched.values());
    }

    public void stepMatchesPattern(String step, boolean matches, StepPattern pattern, Method method,
                                   Object stepsInstance) {
        super.stepMatchesPattern(step, matches, pattern, method, stepsInstance);
        if (matches) {
            String key = pattern.type() + " " + pattern.annotated();
            StepMatch stepMatch = this.matched.get(key);
            if (stepMatch == null) {
                stepMatch = new StepMatch(pattern);
                this.matched.put(key, stepMatch);
            }
        }
    }

    public static class StepMatch {
        private final StepType type; // key
        private final String annotatedPattern; // key
        private final String resolvedPattern;

        public StepMatch(StepPattern pattern) {
            this.type = pattern.type();
            this.annotatedPattern = pattern.annotated();
            this.resolvedPattern = pattern.resolved();
        }

        @Override
        public boolean equals(Object o) {
            StepMatch that = (StepMatch) o;
            return new EqualsBuilder().append(this.type, that.type)
                    .append(this.annotatedPattern, that.annotatedPattern).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder().append(this.type).append(this.annotatedPattern).toHashCode();
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
        }

    }

}
