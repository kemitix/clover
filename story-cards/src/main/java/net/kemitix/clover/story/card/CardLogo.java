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
public class CardLogo extends AbstractElement {

    @Getter private final int priority = 10;

    @Inject
    SimpleTextEffect<Graphics2D> simpleTextEffect;
    @Inject CloverProperties cloverProperties;
    @Inject StoryCardProperties properties;
    @Inject IssueConfig issueConfig;
    @Inject StoryCardDimensions dimensions;

    @Override
    public void draw(Graphics2D drawable, TypedProperties typedProperties) {
        Region region = dimensions.getLogoRegion();
        simpleTextEffect
                .fontFace(fontFace())
                .fit()
                .text(text())
                .vAlign(TextEffect.VAlignment.TOP)
                .hAlign(TextEffect.HAlignment.CENTRE)
                .region(region)
                .accept(drawable);
        drawable.setStroke(new BasicStroke(3));
        drawable.drawLine(
                region.getLeft(),
                region.getBottom(),
                region.getRight(),
                region.getBottom()
        );

//        drawable.drawRect(region.getLeft(), region.getTop(),
//                region.getWidth(), region.getHeight());
    }

    private String text() {
        return String.join("\n",
                issueConfig.getPublicationTitle().split(" "));
    }

    private FontFace fontFace() {
        return FontFace.of(
                cloverProperties.getFontLocation(),
                properties.logoFontSize(),
                issueConfig.getTitleColour(),
                cloverProperties.dropShadowXOffset(),
                cloverProperties.dropShadowYOffset());
    }
}
