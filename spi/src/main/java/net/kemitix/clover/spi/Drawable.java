package net.kemitix.clover.spi;

import net.kemitix.properties.typed.TypedProperties;

import java.awt.*;
import java.util.stream.Stream;

import static net.kemitix.clover.spi.Prioritised.byPriority;

public interface Drawable<T> extends Prioritised  {
    void draw(T drawable, TypedProperties typedProperties);

    static void draw(
            Stream<? extends Drawable<Graphics2D>> drawables,
            Graphics2D graphics2D,
            TypedProperties typedProperties
    ) {
        drawables.sorted(byPriority())
                .forEach(element -> element.draw(graphics2D, typedProperties));
    }

}
