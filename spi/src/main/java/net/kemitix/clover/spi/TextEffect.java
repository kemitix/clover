package net.kemitix.clover.spi;

import java.util.function.Function;

public interface TextEffect {

    RegionNext fontFace(FontFace fontFace);

    interface RegionNext {
        TextNext region(Region region);
    }

    interface TextNext {
        Function<Image, Image> text(String text);
    }

}
