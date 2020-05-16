package net.kemitix.clover.story.card;

import net.kemitix.clover.spi.Region;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StoryCardDimensions {

    float getScaleFromOriginal() {
        return 1;
    }

    Region getRegion() {
        return Region.builder()
                .width(100).height(100)
                .build();
    }

}
