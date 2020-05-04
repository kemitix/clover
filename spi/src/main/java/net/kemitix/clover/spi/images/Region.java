package net.kemitix.clover.spi.images;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class Region {

    @Builder.Default
    private final float top = 0;
    @Builder.Default
    private final float left = 0;
    private final float width;
    private final float height;

    public float getRight() {
        return getLeft() + getWidth();
    }

    public float getBottom() {
        return getTop() + getHeight();
    }

    public void mustContain(final Region inner) {
        if (inner.getWidth() > getWidth()) {
            notContains("is wider than", inner);
        }
        if (inner.getHeight() > getHeight()) {
            notContains("is taller than", inner);
        }
        if (!isBetween(inner.getLeft(), getLeft(), getRight())) {
            notContains("left edge is outside", inner);
        }
        if (!isBetween(inner.getRight(), getLeft(), getRight())) {
            notContains("right edge is outside", inner);
        }
        if (!isBetween(inner.getTop(), getTop(), getBottom())) {
            notContains("top edge is outside", inner);
        }
        if (!isBetween(inner.getBottom(), getTop(), getBottom())) {
            notContains("bottom edge is outside", inner);
        }
    }

    private void notContains(final String message, final Region inner) {
        throw new IllegalArgumentException(String.format(
                "Inner %s container:\n" +
                        " Container: %s - right=%f, bottom=%f\n" +
                        "     Inner: %s - right=%f, bottom=%f",
                message,
                this, getRight(), getBottom(),
                inner, inner.getRight(), inner.getBottom()));
    }

    private boolean isBetween(
            final float subject,
            final float lower,
            final float upper
    ) {
        return lower <= subject && subject <= upper;
    }

    @Override
    public String toString() {
        return "Region{" +
                "(left=" + left +
                ", right=" + getRight() +
                "), (top=" + top +
                ", bottom=" + getBottom() +
                "), width=" + width +
                ", height=" + height +
                '}';
    }
}
