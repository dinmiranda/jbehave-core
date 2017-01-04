package org.jbehave.core;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InjectableStepsFactory;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * <p>
 * Abstract implementation of {@link Embeddable} which allows to configure the
 * {@link Embedder} used to run the stories, using the {@link Configuration} and
 * the {@link InjectableStepsFactory} specified.
 * </p>
 * <p>
 * The instances of the {@link Configuration} and the
 * {@link InjectableStepsFactory} can be provided either via the
 * {@link #useConfiguration(Configuration)} and
 * {@link #useStepsFactory(InjectableStepsFactory)} methods or overriding the
 * {@link #configuration()} and {@link #stepsFactory()} methods.
 * </p>
 * <p>
 * If overriding the {@link #configuration()} method and providing an
 * {@link InjectableStepsFactory} which requires a {@link Configuration}, then
 * care must be taken to avoid re-instantiating the {@link Configuration}. E.g.:
 * <p>
 * <pre>
 * {@code
 * public Configuration configuration() {
 *   if ( super.hasConfiguration() ){
 *     return super.configuration();
 *   }
 *   return new MostUsefulConfiguration()...;
 * }
 * </pre>
 * <p>
 * </p>
 * <p>
 * Note that no defaults are provided by this implementation. If no values are
 * set by the subclasses, then <code>null</code> values are passed to the
 * configured {@link Embedder}, which is responsible for setting the default
 * values.
 * </p>
 * <p>
 * Typically, users that use JUnit will find it easier to extend other
 * implementations, such as {@link JUnitStory} or {@link JUnitStories}, which
 * implement the {@link#run()} using the configured {@link Embedder} and
 * annotate it with JUnit's annotations.
 * </p>
 */
public abstract class ConfigurableEmbedder implements Embeddable {

    private Embedder embedder = new Embedder();
    private Configuration configuration;
    private InjectableStepsFactory stepsFactory;
    private List<CandidateSteps> candidateSteps;

    public void useEmbedder(Embedder embedder) {
        this.embedder = embedder;
    }

    public void useConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration configuration() {
        return this.configuration;
    }

    public boolean hasConfiguration() {
        return this.configuration != null;
    }

    /**
     * @deprecated Use {@link #useStepsFactory(InjectableStepsFactory)}
     */
    public void addSteps(CandidateSteps... steps) {
        addSteps(asList(steps));
    }

    /**
     * @deprecated Use {@link #useStepsFactory(InjectableStepsFactory)}
     */
    public void addSteps(List<CandidateSteps> steps) {
        if (this.candidateSteps == null) {
            this.candidateSteps = new ArrayList<CandidateSteps>();
        }
        this.candidateSteps.addAll(steps);
    }

    /**
     * @deprecated Use {@link #stepsFactory()}
     */
    public List<CandidateSteps> candidateSteps() {
        return this.candidateSteps;
    }

    public void useStepsFactory(InjectableStepsFactory stepsFactory) {
        this.stepsFactory = stepsFactory;
    }

    public InjectableStepsFactory stepsFactory() {
        return this.stepsFactory;
    }

    public boolean hasStepsFactory() {
        return this.stepsFactory != null;
    }

    public Embedder configuredEmbedder() {
        if (this.configuration == null) {
            this.configuration = configuration();
        }
        this.embedder.useConfiguration(this.configuration);
        if (this.candidateSteps == null) {
            this.candidateSteps = candidateSteps();
        }
        this.embedder.useCandidateSteps(this.candidateSteps);
        if (this.stepsFactory == null) {
            this.stepsFactory = stepsFactory();
        }
        this.embedder.useStepsFactory(this.stepsFactory);
        return this.embedder;
    }

}
