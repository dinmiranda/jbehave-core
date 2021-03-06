package org.jbehave.ant;

import org.apache.tools.ant.BuildException;
import org.jbehave.core.embedder.Embedder;

import static org.apache.tools.ant.Project.MSG_INFO;

/**
 * Ant task that maps stories as paths
 */
public class MapStoriesAsPaths extends AbstractEmbedderTask {

    public void execute() throws BuildException {
        Embedder embedder = newEmbedder();
        log("Mapping stories as paths using embedder " + embedder, MSG_INFO);
        embedder.mapStoriesAsPaths(storyPaths());
    }

}