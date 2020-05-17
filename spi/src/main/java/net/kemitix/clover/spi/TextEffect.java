package net.kemitix.clover.spi;

import java.util.function.Function;

public interface TextEffect<T> {

    RegionNext<T> fontFace(FontFace fontFace);

    interface RegionNext<T> {
        HAlignNext<T> region(Region region);
    }

    interface HAlignNext<T> {
        VAlignNext<T> hAlign(HAlignment hAlignment);
    }

    interface VAlignNext<T> {
        TextNext<T> vAlign(VAlignment vAlignment);
    }

    interface TextNext<T> {
        Function<T, T> text(String text);
    }

    enum VAlignment {
        LEFT, RIGHT, CENTRE
    }

    enum HAlignment {
        TOP, BOTTOM, CENTRE
    }

}
