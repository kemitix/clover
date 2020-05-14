package net.kemitix.clover.service;

import net.kemitix.clover.spi.*;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.function.Function;
import java.util.logging.Logger;

@ApplicationScoped
public class SpineBlock implements Function<Image, Image> {

    private static final Logger LOGGER =
            Logger.getLogger(
                    SpineBlock.class.getName());

    @Inject
    CloverProperties cloverProperties;
    @Inject
    IssueConfig issueConfig;
    @Inject
    IssueDimensions dimensions;
    @Inject
    RotatedCenteredTextEffect<Image> rotatedCenteredTextEffect;

    @Override
    public Image apply(Image image) {
        LOGGER.info("Drawing Spine...");
        int fontSize = 62;
        final FontFace fontFace =
                FontFace.of(
                        cloverProperties.getFontFile(),
                        fontSize,
                        "yellow",
                        XY.at(
                                cloverProperties.getDropShadowXOffset(),
                                cloverProperties.getDropShadowYOffset()));
        final String spineText = String.format(
                "%s - Issue %s - %s",
                issueConfig.getPublicationTitle(),
                issueConfig.getIssue(),
                issueConfig.getDate());
//        return image.withFilledArea(
//                dimensions.getSpineCrop(),
//                "black"
//        ).withRotatedCenteredText(
//                spineText,
//                dimensions.getSpineCrop(),
//                fontFace);
        return rotatedCenteredTextEffect.fontFace(fontFace)
                .region(dimensions.getSpineCrop().withOffset(0, (int) (-fontSize * 0.8)))
                .text(spineText)
                .apply(image.withFilledArea(
                        dimensions.getSpineCrop(), "black"));
    }
}
