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

    private IssueDimensions dimensions;
    private Image coverArtImage;
    private FrontCover frontCover;
    private SpineBlock spineBlock;
    private BackCover backCover;
    @Getter
    private List<Image> images;

    public Paperback() {
    }

    @Inject
    protected Paperback(
            final IssueDimensions dimensions,
            final Image coverArtImage,
            final FrontCover frontCover,
            final SpineBlock spineBlock,
            final BackCover backCover
    ) {
        this.dimensions = dimensions;
        this.coverArtImage = coverArtImage;
        this.frontCover = frontCover;
        this.spineBlock = spineBlock;
        this.backCover = backCover;
    }

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
