package net.kemitix.clover.image.effects;

import lombok.*;
import net.kemitix.clover.spi.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.function.Function;
import java.util.stream.IntStream;

@With
@ApplicationScoped
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RotatedCenteredTextEffectImpl
        extends AbstractTextEffect
        implements RotatedCenteredTextEffect<Graphics2D>,
        TextEffect.RegionNext<Graphics2D>,
        TextEffect.TextNext<Graphics2D>,
        TextEffect.HAlignNext<Graphics2D>,
        TextEffect.VAlignNext<Graphics2D>,
        Function<Graphics2D, Graphics2D> {

    @Inject @Getter FontCache fontCache;
    @Inject @Getter FontMetrics fontMetrics;
    VAlignment VAlignment;
    HAlignment HAlignment;

    @Getter FontFace fontFace;
    @Getter Region region;
    @Getter String text;

    @Override
    public Graphics2D apply(Graphics2D graphics2d) {
        graphics2d.setTransform(
                AffineTransform.getQuadrantRotateInstance(1));
        String[] split = text.split("\n");
        IntStream.range(0, split.length)
                .forEach(lineNumber -> {
                    String lineOfText = split[lineNumber];
                    if (lineOfText.length() > 0) {
                        drawText(graphics2d, lineNumber, lineOfText,
                                region.getArea().rotateCW(),
                                region
                                        .rotateCW()
                                        .flipVertically(0)
                        );
                    }
                });
        return graphics2d;
    }

    private void drawText(
            Graphics2D graphics2d,
            int lineCount,
            String line,
            Area imageArea,
            Region region
    ) {
        Area stringBounds = getStringBounds(graphics2d, line, fontFace);
        int top = region.getTop() + ((int) stringBounds.getHeight() * lineCount) - region.getHeight();
        int left = region.getWidth() + region.getLeft() + ((region.getWidth() - (int) stringBounds.getWidth()) / 2);
        AbstractTextEffect.drawText(line, framing -> XY.at(left, top),
                fontFace, graphics2d, fontCache, imageArea);
    }

    @Override
    public TextNext<Graphics2D> fontFace(FontFace fontFace) {
        return withFontFace(fontFace);
    }

    @Override
    public Function<Graphics2D, Graphics2D> region(Region region) {
        return withRegion(region);
    }

    @Override
    public VAlignNext<Graphics2D> text(String text) {
        return withText(text);
    }

    @Override
    public HAlignNext<Graphics2D> vAlign(VAlignment VAlignment) {
        return withVAlignment(VAlignment);
    }

    @Override
    public RegionNext<Graphics2D> hAlign(HAlignment HAlignment) {
        return withHAlignment(HAlignment);
    }
}
