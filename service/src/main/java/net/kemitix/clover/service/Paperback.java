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

    private IssueConfig issueConfig;
    private CloverProperties cloverProperties;
    private StoryListFormatter storyListFormatter;

    @Getter
    private Region frontCoverCropRegion;
    @Getter
    private Region fullCoverCropRegion;
    @Getter
    private Image coverArtImage;
    @Getter
    private Image image;

    public Paperback() {
    }

    @Inject
    protected Paperback(
            final CloverProperties cloverProperties,
            final IssueConfig issueConfig,
            final StoryListFormatter storyListFormatter,
            final Image coverArtImage
    ) {
        this.cloverProperties = cloverProperties;
        this.issueConfig = issueConfig;
        this.storyListFormatter = storyListFormatter;
        this.coverArtImage = coverArtImage;
    }

    @PostConstruct
    public void init() {
        final Area coverArtImageArea = coverArtImage.getArea();
        final Region coverArtRegion = Region.builder()
                .width(coverArtImageArea.getWidth())
                .height(coverArtImageArea.getHeight()).build();
        System.out.println("coverArtRegion = " + coverArtRegion);
        final int frontOffsetLeft = issueConfig.getKindleXOffset();
        System.out.println("frontOffsetLeft = " + frontOffsetLeft);
        final int frontOffsetTop = issueConfig.getKindleYOffset();
        System.out.println("frontOffsetTop = " + frontOffsetTop);
        final int frontWidth = issueConfig.getFrontWidth();
        final float kindleCoverRatio = cloverProperties.getWidth() / cloverProperties.getHeight();
        final Region frontRegion = Region.builder()
                .left(frontOffsetLeft)
                .top(frontOffsetTop)
                .width(frontWidth)
                .height((int) (frontWidth / kindleCoverRatio))
                .build();
        System.out.println("frontRegion = " + frontRegion);

        final int dpi = cloverProperties.getDpi();
        final Region kindleCoverRegion = Region.builder()
                .width((int) (cloverProperties.getWidth() * dpi))
                .height((int) (cloverProperties.getHeight() * dpi))
                .build();
        final float kindleCoverRegionHeight = kindleCoverRegion.getHeight();
        System.out.println("kindleCoverRegionHeight = " + kindleCoverRegionHeight);
        final float frontRegionHeight = frontRegion.getHeight();
        System.out.println("frontRegionHeight = " + frontRegionHeight);
        final float scaleToKindle = kindleCoverRegionHeight / frontRegionHeight;
        System.out.println("scaleToKindle = " + scaleToKindle);

        final float spineInches = issueConfig.getSpine();
        System.out.println("spineInches = " + spineInches);
        final float spineWidth = spineInches * dpi;
        System.out.println("spineWidth = " + spineWidth);
        final float spineArt = spineWidth / scaleToKindle;
        System.out.println("spineArt = " + spineArt);
        System.out.println("frontRegion.getLeft() = " + frontRegion.getLeft());
        System.out.println("frontRegion.getWidth() = " + frontRegion.getWidth());
        final Region fullRegion = Region.builder()
                .left(frontRegion.getLeft() - frontRegion.getWidth() - spineArt)
                .top(frontRegion.getTop())
                .width(frontRegion.getWidth() * 2 + spineArt)
                .height(frontRegion.getHeight()).build();

        fullRegion.mustContain(frontRegion);

        final Region scaledFull = Region.builder()
                .top(fullRegion.getTop() * scaleToKindle)
                .left(fullRegion.getLeft() * scaleToKindle)
                .width(fullRegion.getWidth() * scaleToKindle)
                .height(fullRegion.getHeight() * scaleToKindle).build();

        final Region frontWithinScaledFull = Region.builder()
                .top(0)
                .left(scaledFull.getWidth() - (frontWidth * scaleToKindle))
                .width(frontRegion.getWidth() * scaleToKindle)
                .height(scaledFull.getHeight()).build();

        System.out.println("coverArtRegion = " + coverArtRegion);
        System.out.println("frontRegion = " + frontRegion);
        System.out.println("fullRegion = " + fullRegion);
        System.out.println("---------------");
        System.out.println("scaledFull = " + scaledFull);
        System.out.println("frontWithinScaledFull = " + frontWithinScaledFull);
        System.out.println("---------------");

        scaledFull.mustContain(frontWithinScaledFull);
        assertThat(frontWithinScaledFull.getRight() == scaledFull.getRight());
        assertThat(frontWithinScaledFull.getTop() == 0);
        assertThat(frontWithinScaledFull.getBottom() == scaledFull.getBottom());
        assertThat(frontWithinScaledFull.getHeight() == scaledFull.getHeight());

        System.out.println("Cropping coverArtImage to fullRegion");
        final Image croppedFullRegionImage = coverArtImage.crop(fullRegion);
        System.out.println("Rescaling croppedFullRegionImage to scaleToKindle");
        image = croppedFullRegionImage.rescale(scaleToKindle);
        System.out.println("scaledFull = " + scaledFull);
        System.out.println("image.getRegion() = " + image.getRegion());
        if (!scaledFull.equals(image.getRegion())) {
            throw new IllegalStateException("Image is not expected size");
        }
        fullCoverCropRegion = scaledFull;
        frontCoverCropRegion = frontWithinScaledFull;
        fullCoverCropRegion.mustContain(frontCoverCropRegion);
//                frontCover()
//                .andThen(spine())
//                .andThen(backCover())
//                .apply(cropped)
        System.out.println("Paperback.init - done");
    }

    private void assertThat(final boolean test) {
        if (!test) {
            throw new IllegalStateException("Test failed");
        }
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

    protected Function<Image, Image> spine() {
        final FontFace fontFace =
                FontFace.of(
                        cloverProperties.getFontFile(),
                        62,
                        "yellow",
                        XY.at(
                                cloverProperties.getDropShadowXOffset(),
                                cloverProperties.getDropShadowYOffset()));
        final String spineText = String.format(
                "%s - Issue %s - %s",
                issueConfig.getPublicationTitle(),
                issueConfig.getIssue(),
                issueConfig.getDate());
        return image ->
                image.withFilledArea(
                        getSpineRegion(),
                        "black"
                ).withRotatedCenteredText(
                        spineText,
                        getSpineRegion(),
                        fontFace);
    }

    private Region getSpineRegion() {
        return null;
    }

    @Override
    public TypedProperties getImageProperties() {
        return TypedProperties.create()
                .with(PdfWidth.class, (int) fullCoverCropRegion.getWidth())
                .with(PdfHeight.class, (int) fullCoverCropRegion.getHeight());
    }

    protected Function<Image, Image> frontCover() {
        return drawTitle()
//                .andThen(drawSubTitles())
//                .andThen(drawAuthors())
                ;
    }

    private Function<Image, Image> drawTitle() {
        final FontFace fontFace =
                FontFace.of(
                        cloverProperties.getFontFile(),
                        217,
                        issueConfig.getTitleColour(),
                        XY.at(
                                cloverProperties.getDropShadowXOffset(),
                                cloverProperties.getDropShadowYOffset()));
        return image -> {
            LOGGER.info("Drawing title...");
            // TODO - get the title from Issue, line-split it and use
            //  Framing to center
            return image
                    .withText(
                            "Cossmass",
                            XY.at(60 + frontPageXOffset(), 90),
                            fontFace)
                    .withText(
                            "Infinities",
                            XY.at(130 + frontPageXOffset(), 307),
                            fontFace);
        };
    }

    private int frontPageXOffset() {
        return issueConfig.getKindleXOffset();
    }

    private Function<Image, Image> drawSubTitles() {
        final FontFace fontFace =
                FontFace.of(
                        cloverProperties.getFontFile(),
                        36,
                        issueConfig.getSubTitleColour(),
                        XY.at(
                                cloverProperties.getDropShadowXOffset(),
                                cloverProperties.getDropShadowYOffset()));
        return image -> {
            LOGGER.info("Drawing subtitle...");
            return image
                    .withText(String.format("Issue %s", issueConfig.getIssue()),
                            XY.at(60 + frontPageXOffset(), 485), fontFace)
                    .withText(issueConfig.getDate(),
                            //TODO use a right-edge and the text width to find X
                            XY.at(1200 + frontPageXOffset(), 485), fontFace)
                    .withText("Science Fiction and Fantasy",
                            XY.at(760 + frontPageXOffset(), 109), fontFace);
        };
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
            return image
                    .withText(issueConfig.getAuthors(),
                            XY.at(
                                    issueConfig.getAuthorsXOffset() + frontPageXOffset(),
                                    issueConfig.getAuthorsYOffset()),
                            fontFace);
        };
    }
}
