package net.kemitix.clover.images;

import lombok.Builder;
import net.kemitix.clover.spi.CenteredTextEffect;
import net.kemitix.clover.spi.images.FontFace;
import net.kemitix.clover.spi.images.Image;
import net.kemitix.clover.spi.images.Region;

import java.util.function.Function;

@Builder(toBuilder = true)
public class CentredTextEffectImpl
        implements CenteredTextEffect,
        CenteredTextEffect.FontFaceNext,
        CenteredTextEffect.RegionNext,
        Function<Image, Image> {

    private final String text;
    private final FontFace fontFace;
    private final Region region;

    @Override
    public FontFaceNext text(String text) {
        return toBuilder().text(text).build();
    }

    @Override
    public RegionNext fontFace(FontFace fontFace) {
        return toBuilder().fontFace(fontFace).build();
    }

    @Override
    public Function<Image, Image> region(Region region) {
        return toBuilder().region(region).build();
    }

    @Override
    public Image apply(Image image) {
        //TODO
        return image;
    }
}
