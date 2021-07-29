package net.kemitix.clover.story.card;

import io.smallrye.config.ConfigMapping;
import net.kemitix.clover.spi.Region;

@ConfigMapping(prefix = "clover.story-card")
public interface StoryCardProperties {

    boolean enabled();
    int padding();
    int logoFontSize();
    int width();
    int height();

    default Region region() {
        return Region.builder()
                .width(width()).height(height())
                .build();
    }

}
