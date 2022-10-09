package net.kemitix.clover.issue;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import net.kemitix.clover.spi.AbstractElement;
import net.kemitix.clover.spi.CloverProperties;
import net.kemitix.clover.spi.FrontCover;
import net.kemitix.clover.spi.IssueConfig;
import net.kemitix.clover.spi.IssueDimensions;
import net.kemitix.clover.spi.SimpleTextEffect;
import net.kemitix.clover.spi.TextEffect;
import net.kemitix.fontface.FontFace;
import net.kemitix.properties.typed.TypedProperties;

import java.awt.*;

@FrontCover
@ApplicationScoped
public class CoverLogo extends AbstractElement {

    @Getter private final int priority = 10;

    @Inject CloverProperties cloverProperties;
    @Inject IssueConfig issueConfig;
    @Inject SimpleTextEffect<Graphics2D> simpleTextEffect;
    @Inject IssueDimensions dimensions;

    @Override
    public void draw(Graphics2D drawable, TypedProperties typedProperties) {
        int size = switch (issueConfig.getType()) {
            case ISSUE -> 217;
            case YEAR -> 266;
        };
        FontFace fontFace = FontFace.of(
                cloverProperties.getFontLocation(),
                size,
                issueConfig.getTitleColour(),
                issueConfig.getTitleShadow(),
                cloverProperties.dropShadowXOffset(),
                cloverProperties.dropShadowYOffset());
        var text = String.join("\n",
                issueConfig.getPublicationTitle().split(" "));
        simpleTextEffect
                .fontFace(fontFace)
                .text(text)
                .vAlign(TextEffect.VAlignment.TOP)
                .hAlign(TextEffect.HAlignment.CENTRE)
                .region(dimensions.getFrontCrop().withPadding(85))
                .accept(drawable);
    }
}
