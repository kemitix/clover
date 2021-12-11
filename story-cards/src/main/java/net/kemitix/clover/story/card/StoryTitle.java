package net.kemitix.clover.story.card;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import net.kemitix.clover.spi.*;
import net.kemitix.fontface.FontFace;
import net.kemitix.properties.typed.TypedProperties;

import java.awt.*;

@StoryCard
@ApplicationScoped
public class StoryTitle extends AbstractElement {

    @Getter private final int priority = 20;

    @Inject
    SimpleTextEffect<Graphics2D> simpleTextEffect;
    @Inject OpaqueFill<Graphics2D> opaqueFill;
    @Inject CloverProperties cloverProperties;
    @Inject IssueConfig issueConfig;
    @Inject StoryCardDimensions dimensions;

    @Override
    public void draw(Graphics2D drawable, TypedProperties typedProperties) {
        IssueStory story =
                typedProperties.find(TypedKeys.Story.class, IssueStory.class)
                        .orElseThrow();
        FontFace fontFace = FontFace.of(
                cloverProperties.getFontLocation(),
                0,
                issueConfig.getTitleColour(),
                cloverProperties.dropShadowXOffset(),
                cloverProperties.dropShadowYOffset());
        var text = story.getTitle();
        Region region = dimensions.getTitleRegion();
        opaqueFill
                .opacity(0.25D)
                .colour("white")
                .region(region)
                .accept(drawable);
        simpleTextEffect
                .fontFace(fontFace)
                .fit()
                .text(text)
                .vAlign(TextEffect.VAlignment.CENTRE)
                .hAlign(TextEffect.HAlignment.CENTRE)
                .region(region)
                .accept(drawable);

//        drawable.drawRect(region.getLeft(), region.getTop(),
//                region.getWidth(), region.getHeight());
    }
}
