package net.kemitix.clover.spi;

import java.awt.*;
import java.util.function.Function;

public interface DrawText {
    void draw(
            String text,
            Function<Framing, XY> positioning,
            FontFace fontFace,
            Graphics2D graphics,
            FontCache fontCache,
            Area imageArea
    );
}
