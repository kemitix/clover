package net.kemitix.cossmass.clover.images;

import net.kemitix.cossmass.clover.Area;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;

public interface Image {
    Image scaleToCover(Area area);

    Image crop(XY cropOffset, Area area);

    default Image apply(final Function<Image, Image> function) {
        return function.apply(this);
    }

    void write(Path path, String name);

    Image withText(
            String title,
            XY xy,
            FontFace fontFace);

    Image withText(
            List<String> title,
            XY xy,
            FontFace fontFace);

    Image rescale(float scale);
}
