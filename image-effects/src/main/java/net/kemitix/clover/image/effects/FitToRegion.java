package net.kemitix.clover.image.effects;

import net.kemitix.clover.spi.Area;
import net.kemitix.clover.spi.FontCache;
import net.kemitix.clover.spi.FontFace;
import net.kemitix.clover.spi.Region;
import net.kemitix.text.fit.WordWrapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Fit the text to the region by finding the best font size and word wrapping to
 * fill the space.
 */
@ApplicationScoped
public class FitToRegion {

    @Inject WordWrapper wordWrapper;
    @Inject FontMetrics fontMetrics;
    @Inject FontCache fontCache;

    public FontFace fit(
            String text,
            FontFace fontFace,
            Graphics2D graphics2D,
            Region region
    ) {
        return fitMinMax(0, region.getHeight(), text, fontFace, graphics2D, region);
    }

    private FontFace fitMinMax(
            int min,
            int max,
            String text,
            FontFace fontFace,
            Graphics2D graphics2D,
            Region region
    ) {
        int mid = (max + min) / 2;
        FontFace face = fontFace.withSize(mid);
        if (mid == min || mid == max) {
            return fontFace.withSize(min);
        }
        List<String> lines =
                wordWrapper.wrap(text, fontCache.loadFont(face), graphics2D, region.getWidth());
        List<Area> lineSizes = lines.stream()
                .map(line -> fontMetrics.bounds(graphics2D, line, face))
                .collect(Collectors.toList());
        int linesHeight = lineSizes.stream().map(Area::getHeight)
                .mapToInt(Float::intValue).sum();
        int maxLineWidth = lineSizes.stream().map(Area::getWidth)
                .mapToInt(Float::intValue).max().orElse(0);
        int maxHeight = region.getHeight();
        int maxWidth = region.getWidth();
        if (linesHeight > maxHeight || maxLineWidth > maxWidth) {
            return fitMinMax(min, mid, text, fontFace, graphics2D, region);
        }
        if (linesHeight < maxHeight || maxLineWidth < maxWidth) {
            return fitMinMax(mid, max, text, fontFace, graphics2D, region);
        }
        return face;
    }
}
