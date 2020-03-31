package net.kemitix.cossmass.clover.images.imglib;

import net.kemitix.cossmass.clover.images.FontFace;

import javax.enterprise.context.Dependent;
import java.awt.*;
import java.io.IOException;

@Dependent
public class FontLoader {

    public Font loadFont(final FontFace fontFace) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, fontFace.getFont());
        } catch (final FontFormatException | IOException e) {
            throw new RuntimeException("Font load error", e);
        }
    }

}
