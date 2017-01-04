package org.jbehave.core.parsers;

import org.jbehave.core.model.Story;

public class TransformingStoryParser implements StoryParser {

    private final StoryParser delegate;
    private StoryTransformer[] transformers;

    public TransformingStoryParser(StoryParser delegate, StoryTransformer... transformers) {
        this.delegate = delegate;
        this.transformers = transformers;
    }

    public Story parseStory(String storyAsText) {
        return this.delegate.parseStory(transform(storyAsText));
    }

    public Story parseStory(String storyAsText, String storyPath) {
        return this.delegate.parseStory(transform(storyAsText), storyPath);
    }

    private String transform(String storyAsText) {
        String transformed = storyAsText;
        for (StoryTransformer transformer : this.transformers) {
            transformed = transformer.transform(transformed);
        }
        return transformed;
    }

}
