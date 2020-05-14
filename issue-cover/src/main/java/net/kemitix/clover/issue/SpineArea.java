package net.kemitix.clover.issue;

import net.kemitix.clover.spi.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.awt.*;

@ApplicationScoped
public class SpineArea implements SpineElement {

    @Inject
    @Issue
    Dimensions dimensions;
    @Inject
    Colours colours;

    @Override
    public void draw(Graphics2D graphics2D) {
        Region region = dimensions.getSpineCrop();
        graphics2D.setPaint(colours.getColor("black"));
        graphics2D.fillRect(
                region.getLeft(), region.getTop(),
                region.getWidth(), region.getHeight());
    }
}
