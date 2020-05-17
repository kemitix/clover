package net.kemitix.clover.image.effects;

import lombok.*;
import net.kemitix.clover.spi.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.awt.*;
import java.util.function.Function;
import java.util.stream.IntStream;

@ApplicationScoped
@With
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class RightAlignTextEffectImpl
        extends AbstractTextEffect
        implements RightAlignTextEffect<Graphics2D>,
        TextEffect.RegionNext<Graphics2D>,
        TextEffect.TextNext<Graphics2D>,
        Function<Graphics2D, Graphics2D>, TextEffect.HAlignNext<Graphics2D>, TextEffect.VAlignNext<Graphics2D> {

    private String text;
    @Getter private FontFace fontFace;
    @Getter private Region region;
    HAlignment hAlignment;
    VAlignment vAlignment;

    @Inject @Getter FontCache fontCache;
    @Inject @Getter
    FontMetrics fontMetrics;

    @Override
    public Graphics2D apply(Graphics2D graphics2D) {
        String[] split = text.split("\n");
        IntStream.range(0, split.length)
                .forEach(lineNumber -> {
                    String lineOfText = split[lineNumber];
                    if (lineOfText.length() > 0) {
                        drawText(graphics2D, lineNumber, lineOfText, region.getArea());
                    }
                });
        return graphics2D;
    }

    private void drawText(
            Graphics2D graphics2D,
            int lineCount,
            String line,
            Area area
    ) {
        Area stringBounds = getStringBounds(graphics2D, line, fontFace);
        int top = region.getTop() + ((int) stringBounds.getHeight() * lineCount);
        int left = region.getRight() - (int) stringBounds.getWidth();
        AbstractTextEffect.drawText(line, framing -> XY.at(left, top),
                fontFace, graphics2D, fontCache, area);
    }

    @Override
    public RegionNext<Graphics2D> fontFace(FontFace fontFace) {
        return withFontFace(fontFace);
    }

    @Override
    public HAlignNext<Graphics2D> region(Region region) {
        return withRegion(region);
    }

    @Override
    public Function<Graphics2D, Graphics2D> text(String text) {
        return withText(text);
    }

    @Override
    public VAlignNext<Graphics2D> hAlign(HAlignment hAlignment) {
        return withHAlignment(hAlignment);
    }

    @Override
    public TextNext<Graphics2D> vAlign(VAlignment vAlignment) {
        return withVAlignment(vAlignment);
    }
}
