package org.jbehave.core.reporters;

import org.jbehave.core.failures.StepFailed;
import org.jbehave.core.failures.UUIDExceptionWrapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * When a step fails, the {@link Throwable} that caused the failure is wrapped
 * in a {@link StepFailed} together with the step during which the failure
 * occurred. If such a failure occurs it will throw the {@link StepFailed}
 * after the story is finished.
 * </p>
 *
 * @see StepFailed
 */
public class StepFailureDecorator implements StoryReporter {

    private final StoryReporter delegate;
    private UUIDExceptionWrapper failure;

    public StepFailureDecorator(StoryReporter delegate) {
        this.delegate = delegate;
    }

    @Override
    public void afterScenario() {
        this.delegate.afterScenario();
    }

    @Override
    public void afterStory(boolean givenStory) {
        this.delegate.afterStory(givenStory);
        if (this.failure != null) {
            throw this.failure;
        }
    }

    @Override
    public void beforeScenario(String scenarioTitle) {
        this.delegate.beforeScenario(scenarioTitle);
    }

    @Override
    public void scenarioMeta(Meta meta) {
        this.delegate.scenarioMeta(meta);
    }

    @Override
    public void beforeStory(Story story, boolean givenStory) {
        this.failure = null;
        this.delegate.beforeStory(story, givenStory);
    }

    @Override
    public void narrative(Narrative narrative) {
        this.delegate.narrative(narrative);
    }

    @Override
    public void lifecyle(Lifecycle lifecycle) {
        this.delegate.lifecyle(lifecycle);
    }

    @Override
    public void failed(String step, Throwable cause) {
        this.failure = (UUIDExceptionWrapper) cause;
        this.delegate.failed(step, this.failure);
    }

    @Override
    public void failedOutcomes(String step, OutcomesTable table) {
        this.failure = new StepFailed(step, table);
        this.delegate.failedOutcomes(step, table);
    }

    @Override
    public void beforeStep(String step) {
        this.delegate.beforeStep(step);
    }

    @Override
    public void ignorable(String step) {
        this.delegate.ignorable(step);
    }

    @Override
    public void comment(String step) {
        this.delegate.comment(step);
    }

    @Override
    public void notPerformed(String step) {
        this.delegate.notPerformed(step);
    }

    @Override
    public void pending(String step) {
        this.delegate.pending(step);
    }

    @Override
    public void successful(String step) {
        this.delegate.successful(step);
    }

    @Override
    public void givenStories(GivenStories givenStories) {
        this.delegate.givenStories(givenStories);
    }

    @Override
    public void givenStories(List<String> storyPaths) {
        this.delegate.givenStories(storyPaths);
    }

    @Override
    public void beforeExamples(List<String> steps, ExamplesTable table) {
        this.delegate.beforeExamples(steps, table);
    }

    @Override
    public void example(Map<String, String> tableRow) {
        this.delegate.example(tableRow);
    }

    @Override
    public void afterExamples() {
        this.delegate.afterExamples();
    }

    @Override
    public void scenarioNotAllowed(Scenario scenario, String filter) {
        this.delegate.scenarioNotAllowed(scenario, filter);
    }

    @Override
    public void storyNotAllowed(Story story, String filter) {
        this.delegate.storyNotAllowed(story, filter);
    }

    @Override
    public void dryRun() {
        this.delegate.dryRun();
    }

    @Override
    public void pendingMethods(List<String> methods) {
        this.delegate.pendingMethods(methods);
    }

    @Override
    public void restarted(String step, Throwable cause) {
        this.delegate.restarted(step, cause);
    }

    @Override
    public void restartedStory(Story story, Throwable cause) {
        this.delegate.restartedStory(story, cause);
    }

    @Override
    public void storyCancelled(Story story, StoryDuration storyDuration) {
        this.delegate.storyCancelled(story, storyDuration);
    }
}
