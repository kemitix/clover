package net.kemitix.clover.service;

import net.kemitix.clover.spi.CenteredTextEffect;
import net.kemitix.clover.spi.CloverProperties;
import net.kemitix.clover.spi.images.FontFace;
import net.kemitix.clover.spi.images.Image;
import net.kemitix.clover.spi.images.Region;
import net.kemitix.clover.spi.images.XY;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.function.Function;

@ApplicationScoped
public class StoryCardBranding implements Function<Image, Image> {

    private CloverProperties cloverProperties;
    private IssueConfig issueConfig;
    private CenteredTextEffect centeredText;
    private Dimensions dimensions;

    public StoryCardBranding() {
    }

    @Inject
    public StoryCardBranding(
            CloverProperties cloverProperties,
            IssueConfig issueConfig,
            CenteredTextEffect centeredText,
            Dimensions dimensions
    ) {
        this.cloverProperties = cloverProperties;
        this.issueConfig = issueConfig;
        this.centeredText = centeredText;
        this.dimensions = dimensions;
    }

    @Override
    public Image apply(Image image) {
        final FontFace fontFace = getPublicationFontFace();
        var text = String.join("\n",
                issueConfig.getPublicationTitle().split(" "));
        System.out.println("dimensions.getFrontCrop() = " + dimensions.getFrontCrop());
        Region wrapCrop = dimensions.getWrapCrop();
        System.out.println("wrapCrop = " + wrapCrop);
        int height = wrapCrop.getHeight();
        int offset = (int) (height * 0.6);
        return centeredText.fontFace(fontFace)
                .region(wrapCrop.toBuilder()
                        .top(wrapCrop.getTop() + offset)
                        .height(height - offset)
                        .build())
                .text(text)
                .apply(image);
    }

    private FontFace getPublicationFontFace() {
        return FontFace.of(
                cloverProperties.getFontFile(),
                217,
                issueConfig.getTitleColour(),
                XY.at(
                        cloverProperties.getDropShadowXOffset(),
                        cloverProperties.getDropShadowYOffset()));
    }
}
