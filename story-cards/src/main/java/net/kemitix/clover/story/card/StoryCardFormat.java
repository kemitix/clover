package net.kemitix.clover.story.card;

import net.kemitix.clover.spi.CloverFormat;
import net.kemitix.clover.spi.Image;
import net.kemitix.properties.typed.TypedProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class StoryCardFormat implements CloverFormat {

    @Inject StoryCardProperties properties;

    @Override
    public List<Image> getImages() {
        return Collections.emptyList();
    }

    @Override
    public String getName() {
        return "story-card";
    }

    @Override
    public TypedProperties getImageProperties() {
        return null;
    }

    @Override
    public boolean isEnabled() {
        return properties.isEnabled();
    }
}
