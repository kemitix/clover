package net.kemitix.cossmass.clover.images.imglib;

import net.kemitix.cossmass.clover.images.FatalCloverError;
import net.kemitix.cossmass.clover.images.FontFace;

import javax.enterprise.context.Dependent;
import java.awt.*;
import java.io.IOException;
import java.util.logging.Logger;

@Dependent
public class FontLoader {

    private static final Logger LOGGER =
            Logger.getLogger(
                    CloverImage.class.getName());

    public Font loadFont(final FontFace fontFace) {
        LOGGER.info(String.format("Loading %s %d",
                fontFace.getFont(), fontFace.getSize()));
        try {
            return Font.createFont(Font.TRUETYPE_FONT, fontFace.getFont())
                    .deriveFont(Font.PLAIN, fontFace.getSize());
        } catch (final FontFormatException | IOException e) {
            throw new FatalCloverError("Font load error", e);
        }
    }

}
