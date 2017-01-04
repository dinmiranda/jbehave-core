package org.jbehave.core.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static java.util.Collections.unmodifiableList;

public class Scenario {

    private final String title;
    private final Meta meta;
    private final GivenStories givenStories;
    private final ExamplesTable examplesTable;
    private final List<String> steps;

    public Scenario() {
        this(Arrays.<String>asList());
    }

    public Scenario(List<String> steps) {
        this("", steps);
    }

    public Scenario(String title, Meta meta) {
        this(title, meta, GivenStories.EMPTY, ExamplesTable.EMPTY, Arrays.<String>asList());
    }

    public Scenario(String title, List<String> steps) {
        this(title, Meta.EMPTY, GivenStories.EMPTY, ExamplesTable.EMPTY, steps);
    }

    public Scenario(String title, Meta meta, GivenStories givenStories, ExamplesTable examplesTable, List<String> steps) {
        this.title = title;
        this.meta = meta;
        this.givenStories = givenStories;
        this.examplesTable = examplesTable;
        this.steps = steps;
    }

    public String getTitle() {
        return this.title;
    }

    public GivenStories getGivenStories() {
        return this.givenStories;
    }

    public ExamplesTable getExamplesTable() {
        return this.examplesTable;
    }

    public Meta asMeta(String prefix) {
        Properties p = new Properties();
        p.setProperty(prefix + "title", this.title);
        p.setProperty(prefix + "givenStories", this.givenStories.asString());
        p.setProperty(prefix + "examplesTable", this.examplesTable.asString());
        return new Meta(p);
    }

    public Meta getMeta() {
        return this.meta;
    }

    public List<String> getSteps() {
        return unmodifiableList(this.steps);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
