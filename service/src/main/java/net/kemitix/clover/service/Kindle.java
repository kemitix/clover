package net.kemitix.clover.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import net.kemitix.clover.spi.CloverFormat;
import net.kemitix.clover.spi.CloverProperties;
import net.kemitix.clover.spi.Image;
import net.kemitix.clover.spi.IssueDimensions;
import net.kemitix.properties.typed.TypedProperties;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class Kindle implements CloverFormat {

    @Inject IssueDimensions dimensions;
    @Inject Paperback paperback;
    @Inject CloverProperties cloverProperties;

    @Override
    public List<Image> getImages() {
        return paperback.getImages().stream()
                .map(image -> image.crop(dimensions.getFrontCrop()))
                .collect(Collectors.toList());
    }

    @Override
    public String getName() {
        return "kindle";
    }

    @Override
    public TypedProperties getImageProperties() {
        return TypedProperties.create();
    }

    @Override
    public boolean isEnabled() {
        return cloverProperties.enableKindle();
    }
}
