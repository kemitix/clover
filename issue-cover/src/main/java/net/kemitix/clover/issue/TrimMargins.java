package net.kemitix.clover.issue;

import lombok.Getter;
import net.kemitix.clover.spi.AbstractElement;
import net.kemitix.clover.spi.BoxEffect;
import net.kemitix.clover.spi.CloverProperties;
import net.kemitix.clover.spi.GuideLines;
import net.kemitix.clover.spi.IssueDimensions;
import net.kemitix.clover.spi.OpaqueFill;
import net.kemitix.clover.spi.Region;
import net.kemitix.properties.typed.TypedProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.awt.*;

@GuideLines
@ApplicationScoped
public class TrimMargins
        extends AbstractElement {

    @Inject BoxEffect<Graphics2D> boxEffect;
    @Inject IssueDimensions dimensions;
    @Inject CloverProperties cloverProperties;

    @Getter
    private final int priority = 10;

    @Override
    public void draw(
            Graphics2D drawable,
            TypedProperties typedProperties
    ) {
        int margin = cloverProperties.getCoverMargin();
        int thickness = cloverProperties.getGuideThickness();
        Region region = dimensions.getPaperbackCover().withMargin(margin);
        boxEffect.opacity(1d)
                .thickness(thickness)
                .colour("red")
                .region(region)
                .accept(drawable);
        boxEffect.opacity(0.5d)
                .thickness(thickness)
                .colour("black")
                .region(region.withMargin(thickness))
                .accept(drawable);
    }

}
