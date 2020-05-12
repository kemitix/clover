package net.kemitix.clover.images;

import lombok.*;
import net.kemitix.clover.spi.FontCache;
import net.kemitix.clover.spi.SimpleTextEffect;
import net.kemitix.clover.spi.TextEffect;
import net.kemitix.clover.spi.images.FontFace;
import net.kemitix.clover.spi.images.Image;
import net.kemitix.clover.spi.images.Region;
import net.kemitix.clover.spi.images.XY;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.awt.*;
import java.util.function.Function;
import java.util.stream.IntStream;

@With
@ApplicationScoped
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SimpleTextEffectImpl
        extends AbstractTextEffect
        implements SimpleTextEffect,
        TextEffect.RegionNext,
        TextEffect.TextNext,
        Function<Image, Image> {

    @Inject
    @Getter FontCache fontCache;
    @Getter FontFace fontFace;
    @Getter Region region;
    @Getter String text;

    @Override
    public Image apply(Image image) {
        return image.withGraphics(graphics2D -> {
            String[] split = text.split("\n");
            IntStream.range(0, split.length)
                    .forEach(i -> drawText(image, graphics2D, i, split[i]));
        });
    }

    private void drawText(Image image, Graphics2D graphics2D, int lineLumber, String line) {
        AbstractTextEffect.drawText(text, framing ->
                        XY.at(region.getLeft(), region.getTop()),
                fontFace, graphics2D, fontCache, image.getBufferedImage());
    }

    @Override
    public RegionNext fontFace(FontFace fontFace) {
        return withFontFace(fontFace);
    }

    @Override
    public TextNext region(Region region) {
        return withRegion(region);
    }

    @Override
    public Function<Image, Image> text(String text) {
        return withText(text);
    }
}
