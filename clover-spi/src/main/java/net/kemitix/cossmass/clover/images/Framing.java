package net.kemitix.cossmass.clover.images;

import lombok.Builder;
import lombok.Getter;
import net.kemitix.cossmass.clover.Area;

@Getter
@Builder
public class Framing {
    private final Area outer;
    private final Area inner;

    public static Framing of(final Area outer, final Area inner) {
        return Framing.builder()
                .outer(outer)
                .inner(inner)
                .build();
    }

    public XY centered() {
        if (inner.getHeight() > outer.getHeight()) {
            throw new IllegalArgumentException("Inner is taller than outer");
        }
        if (inner.getWidth() > outer.getWidth()) {
            throw new IllegalArgumentException("Inner is wider than outer");
        }
        final int x = (outer.getWidth() - inner.getWidth()) / 2;
        final int y = (outer.getHeight() - inner.getHeight()) / 2;
        return XY.at(x, y);
    }
}
