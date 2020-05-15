package net.kemitix.clover.service;

import net.kemitix.clover.spi.*;
import net.kemitix.clover.spi.Image;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.awt.*;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@ApplicationScoped
public class SpineBlock implements Block<Graphics2D> {

    private static final Logger LOGGER =
            Logger.getLogger(
                    SpineBlock.class.getName());

    @Spine
    @Inject
    Instance<Element<Graphics2D>> spineElements;

    @Override
    public void draw(Graphics2D drawable) {
        LOGGER.info("Draw elements " + spineElements.toString());
        try {
            LOGGER.info("Collect elements...");
            spineElements.stream()
                    .forEach(element -> element.draw(drawable));
        } catch (IllegalAccessError e) {
            e.printStackTrace();
        } finally {
            LOGGER.info("Finally");
        }
    }
}
