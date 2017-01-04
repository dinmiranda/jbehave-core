package org.jbehave.ant;

import org.apache.tools.ant.BuildException;
import org.jbehave.core.embedder.Embedder;

import static org.apache.tools.ant.Project.MSG_INFO;

/**
 * Ant task that generates a stories view
 */
public class GenerateStoriesView extends AbstractEmbedderTask {

    public void execute() throws BuildException {
        Embedder embedder = newEmbedder();
        log("Generating stories view using embedder " + embedder, MSG_INFO);
        embedder.generateReportsView();
    }

}
