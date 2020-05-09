package net.kemitix.clover.service;

import lombok.Getter;
import net.kemitix.clover.spi.CloverProperties;
import net.kemitix.clover.spi.PdfHeight;
import net.kemitix.clover.spi.PdfWidth;
import net.kemitix.clover.spi.images.*;
import net.kemitix.properties.typed.TypedProperties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.function.Function;
import java.util.logging.Logger;

@ApplicationScoped
public class Paperback implements CloverFormat {

    private static final Logger LOGGER =
            Logger.getLogger(
                    Paperback.class.getName());

    private CloverProperties cloverProperties;
    private IssueConfig issueConfig;
    private Dimensions dimensions;
    private Image coverArtImage;
    private StoryListFormatter storyListFormatter;
    private FrontCover frontCover;
    private Spine spine;
    @Getter
    private Image image;

    public Paperback() {
    }

    @Inject
    protected Paperback(
            final CloverProperties cloverProperties,
            final IssueConfig issueConfig,
            final Dimensions dimensions,
            final Image coverArtImage,
            final StoryListFormatter storyListFormatter,
            final FrontCover frontCover,
            final Spine spine
    ) {
        this.cloverProperties = cloverProperties;
        this.issueConfig = issueConfig;
        this.dimensions = dimensions;
        this.coverArtImage = coverArtImage;
        this.storyListFormatter = storyListFormatter;
        this.frontCover = frontCover;
        this.spine = spine;
    }

    @PostConstruct
    public void init() {
        image = rescale(dimensions.getScaleFromOriginal())
                .andThen(crop(dimensions.getWrapCrop()))
                .andThen(frontCover)
                .andThen(spine)
                .andThen(backCover())
                .apply(coverArtImage);
    }

    private Function<Image, Image> crop(Region cropRegion) {
        return image -> image.crop(cropRegion);
    }

    private Function<Image, Image> rescale(float factor) {
        return image -> image.rescale(factor);
    }

    @Override
    public String getName() {
        return "paperback";
    }

    protected Function<Image, Image> backCover() {
        final FontFace fontFace =
                FontFace.of(
                        cloverProperties.getFontFile(),
                        48,
                        issueConfig.getTextColour(),
                        XY.at(
                                cloverProperties.getDropShadowXOffset(),
                                cloverProperties.getDropShadowYOffset()));
        return drawSFStories(fontFace)
                .andThen(drawFantasyStories(fontFace))
                .andThen(drawReprintStories(fontFace));
    }

    private Function<Image, Image> drawSFStories(final FontFace fontFace) {
        return image -> {
            LOGGER.info("Drawing SF Stories...");
            return image
                    .withText(
                            storyListFormatter.format(
                                    "Science Fiction Stories",
                                    issueConfig.getSfStories()),
                            XY.at(150, 200),
                            fontFace);
        };
    }

    private Function<Image, Image> drawFantasyStories(final FontFace fontFace) {
        return image -> {
            LOGGER.info("Drawing Fantasy Stories...");
            return image
                    .withText(
                            storyListFormatter.format(
                                    "Fantasy Stories",
                                    issueConfig.getFantasyStories()),
                            XY.at(500, 1100),
                            fontFace);
        };
    }

    private Function<Image, Image> drawReprintStories(final FontFace fontFace) {
        return image -> {
            LOGGER.info("Drawing Reprint Stories...");
            return image
                    .withText(
                            storyListFormatter.format(
                                    "Plus",
                                    issueConfig.getReprintStories()),
                            XY.at(150, 1800),
                            fontFace);
        };
    }

    @Override
    public TypedProperties getImageProperties() {
        Region wrapCrop = dimensions.getWrapCrop();
        return TypedProperties.create()
                .with(PdfWidth.class, (int) wrapCrop.getWidth())
                .with(PdfHeight.class, (int) wrapCrop.getHeight());
    }

}
