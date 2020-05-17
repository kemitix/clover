package net.kemitix.clover.image.effects;

import net.kemitix.clover.spi.Area;
import net.kemitix.clover.spi.FontCache;
import net.kemitix.clover.spi.FontFace;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.awt.*;
import java.awt.geom.Rectangle2D;

@ApplicationScoped
public class FontMetrics {

    @Inject FontCache fontCache;

    public Area bounds(Graphics2D graphics2d, String text, FontFace fontFace) {
        Rectangle2D bounds = fontCache.loadFont(fontFace)
                .getStringBounds(text.strip(), graphics2d.getFontRenderContext());
        return Area.of((int) bounds.getWidth(), (int) bounds.getHeight());
    }
}
