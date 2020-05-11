package net.kemitix.clover.images;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import net.kemitix.clover.spi.CenteredTextEffect;
import net.kemitix.clover.spi.FontCache;
import net.kemitix.clover.spi.images.*;
import net.kemitix.clover.spi.images.Image;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.util.function.Function;

@ApplicationScoped
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CentredTextEffectImpl
    extends AbstractTextEffect
        implements CenteredTextEffect,
        CenteredTextEffect.TextNext,
        CenteredTextEffect.RegionNext,
        Function<Image, Image> {

    private String text;
    @Getter
    private FontCache fontCache;
    @Getter
    private FontFace fontFace;
    @Getter
    private Region region;

    public CentredTextEffectImpl() {
    }

    @Inject
    public CentredTextEffectImpl(FontCache fontCache) {
        this.fontCache = fontCache;
    }

    @Override
    public Image apply(Image image) {
        return image.withGraphics(graphics2d -> {
            int lineCount = 0;
            for (String line : text.split("\n")) {
                drawText(image, graphics2d, lineCount, line);
                lineCount++;
            }
        });
    }

    private void drawText(Image image, Graphics2D graphics2d, int lineCount, String line) {
        Rectangle2D stringBounds = getStringBounds(graphics2d, line);
        int top = region.getTop() + ((int) stringBounds.getHeight() * lineCount);
        int left = region.getLeft() + ((region.getWidth() - (int) stringBounds.getWidth()) / 2);
        CloverImage.drawText(line, framing -> XY.at(left, top),
                fontFace, graphics2d, fontCache, image.getBufferedImage());
    }

    @Override
    public Function<Image, Image> text(String text) {
        return toBuilder().text(text).build();
    }

    @Override
    public RegionNext fontFace(FontFace fontFace) {
        return toBuilder().fontFace(fontFace).build();
    }

    @Override
    public TextNext region(Region region) {
        return toBuilder().region(region).build();
    }

}
