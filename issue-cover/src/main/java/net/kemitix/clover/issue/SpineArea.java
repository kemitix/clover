package net.kemitix.clover.issue;

import net.kemitix.clover.spi.*;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.awt.*;
import java.util.logging.Logger;

@Spine
@Priority(10)
@ApplicationScoped
public class SpineArea implements Element<Graphics2D> {

    private static final Logger LOG =
            Logger.getLogger(
                    SpineArea.class.getName());

    @Inject @Issue
    Dimensions dimensions;

    @Inject @Spine
    private Paint paint;

    @Override
    public void draw(Graphics2D graphics2D) {
        Region region = dimensions.getSpineCrop();
        LOG.info("Draw Spine background: " + region);
        graphics2D.setPaint(paint);
        graphics2D.fillRect(
                region.getLeft(), region.getTop(),
                region.getWidth(), region.getHeight());
    }
}
