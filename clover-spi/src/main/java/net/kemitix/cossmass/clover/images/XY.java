package net.kemitix.cossmass.clover.images;

import lombok.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public class XY {

    private final int x;
    private final int y;

    public static XY at(final int x, final int y) {
        return new XY(x, y);
    }

}
