package net.kemitix.cossmass.clover.images;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class XYPosition {

    private final int x;
    private final int y;

    public static XYPosition at(final int x, final int y) {
        return new XYPosition(x, y);
    }

}
