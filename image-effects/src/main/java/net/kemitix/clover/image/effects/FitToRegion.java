package net.kemitix.clover.image.effects;

import net.kemitix.clover.spi.FontFace;
import net.kemitix.clover.spi.Region;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.awt.*;

/**
 * Fit the text to the region by finding the best font size and word wrapping to
 * fill the space.
 */
@ApplicationScoped
public class FitToRegion {

    @Inject WordWrapper wordWrapper;

    public FontFace fit(
            String text,
            FontFace fontFace,
            Graphics2D graphics2D,
            Region region
    ) {
        return fontFace;
    }
}
