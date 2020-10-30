package net.kemitix.clover.issue;

import lombok.Getter;
import net.kemitix.clover.spi.AbstractElement;
import net.kemitix.clover.spi.CloverProperties;
import net.kemitix.clover.spi.GuideLines;
import net.kemitix.clover.spi.OpaqueFill;
import net.kemitix.clover.spi.Region;
import net.kemitix.properties.typed.TypedProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.awt.*;

@GuideLines
@ApplicationScoped
public class BarcodeGuide
        extends AbstractElement {

    @Inject CloverProperties cloverProperties;
    @Inject OpaqueFill<Graphics2D> opaqueFill;

    @Getter
    private int priority = 10;

    @Override
    public void draw(
            Graphics2D drawable,
            TypedProperties typedProperties
    ) {
        final Region region = Region.builder()
                .top(cloverProperties.getBarcodeTop())
                .left(cloverProperties.getBarcodeLeft())
                .width(cloverProperties.getBarcodeWidth())
                .height(cloverProperties.getBarcodeHeight())
                .build();
        final String fillColour = cloverProperties.getBarcodeFillColour();
        opaqueFill.opacity(1d)
                .colour(fillColour)
                .region(region)
                .accept(drawable);
    }

}
