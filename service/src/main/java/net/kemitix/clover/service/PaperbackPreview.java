package net.kemitix.clover.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import net.kemitix.clover.spi.CloverFormat;
import net.kemitix.clover.spi.CloverProperties;
import net.kemitix.clover.spi.Drawable;
import net.kemitix.clover.spi.Element;
import net.kemitix.clover.spi.GuideLines;
import net.kemitix.clover.spi.Image;
import net.kemitix.properties.typed.TypedProperties;

import java.awt.*;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@ApplicationScoped
public class PaperbackPreview implements CloverFormat {

    @Inject CloverProperties cloverProperties;
    @Inject Paperback paperback;
    @GuideLines @Inject Instance<Element<Graphics2D>> blocks;

    @Override
    public List<Image> getImages() {
        return paperback.getImages()
                .stream()
                .map(blocks())
                .collect(Collectors.toList());
    }

    @Override
    public String getName() {
        return paperback.getName() + "-preview";
    }

    @Override
    public TypedProperties getImageProperties() {
        return paperback.getImageProperties();
    }

    @Override
    public boolean isEnabled() {
        return cloverProperties.enablePaperbackPreview();
    }
    private Function<Image, Image> blocks() {
        var properties = TypedProperties.create();
        return image ->
                image.withGraphics(graphics2D ->
                        Drawable.draw(blocks.stream(), graphics2D, properties));
    }

}
