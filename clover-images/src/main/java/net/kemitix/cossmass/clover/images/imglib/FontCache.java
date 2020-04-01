package net.kemitix.cossmass.clover.images.imglib;

import net.kemitix.cossmass.clover.images.FontFace;

import javax.enterprise.context.Dependent;
import java.awt.*;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Dependent
public class FontCache {

    private static final Logger LOGGER =
            Logger.getLogger(
                    FontCache.class.getName());

    private final Map<File, Map<Integer, Font>> cache = new HashMap<>();

    private final FontLoader fontLoader;

    public FontCache(final FontLoader fontLoader) {
        this.fontLoader = fontLoader;
    }

    public Font loadFont(final FontFace fontFace) {
        LOGGER.info(String.format("Requesting %s %d",
                fontFace.getFont(), fontFace.getSize()));
        return cache.computeIfAbsent(
                fontFace.getFont(),
                file ->
                        Collections.singletonMap(
                                fontFace.getSize(),
                                fontLoader.loadFont(fontFace)))
                .get(fontFace.getFont());
    }
}
