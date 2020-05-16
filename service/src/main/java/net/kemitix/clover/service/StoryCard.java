package net.kemitix.clover.service;

import lombok.extern.java.Log;
import net.kemitix.clover.spi.CenteredTextEffect;
import net.kemitix.clover.spi.CloverProperties;
import net.kemitix.clover.spi.images.FontFace;
import net.kemitix.clover.spi.images.Image;
import net.kemitix.clover.spi.images.XY;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.function.BiFunction;
import java.util.logging.Logger;

@ApplicationScoped
public class StoryCard implements BiFunction<IssueConfig.Story, Image, Image> {

    private static final Logger LOG =
            Logger.getLogger(
                    StoryCard.class.getName());

    private CloverProperties cloverProperties;
    private IssueConfig issueConfig;
    private CenteredTextEffect centeredText;
    private Dimensions dimensions;

    public StoryCard() {
    }

    @Inject
    public StoryCard(
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
    public Image apply(
            IssueConfig.Story story,
            Image image
    ) {
        final FontFace fontFace =
                FontFace.of(
                        cloverProperties.getFontFile(),
                        120,
                        issueConfig.getTextColour(),
                        XY.at(
                                cloverProperties.getDropShadowXOffset(),
                                cloverProperties.getDropShadowYOffset()));
        LOG.info(String.format("Story: %s", story.getTitle()));
        return centeredText.fontFace(fontFace)
                .region(dimensions.getFrontCrop().toBuilder()
                        .left(0)
                        .width(dimensions.getWrapCrop().getWidth()).build()
                        .withPadding(100))
                .text(story.getTitle())
                .apply(image)
                .withNameQualifier("-" +
                        story.getTitle()
                                .replace(" ", "")
                                .replace("'", "")
                                .toLowerCase()
                                .substring(0, 10));
    }
}
