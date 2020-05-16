package net.kemitix.clover.service;

import net.kemitix.clover.spi.*;
import net.kemitix.clover.spi.Image;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.awt.*;
import java.util.function.Function;

@ApplicationScoped
public class FrontCoverBlock implements Function<Image, Image> {

    @Inject @FrontCover Instance<Element<Graphics2D>> elements;

    @Override
    public Image apply(Image image) {
        return elements()
                .apply(image);
    }

    private Function<Image, Image> elements() {
        return image ->
                image.withGraphics(graphics2D ->
                        Drawable.draw(elements, graphics2D));
    }
}
