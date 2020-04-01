package net.kemitix.cossmass.clover;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Area {

    private final int width;
    private final int height;

    public static Area of(final int width, final int height) {
        return Area.builder()
                .width(width)
                .height(height)
                .build();
    }
}
