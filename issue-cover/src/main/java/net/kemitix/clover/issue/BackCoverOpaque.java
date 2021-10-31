package net.kemitix.clover.issue;

import lombok.Getter;
import net.kemitix.clover.spi.AbstractElement;
import net.kemitix.clover.spi.BackCover;
import net.kemitix.clover.spi.BackCoverBackgroundBox;
import net.kemitix.clover.spi.CloverProperties;
import net.kemitix.clover.spi.Effect;
import net.kemitix.clover.spi.GuideLines;
import net.kemitix.clover.spi.IssueDimensions;
import net.kemitix.clover.spi.OpaqueFill;
import net.kemitix.clover.spi.Region;
import net.kemitix.properties.typed.TypedProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.awt.*;

@BackCover
@ApplicationScoped
public class BackCoverOpaque
        extends AbstractElement {

    @Getter
    private final int priority = 5;

    @Inject OpaqueFill<Graphics2D> opaqueFill;
    @Inject IssueDimensions dimensions;
    @Inject BackCoverBackgroundBox backgroundBox;
    @Inject CloverProperties cloverProperties;
    @Inject @GuideLines BarcodeGuide barcodeGuide;

    @Override
    public void draw(
            Graphics2D drawable,
            TypedProperties typedProperties
    ) {
        if (backgroundBox.isShow()) {
            box(drawable,
                    backgroundBox.getOuterColour().getOpacity(),
                    backgroundBox.getOuterColour().getColour(),
                    getMargin(0),
                    getMargin(1));
            box(drawable,
                    backgroundBox.getMiddleColour().getOpacity(),
                    backgroundBox.getMiddleColour().getColour(),
                    getMargin(1),
                    getMargin(2));
            fill(drawable,
                    backgroundBox.getInnerColour().getOpacity(),
                    backgroundBox.getInnerColour().getColour(),
                    getMargin(2));
        }
    }

    private int getMargin(
            int steps
    ) {
        int outerMargin = backgroundBox.getOuterMargin();
        return outerMargin + (steps * backgroundBox.getMarginStep());
    }

    private void box(
            Graphics2D drawable,
            double opacity,
            String colour,
            int marginOuter,
            int marginInner
    ) {
        var pen = opaqueFill.opacity(opacity).colour(colour);

        int width = marginInner - marginOuter;
        Region outer = getRegion().withMargin(marginOuter);

        Region topBar = outer.withHeight(width);
        pen.region(topBar).accept(drawable);

        Region leftBar = outer.withWidth(width);
        pen.region(leftBar).accept(drawable);

        Region rightBar = outer
                .withLeft(outer.getRight() - width)
                .withWidth(width);
        pen.region(rightBar).accept(drawable);

        Region bottomBar = outer
                .withTop(outer.getBottom() - width)
                .withHeight(width);
        pen.region(bottomBar).accept(drawable);
    }

    private Region getRegion() {
        int trim = dpi(cloverProperties.trimLeft());
        return Region.builder()
                .top(trim)
                .left(trim)
                .width(dimensions.getFrontCrop().getWidth() - trim)
                .build()
                .withBottom(barcodeGuide.getBarcodeRegion().getTop())
                ;
    }

    private int dpi(float inches) {
        return (int) (inches * cloverProperties.dpi());
    }

    private void fill(
            Graphics2D drawable,
            double opacity,
            String colour,
            int margin
    ) {
        opaqueFill.opacity(opacity)
                .colour(colour)
                .region(getRegion()
                        .withMargin(margin)
                )
                .accept(drawable);
    }

}
