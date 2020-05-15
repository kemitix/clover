package net.kemitix.clover.spi;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class FontFaceImpl implements FontFace {

    private final File font;
    private final int size;
    private final String colour;
    private final String shadowColour;
    private final XY shadowOffset;

    static String shadowColour(final String colour) {
        switch (colour) {
            case "white":
            case "yellow":
                return "black";
            default:
                return "white";
        }
    }
}
