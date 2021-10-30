package net.kemitix.clover.issue;

import lombok.Getter;
import net.kemitix.clover.spi.AbstractElement;
import net.kemitix.clover.spi.CloverProperties;
import net.kemitix.clover.spi.FrontCover;
import net.kemitix.clover.spi.IssueConfig;
import net.kemitix.clover.spi.IssueDimensions;
import net.kemitix.clover.spi.IssueType;
import net.kemitix.clover.spi.Region;
import net.kemitix.clover.spi.SimpleTextEffect;
import net.kemitix.clover.spi.TextEffect;
import net.kemitix.fontface.FontFace;
import net.kemitix.properties.typed.TypedProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.awt.*;
import java.util.function.Consumer;

@FrontCover
@ApplicationScoped
public class LogoStrapsIssue extends AbstractElement {

    @Getter private final int priority = 20;

    @Inject CloverProperties cloverProperties;
    @Inject IssueConfig issueConfig;
    @Inject SimpleTextEffect<Graphics2D> simpleTextEffect;
    @Inject IssueDimensions issueDimensions;

    @Override
    public void draw(Graphics2D drawable, TypedProperties typedProperties) {
        if (IssueType.ISSUE.equals(issueConfig.getType())) {
            doDraw(drawable);
        }
    }

    protected void doDraw(Graphics2D drawable) {
        FontFace fontFace = fontFace();
        bottomLeftStrap(fontFace)
                .andThen(bottomRightStrap(fontFace))
                .andThen(topRightStrap(fontFace))
                .accept(drawable);
    }

    protected Consumer<Graphics2D> topRightStrap(FontFace fontFace) {
        return simpleTextEffect.fontFace(fontFace)
                .text("Science Fiction and Fantasy")
                .vAlign(TextEffect.VAlignment.TOP)
                .hAlign(TextEffect.HAlignment.RIGHT)
                .region(issueDimensions.getFrontCrop()
                        .withTop(10)
                        .withWidth(w -> w - 30)
                        .withPadding(85))
                ;
    }

    protected Consumer<Graphics2D> bottomRightStrap(FontFace fontFace) {
        int top = 390;
        return simpleTextEffect.fontFace(fontFace)
                .text(issueConfig.getDate())
                .vAlign(TextEffect.VAlignment.TOP)
                .hAlign(TextEffect.HAlignment.RIGHT)
                .region(issueDimensions.getFrontCrop()
                        .withTop(top)
                        .withWidth(w -> w - 30)
                        .withPadding(85))
                ;
    }

    protected Consumer<Graphics2D> bottomLeftStrap(FontFace fontFace) {
        int top = 475;
        int left = 85;
        return simpleTextEffect.fontFace(fontFace)
                .text(String.format("Issue %s", issueConfig.getIssue()))
                .vAlign(TextEffect.VAlignment.TOP)
                .hAlign(TextEffect.HAlignment.LEFT)
                .region(Region.builder()
                        .top(top)
                        .left(left + issueDimensions.getFrontCrop().getLeft() + 30)
                        .width(issueDimensions.getFrontCrop().getWidth() -
                                (left + issueDimensions.getFrontCrop().getLeft()))
                        .height(issueDimensions.getFrontCrop().getHeight() - top)
                        .build())
                ;
    }

    protected FontFace fontFace() {
        int size = switch (issueConfig.getType()) {
            case ISSUE -> 48;
            case YEAR -> 60;
        };
        return FontFace.of(
                cloverProperties.getFontLocation(),
                size,
                issueConfig.getSubTitleColour(),
                cloverProperties.dropShadowXOffset(),
                cloverProperties.dropShadowYOffset());
    }
}
