package net.kemitix.clover.story.card;

import lombok.Getter;
import net.kemitix.clover.spi.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.awt.*;

@StoryCard
@ApplicationScoped
public class CardLogo implements Element<Graphics2D> {

    @Getter private final int priority = 10;

    @Inject CenteredTextEffect<Graphics2D> centeredText;
    @Inject CloverProperties cloverProperties;
    @Inject StoryCardProperties properties;
    @Inject IssueConfig issueConfig;
    @Inject StoryCardDimensions dimensions;

    @Override
    public void draw(Graphics2D drawable) {
        FontFace fontFace = FontFace.of(
                cloverProperties.getFontFile(),
                properties.getLogoFontSize(),
                issueConfig.getTitleColour(),
                XY.at(
                        cloverProperties.getDropShadowXOffset(),
                        cloverProperties.getDropShadowYOffset()));
        var text = String.join("\n",
                issueConfig.getPublicationTitle().split(" "));
        centeredText
                .fontFace(fontFace)
                .region(dimensions.getLogoRegion())
                .text(text)
                .apply(drawable);
    }
}
