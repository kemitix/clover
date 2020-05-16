package net.kemitix.clover.service;

import net.kemitix.clover.spi.*;
import net.kemitix.clover.spi.FontFace;
import net.kemitix.clover.spi.Image;
import net.kemitix.clover.spi.Region;
import net.kemitix.clover.spi.XY;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.awt.*;
import java.util.function.Function;
import java.util.logging.Logger;

@ApplicationScoped
public class FrontCoverBlock implements Function<Image, Image> {

    private static final Logger LOGGER =
            Logger.getLogger(
                    FrontCoverBlock.class.getName());

    @Inject CloverProperties cloverProperties;
    @Inject IssueConfig issueConfig;
    @Inject IssueDimensions dimensions;
    @Inject RightAlignTextEffect<Image> rightAlignText;
    @Inject SimpleTextEffect<Image> simpleTextEffect;
    @Inject @FrontCover Instance<Element<Graphics2D>> elements;

    @Override
    public Image apply(Image image) {
        return drawSubTitles()
                .andThen(elements())
                .apply(image);
    }

    private Function<Image, Image> elements() {
        return image ->
                image.withGraphics(graphics2D ->
                        Drawable.draw(elements, graphics2D));
    }

    private int frontLeftEdge() {
        return dimensions.getFrontCrop().getLeft();
    }

    private Function<Image, Image> drawSubTitles() {
        final FontFace fontFace =
                FontFace.of(
                        cloverProperties.getFontFile(),
                        48,
                        issueConfig.getSubTitleColour(),
                        XY.at(
                                cloverProperties.getDropShadowXOffset(),
                                cloverProperties.getDropShadowYOffset()));
        return image -> {
            LOGGER.info("Drawing subtitle...");
            return drawDateSubtitle(fontFace)
                    .andThen(drawBannerSubtitle(fontFace))
                    .andThen(issueNumber(fontFace))
                    .apply(image);
        };
    }

    private Function<Image, Image> issueNumber(FontFace fontFace) {
        return image -> {
            LOGGER.info("issueNumber");
            return simpleTextEffect.fontFace(fontFace)
                    .region(Region.builder()
                            .top(475).left(85 + frontLeftEdge())
                            .width((int) image.getArea().getWidth() - (85 + frontLeftEdge()))
                            .height((int) image.getArea().getHeight() - 475)
                            .build())
                    .text(String.format("Issue %s", issueConfig.getIssue()))
                    .apply(image);
        };
    }

    private Function<Image, Image> drawBannerSubtitle(FontFace fontFace) {
        return image ->
                rightAlignText.fontFace(fontFace)
                        .region(dimensions.getFrontCrop().toBuilder()
                                .top(10).build().withPadding(85))
                        .text("Science Fiction and Fantasy")
                        .apply(image);
    }

    private Function<Image, Image> drawDateSubtitle(FontFace fontFace) {
        return rightAlignText.fontFace(fontFace)
                .region(Region.builder()
                        .top(390).left(frontLeftEdge())
                        .width(dimensions.getFrontCrop().getWidth())
                        .height(dimensions.getFrontCrop().getHeight())
                        .build().withPadding(85))
                .text(issueConfig.getDate());
    }

    private Function<Image, Image> drawAuthors() {
        final FontFace fontFace =
                FontFace.of(
                        cloverProperties.getFontFile(),
                        48,
                        issueConfig.getTextColour(),
                        XY.at(
                                cloverProperties.getDropShadowXOffset(),
                                cloverProperties.getDropShadowYOffset()));
        return image -> {
            LOGGER.info("Drawing authors...");
            int top = issueConfig.getAuthorsYOffset();
            int left = issueConfig.getAuthorsXOffset() + frontLeftEdge();
            return simpleTextEffect.fontFace(fontFace)
                    .region(Region.builder()
                            .top(top).left(left)
                            .width(image.getRegion().getWidth() - left)
                            .height(image.getRegion().getHeight() - top)
                            .build())
                    .text(String.join("\n", issueConfig.authors()))
                    .apply(image);
        };
    }
}
