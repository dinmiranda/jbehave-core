package org.jbehave.core.io;

import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class LoadFromRelativeFileBehaviour {

    @Test
    public void testLoadFromRelativeFile() throws MalformedURLException {
        URL baseLocation = CodeLocations.codeLocationFromClass(LoadFromRelativeFileBehaviour.class);
        URL subdir = new URL(baseLocation.toString() + "/test+dir");
        LoadFromRelativeFile load = new LoadFromRelativeFile(subdir);
        String storyText = load.loadStoryAsText("dummy.story");
        assertThat(storyText, equalTo("dummy story file"));
    }

}
