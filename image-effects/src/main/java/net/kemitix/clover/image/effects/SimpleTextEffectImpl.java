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
public class SimpleTextEffectImpl
        extends AbstractTextEffect
        implements
        SimpleTextEffect<Graphics2D>,
        TextEffect.TextNext<Graphics2D>,
        TextEffect.RegionNext<Graphics2D>,
        TextEffect.HAlignNext<Graphics2D>,
        TextEffect.VAlignNext<Graphics2D>,
        TextEffect<Graphics2D>,
        Function<Graphics2D, Graphics2D> {

    @Inject @Getter FontCache fontCache;
    @Inject @Getter FontMetrics fontMetrics;
    @Inject WordWrapper wordWrapper;
    @Inject FitToRegion fitToRegion;

    @Getter Region region;

    VHAlignment vAlignment;
    HAlignment hAlignment;
    FontFace fontFace;
    String text;

    @Override
    public Graphics2D apply(Graphics2D graphics2D) {
        FontFace face = fitToRegion.fit(text, fontFace, graphics2D, region);
        String[] split =
                wordWrapper.wrap(text, face, graphics2D, region.getWidth());
        int top = topEdge(split.length * face.getSize());
        IntStream.range(0, split.length)
                .forEach(lineNumber -> {
                    String lineOfText = split[lineNumber];
                    if (lineOfText.length() > 0) {
                        drawText(graphics2D, lineNumber, lineOfText,
                                region.getArea(), face, top);
                    }
                });
        return graphics2D;
    }

    private void drawText(
            Graphics2D graphics2d,
            int lineCount,
            String line,
            Area imageArea,
            FontFace face,
            int topOffset
    ) {
        Area stringBounds = getStringBounds(graphics2d, line, face);
        int top = topOffset + ((int) stringBounds.getHeight() * lineCount);
        int left = lineLeftEdge((int) stringBounds.getWidth());
        AbstractTextEffect.drawText(line, framing -> XY.at(left, top),
                face, graphics2d, fontCache, imageArea);
        graphics2d.drawRect(left, top, (int) stringBounds.getWidth(), (int) stringBounds.getHeight());
    }

    private int topEdge(int height) {
        switch (vAlignment) {
            case TOP:
                return region.getTop();
            case BOTTOM:
                return region.getTop() + (region.getHeight() - height);
            case CENTRE:
                return region.getTop() + ((region.getHeight() - height) / 2);
        }
        throw new UnsupportedOperationException(
                "Unknown Vertical Alignment: " + vAlignment);
    }

    private int lineLeftEdge(int width) {
        switch (hAlignment) {
            case LEFT:
                return region.getLeft();
            case RIGHT:
                return region.getWidth() - width;
            case CENTRE:
                return region.getLeft() + ((region.getWidth() - width) / 2);
        }
        throw new UnsupportedOperationException(
                "Unknown Horizontal Alignment: " + hAlignment);
    }

    @Override
    public Function<Graphics2D, Graphics2D> text(String text) {
        return withText(text);
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
    public VAlignNext<Graphics2D> hAlign(VHAlignment VHAlignment) {
        return withVAlignment(VHAlignment);
    }

    @Override
    public TextNext<Graphics2D> vAlign(HAlignment hAlignment) {
        return withHAlignment(hAlignment);
    }
}
