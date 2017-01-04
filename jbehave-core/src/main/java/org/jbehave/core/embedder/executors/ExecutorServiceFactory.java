package org.jbehave.core.embedder.executors;

import org.jbehave.core.embedder.EmbedderControls;

import java.util.concurrent.ExecutorService;

public interface ExecutorServiceFactory {

    ExecutorService create(EmbedderControls controls);

}
