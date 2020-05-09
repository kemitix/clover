package net.kemitix.clover.service;

import net.kemitix.clover.spi.CloverProperties;
import net.kemitix.clover.spi.images.FontFace;
import net.kemitix.clover.spi.images.Image;
import net.kemitix.clover.spi.images.XY;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.function.Function;
import java.util.logging.Logger;

@ApplicationScoped
public class FrontCover implements Function<Image, Image> {

    private static final Logger LOGGER =
            Logger.getLogger(
                    FrontCover.class.getName());

    private CloverProperties cloverProperties;
    private IssueConfig issueConfig;
    private Dimensions dimensions;

    public FrontCover() {
    }

    @Inject
    public FrontCover(
            CloverProperties cloverProperties,
            IssueConfig issueConfig,
            Dimensions dimensions
    ) {
        this.cloverProperties = cloverProperties;
        this.issueConfig = issueConfig;
        this.dimensions = dimensions;
    }


    @Override
    public Image apply(Image image) {
        return drawTitle()
                .andThen(drawSubTitles())
                .andThen(drawAuthors())
                .apply(image);
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
                            XY.at(60 + frontLeftEdge(), 90),
                            fontFace)
                    .withText(
                            "Infinities",
                            XY.at(130 + frontLeftEdge(), 307),
                            fontFace);
        };
    }

    private int frontLeftEdge() {
        return dimensions.getFrontCrop().getLeft();
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
                            XY.at(60 + frontLeftEdge(), 485), fontFace)
                    .withText(issueConfig.getDate(),
                            //TODO use a right-edge and the text width to find X
                            XY.at(1200 + frontLeftEdge(), 485), fontFace)
                    .withText("Science Fiction and Fantasy",
                            XY.at(760 + frontLeftEdge(), 109), fontFace);
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
                                    issueConfig.getAuthorsXOffset() + frontLeftEdge(),
                                    issueConfig.getAuthorsYOffset()),
                            fontFace);
        };
    }
}
