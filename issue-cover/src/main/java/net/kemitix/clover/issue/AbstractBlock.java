package net.kemitix.clover.issue;

import net.kemitix.clover.spi.Block;
import net.kemitix.clover.spi.Drawable;
import net.kemitix.properties.typed.TypedProperties;

import java.awt.*;
import java.util.stream.Stream;

public abstract class AbstractBlock implements Block<Graphics2D> {

    @Override
    public void draw(Graphics2D drawable, TypedProperties typedProperties) {
        Drawable.draw(elements(), drawable, typedProperties);
    }

    protected abstract Stream<? extends Drawable<Graphics2D>> elements();

}
