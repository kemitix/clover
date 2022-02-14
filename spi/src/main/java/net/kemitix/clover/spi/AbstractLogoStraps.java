package net.kemitix.clover.spi;

import lombok.Getter;
import net.kemitix.fontface.FontFace;
import net.kemitix.properties.typed.TypedProperties;

import javax.inject.Inject;
import java.awt.*;
import java.util.function.Consumer;

public abstract class AbstractLogoStraps extends AbstractElement {

    @Getter
    private final int priority = 20;
    @Inject @Getter CloverProperties cloverProperties;
    @Inject @Getter IssueConfig issueConfig;
    @Inject @Getter SimpleTextEffect<Graphics2D> simpleTextEffect;
    @Inject @Getter IssueDimensions issueDimensions;

    public void doDraw(Graphics2D drawable) {
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
                issueConfig.getSubTitleShadow(),
                cloverProperties.dropShadowXOffset(),
                cloverProperties.dropShadowYOffset());
    }

    @Override
    public void draw(Graphics2D drawable, TypedProperties typedProperties) {
        if (typeFilter(issueConfig.getType()))  {
            doDraw(drawable);
        }
    }
}
