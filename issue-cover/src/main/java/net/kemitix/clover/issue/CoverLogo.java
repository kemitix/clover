package net.kemitix.clover.issue;

import lombok.Getter;
import net.kemitix.clover.spi.*;
import net.kemitix.properties.typed.TypedProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.awt.*;

@FrontCover
@ApplicationScoped
public class CoverLogo implements Element<Graphics2D> {

    @Getter private final int priority = 10;

    @Inject CloverProperties cloverProperties;
    @Inject IssueConfig issueConfig;
    @Inject CenteredTextEffect<Graphics2D> centeredText;
    @Inject IssueDimensions dimensions;

    @Override
    public void draw(Graphics2D drawable, TypedProperties typedProperties) {
        FontFace fontFace = FontFace.of(
                cloverProperties.getFontFile(),
                217,
                issueConfig.getTitleColour(),
                XY.at(
                        cloverProperties.getDropShadowXOffset(),
                        cloverProperties.getDropShadowYOffset()));
        var text = String.join("\n",
                issueConfig.getPublicationTitle().split(" "));
        centeredText
                .fontFace(fontFace)
                .region(dimensions.getFrontCrop().withPadding(85))
                .hAlign(TextEffect.HAlignment.TOP)
                .vAlign(TextEffect.VAlignment.CENTRE)
                .text(text)
                .apply(drawable);
    }
}
