package net.kemitix.cossmass.clover.images.imglib;

import net.kemitix.cossmass.clover.images.FatalCloverError;
import net.kemitix.cossmass.clover.images.FontFace;

import javax.enterprise.context.Dependent;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Dependent
public class FontLoader {

    private static final Logger LOGGER =
            Logger.getLogger(
                    CloverImage.class.getName());

    private final Map<File, Map<Integer, Font>> cache = new HashMap<>();

    public Font loadFont(final FontFace fontFace) {
        LOGGER.info(String.format("Requesting %s %d",
                fontFace.getFont(), fontFace.getSize()));
        return cache.computeIfAbsent(
                fontFace.getFont(),
                file ->
                        Collections.singletonMap(
                                fontFace.getSize(),
                                loadSizedFont(fontFace)))
                .get(fontFace.getFont());
    }

    private Font loadSizedFont(final FontFace fontFace) {
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
