package net.kemitix.clover.images;

import net.kemitix.clover.spi.FatalCloverError;
import net.kemitix.clover.spi.FontCache;
import net.kemitix.clover.spi.images.*;
import org.beryx.awt.color.ColorFactory;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Optional;
import java.util.function.Function;

abstract class AbstractTextEffect {

    @Deprecated
    public static void drawText(
            final String text,
            final Function<Framing, XY> positioning,
            final FontFace fontFace,
            final Graphics2D graphics,
            final FontCache fontCache,
            final BufferedImage image
    ) {
        final Font font = fontCache.loadFont(fontFace);
        graphics.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
        graphics.setFont(font);
        final Rectangle2D stringBounds =
                font.getStringBounds(text, graphics.getFontRenderContext());
        final XY topLeft = positioning.apply(Framing.builder()
                .outer(Area.of(image.getWidth(), image.getHeight()))
                .inner(Area.of(((int) stringBounds.getWidth()), ((int) stringBounds.getHeight())))
                .build());
        // Drop Shadow
        final XY shadowOffset = fontFace.getShadowOffset();
        if (shadowOffset.getX() != 0 || shadowOffset.getY() != 0) {
            graphics.setPaint(getColor(fontFace.getShadowColour()));
            graphics.drawString(text,
                    topLeft.getX() + shadowOffset.getX(),
                    (int) (topLeft.getY() - stringBounds.getY() + shadowOffset.getY()));
        }
        // Text
        graphics.setPaint(getColor(fontFace.getColour()));
        graphics.drawString(text,
                topLeft.getX(),
                (int) (topLeft.getY() - stringBounds.getY()));
//        if (config.drawBoundingBoxes) {
//            graphics.setPaint(getColor("red"));
//            graphics.drawRect(topLeft.getX(), topLeft.getY(), ((int) stringBounds.getWidth()), ((int) stringBounds.getHeight()));
//        }
    }

    static Color getColor(final String colour) {
        return Optional.ofNullable(
                ColorFactory.valueOf(colour))
                .orElseThrow(() ->
                        new FatalCloverError(
                                "Unknown colour: " + colour));
    }

    private Font getFont() {
        return getFontCache().loadFont(getFontFace());
    }

    protected abstract FontFace getFontFace();

    protected abstract FontCache getFontCache();

    protected Rectangle2D getStringBounds(Graphics2D graphics2d, String text) {
        Font font = getFont();
        FontRenderContext fontRenderContext = graphics2d.getFontRenderContext();
        Rectangle2D stringBounds = font.getStringBounds(text, fontRenderContext);
        getRegion().mustContain(stringBounds);
        return stringBounds;
    }

    protected abstract Region getRegion();
}
