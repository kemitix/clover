package net.kemitix.clover.images.imglib;

import net.kemitix.clover.spi.images.FontFace;

import java.awt.*;

public interface FontLoader {
    Font loadFont(FontFace fontFace);
}
