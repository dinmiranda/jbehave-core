package org.jbehave.core.model;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StoryDurationBehaviour {

    @Test
    public void shouldTimeout() {
        StoryDuration duration = new StoryDuration(1);
        sleep(2);
        assertThat(duration.update().timedOut(), is(true));
    }

    @Test
    public void shouldNotTimeout() {
        StoryDuration duration = new StoryDuration(0);
        sleep(2);
        assertThat(duration.update().timedOut(), is(false));
    }

    private void sleep(int secs) {
        try {
            TimeUnit.SECONDS.sleep(secs);
        } catch (InterruptedException e) {
        }
    }

}
