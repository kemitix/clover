package net.kemitix.clover.spi;

import lombok.*;

import javax.enterprise.inject.Vetoed;
import java.io.File;

@Vetoed
@Getter
@With
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class FontFaceImpl implements FontFace {

    private File font;
    private int size;
    private String colour;
    private String shadowColour;
    private XY shadowOffset;

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
