package net.kemitix.clover.spi.images;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Region {

    @Builder.Default
    private final int top = 0;
    @Builder.Default
    private final int left = 0;
    private final int width;
    private final int height;

    public int getRight() {
        return getLeft() + getWidth();
    }

    public int getBottom() {
        return getTop() + getWidth();
    }

    public boolean contains(final Region inner) {
        return isBetween(inner.getLeft(), getLeft(), getRight()) &&
                isBetween(inner.getRight(), getLeft(), getRight()) &&
                isBetween(inner.getTop(), getTop(), getBottom()) &&
                isBetween(inner.getBottom(), getTop(), getBottom());
    }

    private boolean isBetween(
            final int subject,
            final int lower,
            final int upper
    ) {
        return lower <= subject && subject <= upper;
    }
}
