package net.kemitix.clover.story.card;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import net.kemitix.clover.spi.*;

@ApplicationScoped
public class StoryCardDimensions {

    @Inject
    IssueConfig issueConfig;
    @Inject StoryCardProperties properties;

    @Getter private float scaleFromOriginal;
    @Getter private Region sourceRegion;
    @Getter private Region cardRegion;

    private int rows = 12;
    private int cols = 12;
    private int rowHeight;
    private int colWidth;

    @PostConstruct
    void init() {
        Region targetSizeRegion = properties.region();
        cardRegion = targetSizeRegion.toBuilder().top(0).left(0).build();
        rowHeight = cardRegion.getHeight() / rows;
        colWidth = cardRegion.getWidth() / cols;
        IssueStoryCards storyCardSpec = issueConfig.getStoryCards();
        scaleFromOriginal = (float) targetSizeRegion.getWidth() / storyCardSpec.getWidth();
        sourceRegion = Region.builder()
                .top(storyCardSpec.getTop())
                .left(storyCardSpec.getLeft())
                .width(targetSizeRegion.getWidth())
                .height(targetSizeRegion.getHeight())
                .build();
    }

    public Region getLogoRegion() {
        return gridPosition(0, 0, 12, 2);
    }

    public Region getTitleRegion() {
        return gridPosition(3, 0, 7, 6);
    }

    public Region getAuthorRegion() {
        return gridPosition(10, 0, 12, 2);
    }

    private Region gridPosition(
            int top,
            int left,
            int width,
            int height
    ) {
        return cardRegion.toBuilder()
                .top(top * rowHeight)
                .left(left * colWidth)
                .height(height * rowHeight)
                .width(width * colWidth)
                .build()
                .withPadding(properties.padding());
    }
}
