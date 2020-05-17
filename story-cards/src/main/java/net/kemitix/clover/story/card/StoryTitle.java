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

    @Inject SimpleTextEffect<Graphics2D> simpleTextEffect;
    @Inject CloverProperties cloverProperties;
    @Inject IssueConfig issueConfig;
    @Inject StoryCardDimensions dimensions;

    @Override
    public void draw(Graphics2D drawable, TypedProperties typedProperties) {
        IssueStory story =
                typedProperties.find(TypedKeys.Story.class, IssueStory.class)
                        .orElseThrow();
        FontFace fontFace = FontFace.of(
                cloverProperties.getFontFile(),
                0,
                issueConfig.getTitleColour(),
                XY.at(
                        cloverProperties.getDropShadowXOffset(),
                        cloverProperties.getDropShadowYOffset()));
        var text = story.getTitle();
        Region region = dimensions.getTitleRegion();
        simpleTextEffect
                .fontFace(fontFace)
                .text(text)
                .vAlign(TextEffect.VAlignment.CENTRE)
                .hAlign(TextEffect.HAlignment.CENTRE)
                .region(region)
                .accept(drawable);

//        drawable.drawRect(region.getLeft(), region.getTop(),
//                region.getWidth(), region.getHeight());
    }
}
