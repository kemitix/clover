package net.kemitix.clover.issue;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import net.kemitix.clover.spi.AbstractElement;
import net.kemitix.clover.spi.IssueDimensions;
import net.kemitix.clover.spi.Region;
import net.kemitix.clover.spi.Spine;
import net.kemitix.properties.typed.TypedProperties;

import java.awt.*;

@Spine
@ApplicationScoped
public class SpineArea extends AbstractElement {

    @Getter private final int priority = 10;

    @Inject IssueDimensions dimensions;
    @Inject @Spine Paint paint;

    @Override
    public void draw(Graphics2D graphics2D, TypedProperties typedProperties) {
        Region region = dimensions.getSpineCrop();
        graphics2D.setPaint(paint);
        graphics2D.fillRect(
                region.getLeft(), region.getTop(),
                region.getWidth(), region.getHeight());
    }

}
