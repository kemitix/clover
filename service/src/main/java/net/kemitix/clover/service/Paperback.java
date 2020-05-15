package net.kemitix.clover.service;

import lombok.Getter;
import net.kemitix.clover.spi.*;
import net.kemitix.clover.spi.Image;
import net.kemitix.properties.typed.TypedProperties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.awt.*;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Logger;

@ApplicationScoped
public class Paperback implements CloverFormat {

    private static final Logger LOG =
            Logger.getLogger(
                    Paperback.class.getName());

    @Inject
    IssueDimensions dimensions;
    @Inject
    Image coverArtImage;
    @Inject
    FrontCover frontCover;
    @Inject
    Instance<Block<Graphics2D>> blocks;
    @Getter
    private List<Image> images;

    @PostConstruct
    public void init() {
        images = Collections.singletonList(
                rescale(dimensions.getScaleFromOriginal())
                        .andThen(crop(dimensions.getWrapCrop()))
                        .andThen(frontCover)
                        .andThen(blocks())
                        .apply(coverArtImage));
    }

    private Function<Image, Image> blocks() {
        return image ->
                image.withGraphics(graphics2D ->
                {
                    LOG.info("Draw blocks " + blocks.toString());
                    blocks.stream()
                            .forEach(block -> {
                                LOG.info("Block: " + block);
                                block.draw(graphics2D);
                            });
                });
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
