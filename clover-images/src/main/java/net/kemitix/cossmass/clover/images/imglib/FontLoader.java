package net.kemitix.cossmass.clover.images.imglib;

import javax.enterprise.context.Dependent;
import java.awt.*;
import java.io.File;
import java.io.IOException;

@Dependent
public class FontLoader {

    public Font loadFont(
            final File font,
            final int size
    ) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, font);
        } catch (final FontFormatException | IOException e) {
            throw new RuntimeException("Font load error", e);
        }
    }

}
