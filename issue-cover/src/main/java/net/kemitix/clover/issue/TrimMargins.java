package net.kemitix.clover.issue;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import net.kemitix.clover.spi.AbstractElement;
import net.kemitix.clover.spi.BoxEffect;
import net.kemitix.clover.spi.CloverProperties;
import net.kemitix.clover.spi.GuideLines;
import net.kemitix.clover.spi.IssueDimensions;
import net.kemitix.clover.spi.Region;
import net.kemitix.properties.typed.TypedProperties;

import java.awt.*;

@GuideLines
@ApplicationScoped
public class TrimMargins
        extends AbstractElement {

    @Inject
    BoxEffect<Graphics2D> boxEffect;
    @Inject IssueDimensions dimensions;
    @Inject CloverProperties cloverProperties;

    @Getter
    private final int priority = 10;

    @Override
    public void draw(
            Graphics2D drawable,
            TypedProperties typedProperties
    ) {
        float trimTop = cloverProperties.trimTop();
        float trimLeft = cloverProperties.trimLeft();
        float trimRight = cloverProperties.trimRight();
        float trimBottom = cloverProperties.trimBottom();
        Region region = dimensions.getPaperbackCover()
                .withTop(top -> top + dpi(trimTop))
                .withLeft(left -> left + dpi(trimLeft))
                .withWidth(width -> width - dpi(trimLeft + trimRight))
                .withHeight(height -> height - dpi(trimTop + trimBottom));
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

    private int dpi(float inches) {
        return (int) (cloverProperties.dpi() * inches);
    }

}
