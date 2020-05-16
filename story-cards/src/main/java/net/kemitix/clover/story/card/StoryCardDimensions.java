package net.kemitix.clover.story.card;

import lombok.Getter;
import net.kemitix.clover.spi.*;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class StoryCardDimensions {

    @Inject IssueConfig issueConfig;
    @Inject StoryCardProperties properties;

    @Getter private float scaleFromOriginal;
    @Getter private Region sourceRegion;

    @PostConstruct
    void init() {
        Region targetSizeRegion = properties.getRegion();
        IssueStoryCards storyCardSpec = issueConfig.getStoryCards();
        scaleFromOriginal = targetSizeRegion.getWidth() / storyCardSpec.getWidth();
        sourceRegion = Region.builder()
                .top(storyCardSpec.getTop())
                .left(storyCardSpec.getLeft())
                .width(storyCardSpec.getWidth())
                .height((int) (targetSizeRegion.getHeight() * scaleFromOriginal))
                .build();
    }

    public Region getLogoRegion() {
        Region region = properties.getRegion();
        int height = region.getHeight() / 3;
        int top = region.getHeight() - height;
        return region.toBuilder()
                .top(top)
                .height(height)
                .build()
                .withPadding(properties.getPadding());
    }
}
