package net.kemitix.cossmass.clover.images;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FontFace {

    private final File font;
    private final int size;
    private final String colour;


    public static FontFace of(
            final File font,
            final int size,
            final String colour
    ) {
        return new FontFace(font, size, colour);
    }
}
