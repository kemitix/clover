package net.kemitix.cossmass.clover;

import net.kemitix.cossmass.clover.images.*;

import java.io.File;
import java.util.function.Function;
import java.util.logging.Logger;

public abstract class FrontCoverFormat extends CloverFormat {

    private static final Logger LOGGER =
            Logger.getLogger(
                    FrontCoverFormat.class.getName());

    private final Issue issue;
    private final CloverConfig config;

    protected FrontCoverFormat(
            final CloverConfig config,
            final Issue issue,
            final ImageService imageService
    ) {
        super(config, issue, imageService);
        this.config = config;
        this.issue = issue;
    }

    @Override
    protected Function<Image, Image> frontCover() {
        LOGGER.info("Drawing the Front Cover");
        return super.frontCover()
                .andThen(drawTitle())
                .andThen(drawSubTitles())
                .andThen(drawAuthors());
    }

    private Function<Image, Image> drawTitle() {
        LOGGER.info("drawTitle()");
        final File font = config.getFontFile();
        final int size = 217;
        final String colour = issue.getTitleColour();
        return image -> {
            LOGGER.info("drawTitle().apply()");
            return image
                    .withText(
                            "Cossmass",
                            XY.at(60, 90),
                            FontFace.of(font, size, colour))
                    .withText(
                            "Infinities",
                            XY.at(130, 307),
                            FontFace.of(font, size, colour));
        };
    }

    private Function<Image, Image> drawSubTitles() {
        LOGGER.info("drawSubTitles()");
        return image -> {
            LOGGER.info("drawSubTitles().apply()");
            return image;
        };
    }

    private Function<Image, Image> drawAuthors() {
        LOGGER.info("drawAuthors()");
        return image -> {
            LOGGER.info("drawAuthors().apply()");
            return image;
        };
    }

}
