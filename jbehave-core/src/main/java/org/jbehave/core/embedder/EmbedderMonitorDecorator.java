package org.jbehave.core.embedder;

import org.jbehave.core.failures.BatchFailures;
import org.jbehave.core.model.Meta;
import org.jbehave.core.model.Scenario;
import org.jbehave.core.model.Story;
import org.jbehave.core.model.StoryDuration;
import org.jbehave.core.model.StoryMaps;
import org.jbehave.core.reporters.ReportsCount;

import java.io.File;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;

/**
 * Decorator of EmbedderMonitor that delegates to an injected instance and
 * allows classes extending it to override only the methods that are needed.
 */
public class EmbedderMonitorDecorator implements EmbedderMonitor {

    private final EmbedderMonitor delegate;

    public EmbedderMonitorDecorator(EmbedderMonitor delegate) {
        this.delegate = delegate;
    }

    public void runningEmbeddable(String name) {
        this.delegate.runningEmbeddable(name);
    }

    public void embeddableFailed(String name, Throwable cause) {
        this.delegate.embeddableFailed(name, cause);
    }

    public void embeddableNotConfigurable(String name) {
        this.delegate.embeddableNotConfigurable(name);
    }

    public void embeddablesSkipped(List<String> classNames) {
        this.delegate.embeddablesSkipped(classNames);
    }

    public void metaNotAllowed(Meta meta, MetaFilter filter) {
        this.delegate.metaNotAllowed(meta, filter);
    }

    public void runningStory(String path) {
        this.delegate.runningStory(path);
    }

    public void storyFailed(String path, Throwable cause) {
        this.delegate.storyFailed(path, cause);
    }

    public void storiesSkipped(List<String> storyPaths) {
        this.delegate.storiesSkipped(storyPaths);
    }

    public void storiesNotAllowed(List<Story> notAllowed, MetaFilter filter) {
        this.delegate.storiesNotAllowed(notAllowed, filter);
    }

    public void storiesNotAllowed(List<Story> stories, MetaFilter filter, boolean verbose) {
        this.delegate.storiesNotAllowed(stories, filter, verbose);
    }

    public void scenarioNotAllowed(Scenario scenario, MetaFilter filter) {
        this.delegate.scenarioNotAllowed(scenario, filter);
    }

    public void batchFailed(BatchFailures failures) {
        this.delegate.batchFailed(failures);
    }

    public void beforeOrAfterStoriesFailed() {
        this.delegate.beforeOrAfterStoriesFailed();
    }

    public void generatingReportsView(File outputDirectory, List<String> formats, Properties viewProperties) {
        this.delegate.generatingReportsView(outputDirectory, formats, viewProperties);
    }

    public void reportsViewGenerationFailed(File outputDirectory, List<String> formats, Properties viewProperties,
                                            Throwable cause) {
        this.delegate.reportsViewGenerationFailed(outputDirectory, formats, viewProperties, cause);
    }

    public void reportsViewGenerated(ReportsCount count) {
        this.delegate.reportsViewGenerated(count);
    }

    public void reportsViewFailures(ReportsCount count) {
        this.delegate.reportsViewFailures(count);
    }

    public void reportsViewNotGenerated() {
        this.delegate.reportsViewNotGenerated();
    }

    public void runningWithAnnotatedEmbedderRunner(String className) {
        this.delegate.runningWithAnnotatedEmbedderRunner(className);
    }

    public void annotatedInstanceNotOfType(Object annotatedInstance, Class<?> type) {
        this.delegate.annotatedInstanceNotOfType(annotatedInstance, type);
    }

    public void mappingStory(String storyPath, List<String> metaFilters) {
        this.delegate.mappingStory(storyPath, metaFilters);
    }

    public void generatingMapsView(File outputDirectory, StoryMaps storyMaps, Properties viewProperties) {
        this.delegate.generatingMapsView(outputDirectory, storyMaps, viewProperties);
    }

    public void mapsViewGenerationFailed(File outputDirectory, StoryMaps storyMaps, Properties viewProperties,
                                         Throwable cause) {
        this.delegate.mapsViewGenerationFailed(outputDirectory, storyMaps, viewProperties, cause);
    }

    public void generatingNavigatorView(File outputDirectory, Properties viewResources) {
        this.delegate.generatingNavigatorView(outputDirectory, viewResources);
    }

    public void navigatorViewGenerationFailed(File outputDirectory, Properties viewResources, Throwable cause) {
        this.delegate.navigatorViewGenerationFailed(outputDirectory, viewResources, cause);
    }

    public void navigatorViewNotGenerated() {
        this.delegate.navigatorViewNotGenerated();
    }

    public void processingSystemProperties(Properties properties) {
        this.delegate.processingSystemProperties(properties);
    }

    public void systemPropertySet(String name, String value) {
        this.delegate.systemPropertySet(name, value);
    }

    public void storyTimeout(Story story, StoryDuration storyDuration) {
        this.delegate.storyTimeout(story, storyDuration);
    }

    public void usingThreads(int threads) {
        this.delegate.usingThreads(threads);
    }

    public void usingExecutorService(ExecutorService executorService) {
        this.delegate.usingExecutorService(executorService);
    }

    public void usingControls(EmbedderControls embedderControls) {
        this.delegate.usingControls(embedderControls);
    }

    public void invalidTimeoutFormat(String path) {
        this.delegate.invalidTimeoutFormat(path);
    }

    public void usingTimeout(String path, long timeout) {
        this.delegate.usingTimeout(path, timeout);
    }

}
