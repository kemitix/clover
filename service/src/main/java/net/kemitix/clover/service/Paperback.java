package net.kemitix.clover.service;

import lombok.Getter;
import net.kemitix.clover.spi.*;
import net.kemitix.properties.typed.TypedProperties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class Paperback implements CloverFormat {

    @Inject
    IssueDimensions dimensions;
    @Inject
    Image coverArtImage;
    @Inject
    FrontCover frontCover;
    @Inject
    SpineBlock spineBlock;
    @Inject
    BackCover backCover;
    @Getter
    private List<Image> images;

    @PostConstruct
    public void init() {
        images = Collections.singletonList(
                rescale(dimensions.getScaleFromOriginal())
                        .andThen(crop(dimensions.getWrapCrop()))
                        .andThen(frontCover)
                        .andThen(spineBlock)
                        .andThen(backCover)
                        .apply(coverArtImage));
    }

    @Override
    public String getName() {
        return "paperback";
    }

    @Override
    public TypedProperties getImageProperties() {
        Region wrapCrop = dimensions.getWrapCrop();
        return TypedProperties.create()
                .with(PdfWidth.class, (int) wrapCrop.getWidth())
                .with(PdfHeight.class, (int) wrapCrop.getHeight());
    }

}
