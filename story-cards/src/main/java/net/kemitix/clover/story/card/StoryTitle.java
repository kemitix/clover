package net.kemitix.clover.story.card;

import lombok.Getter;
import net.kemitix.clover.spi.*;
import net.kemitix.properties.typed.TypedProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.awt.*;

@StoryCard
@ApplicationScoped
public class StoryTitle implements Element<Graphics2D> {

    @Getter private final int priority = 20;

    @Inject CenteredTextEffect<Graphics2D> centeredText;
    @Inject CloverProperties cloverProperties;
    @Inject IssueConfig issueConfig;
    @Inject StoryCardDimensions dimensions;

    @Override
    public void draw(Graphics2D drawable, TypedProperties typedProperties) {
        IssueStory story =
                typedProperties.find(TypedKeys.Story.class, IssueStory.class)
                        .orElseThrow();
        int storyCardFontSize = story.getStoryCardFontSize();
        FontFace fontFace = FontFace.of(
                cloverProperties.getFontFile(),
                storyCardFontSize,
                issueConfig.getTitleColour(),
                XY.at(
                        cloverProperties.getDropShadowXOffset(),
                        cloverProperties.getDropShadowYOffset()));
        var text = story.getTitle();
        Region region = dimensions.getTitleRegion();
        centeredText
                .fontFace(fontFace)
                .region(region)
                .hAlign(TextEffect.HAlignment.TOP)
                .vAlign(TextEffect.VAlignment.CENTRE)
                .text(text)
                .apply(drawable);

        drawable.drawRect(region.getLeft(), region.getTop(),
                region.getWidth(), region.getHeight());
    }
}
