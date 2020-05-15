package net.kemitix.clover.service;

import net.kemitix.clover.spi.*;
import net.kemitix.clover.spi.Image;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.awt.*;
import java.util.function.Function;

@ApplicationScoped
public class BackCover implements Function<Image, Image> {

    @Inject @net.kemitix.clover.spi.BackCover
    Instance<Element<Graphics2D>> backCoverElements;

    @Override
    public Image apply(Image image) {
        return image.withGraphics(graphics2D ->
                Drawable.draw(backCoverElements, graphics2D));
    }

}
