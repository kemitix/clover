package net.kemitix.clover.service;

import net.kemitix.clover.spi.images.Image;
import net.kemitix.properties.typed.TypedProperties;

import java.util.List;

public interface CloverFormat {
    List<Image> getImages();

    String getName();

    TypedProperties getImageProperties();
}
