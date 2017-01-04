package org.jbehave.core.io.stories;

import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.StoryReporter;
import org.jbehave.core.reporters.TxtOutput;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MyStory extends JUnitStory {

    public MyStory() {
        // Making sure this doesn't output to the build while it's running
        useConfiguration(new MostUsefulConfiguration() {
            @Override
            public StoryReporter defaultStoryReporter() {
                return new TxtOutput(new PrintStream(new ByteArrayOutputStream()));
            }
        });
    }
}