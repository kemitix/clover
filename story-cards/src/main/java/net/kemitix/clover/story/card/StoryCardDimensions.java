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
    @Getter private Region cardRegion;

    @PostConstruct
    void init() {
        Region targetSizeRegion = properties.getRegion();
        cardRegion = targetSizeRegion.toBuilder().top(0).left(0).build();
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

    public Region getTitleRegion() {
        Region region = properties.getRegion();
        int height = region.getHeight() / 3;
        return region.toBuilder()
                .height(height)
                .width(properties.getWidth())
                .build()
                .withPadding(properties.getPadding());
    }
}
