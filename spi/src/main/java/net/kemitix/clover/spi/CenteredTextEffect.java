package net.kemitix.clover.spi;

import net.kemitix.clover.spi.images.FontFace;
import net.kemitix.clover.spi.images.Image;
import net.kemitix.clover.spi.images.Region;

import java.util.function.Function;

public interface CenteredTextEffect {
    FontFaceNext text(String text);

    interface FontFaceNext {
        RegionNext fontFace(FontFace fontFace);
    }

    interface RegionNext {
        Function<Image, Image> region(Region region);
    }
}
