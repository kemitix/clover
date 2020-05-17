package net.kemitix.clover.spi;

import java.util.function.Function;

public interface TextEffect<T> extends Effect<T> {

    RegionNext<T> fontFace(FontFace fontFace);

    interface RegionNext<T> {
        HAlignNext<T> region(Region region);
    }

    interface HAlignNext<T> {
        VAlignNext<T> hAlign(VHAlignment VHAlignment);
    }

    interface VAlignNext<T> {
        TextNext<T> vAlign(HAlignment hAlignment);
    }

    interface TextNext<T> {
        Function<T, T> text(String text);
    }

    enum HAlignment {
        LEFT, RIGHT, CENTRE
    }

    enum VHAlignment {
        TOP, BOTTOM, CENTRE
    }

}
