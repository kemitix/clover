package net.kemitix.clover.issue;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import net.kemitix.clover.spi.AbstractElement;
import net.kemitix.clover.spi.BoxEffect;
import net.kemitix.clover.spi.CloverProperties;
import net.kemitix.clover.spi.GuideLines;
import net.kemitix.clover.spi.IssueDimensions;
import net.kemitix.clover.spi.OpaqueFill;
import net.kemitix.clover.spi.Region;
import net.kemitix.properties.typed.TypedProperties;

import java.awt.*;

@GuideLines
@ApplicationScoped
public class BarcodeGuide
        extends AbstractElement {

    @Inject
    CloverProperties cloverProperties;
    @Inject OpaqueFill<Graphics2D> opaqueFill;
    @Inject IssueDimensions issueDimensions;
    @Inject BoxEffect<Graphics2D> boxEffect;

    @Getter
    private int priority = 10;

    @Override
    public void draw(
            Graphics2D drawable,
            TypedProperties typedProperties
    ) {
        Region region = getBarcodeRegion();
        String fillColour = cloverProperties.barcodeFillColour();
        opaqueFill.opacity(1d)
                .colour(fillColour)
                .region(region.withPadding(20))
                .accept(drawable);
        // box
        int thickness = cloverProperties.guideThickness();
        boxEffect.opacity(0.5d)
                .thickness(thickness)
                .colour("black")
                .region(region.withMargin(-thickness))
                .accept(drawable);
        boxEffect.opacity(1d)
                .thickness(thickness)
                .colour(cloverProperties.trimColour())
                .region(region)
                .accept(drawable);
        boxEffect.opacity(0.5d)
                .thickness(thickness)
                .colour("black")
                .region(region.withMargin(thickness))
                .accept(drawable);
    }

    public Region getBarcodeRegion() {
        Region frontCrop = issueDimensions.getFrontCrop();
        Region region = frontCrop
                .withTop(frontCrop.getHeight() - cloverProperties.barcodeBottomPadding() - cloverProperties.getBarcodeHeight())
                .withLeft(frontCrop.getWidth() - cloverProperties.barcodeRightPadding() - cloverProperties.getBarcodeWidth())
                .withWidth(cloverProperties.getBarcodeWidth())
                .withHeight(cloverProperties.getBarcodeHeight());
        return region.withPadding(20);
    }

}
