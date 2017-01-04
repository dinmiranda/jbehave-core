package org.jbehave.core.embedder;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class UnmodifiableEmbedderControls extends EmbedderControls {

    private EmbedderControls delegate;

    public UnmodifiableEmbedderControls(EmbedderControls delegate) {
        this.delegate = delegate;
    }

    @Override
    public boolean batch() {
        return this.delegate.batch();
    }

    @Override
    public boolean ignoreFailureInView() {
        return this.delegate.ignoreFailureInView();
    }

    @Override
    public boolean ignoreFailureInStories() {
        return this.delegate.ignoreFailureInStories();
    }

    @Override
    public boolean generateViewAfterStories() {
        return this.delegate.generateViewAfterStories();
    }

    @Override
    public boolean skip() {
        return this.delegate.skip();
    }

    @Override
    public boolean verboseFailures() {
        return this.delegate.verboseFailures();
    }

    @Override
    public boolean verboseFiltering() {
        return this.delegate.verboseFiltering();
    }

    @Override
    public String storyTimeouts() {
        return this.delegate.storyTimeouts();
    }

    @Override
    public long storyTimeoutInSecs() {
        return this.delegate.storyTimeoutInSecs();
    }

    @Override
    public String storyTimeoutInSecsByPath() {
        return this.delegate.storyTimeoutInSecsByPath();
    }

    @Override
    public boolean failOnStoryTimeout() {
        return this.delegate.failOnStoryTimeout();
    }

    @Override
    public int threads() {
        return this.delegate.threads();
    }

    @Override
    public EmbedderControls doBatch(boolean batch) {
        throw notAllowed();
    }

    @Override
    public EmbedderControls doIgnoreFailureInView(boolean ignoreFailureInReports) {
        throw notAllowed();
    }

    @Override
    public EmbedderControls doIgnoreFailureInStories(boolean ignoreFailureInStories) {
        throw notAllowed();
    }

    @Override
    public EmbedderControls doGenerateViewAfterStories(boolean generateViewAfterStories) {
        throw notAllowed();
    }

    @Override
    public EmbedderControls doSkip(boolean skip) {
        throw notAllowed();
    }

    @Override
    public EmbedderControls doVerboseFailures(boolean verboseFailures) {
        throw notAllowed();
    }

    @Override
    public EmbedderControls doVerboseFiltering(boolean verboseFiltering) {
        throw notAllowed();
    }

    @Override
    public EmbedderControls useStoryTimeoutInSecs(long storyTimeoutInSecs) {
        throw notAllowed();
    }

    @Override
    public EmbedderControls useStoryTimeoutInSecsByPath(String storyTimeoutInSecsByPath) {
        throw notAllowed();
    }

    @Override
    public EmbedderControls useThreads(int threads) {
        throw notAllowed();
    }

    private RuntimeException notAllowed() {
        return new ModificationNotAllowed();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append(this.delegate).toString();
    }

    public static class ModificationNotAllowed extends RuntimeException {
        public ModificationNotAllowed() {
            super("Configuration elements are unmodifiable");
        }
    }

}
