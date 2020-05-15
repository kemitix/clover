package net.kemitix.clover.service;

import net.kemitix.clover.spi.*;
import net.kemitix.clover.spi.Image;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.awt.*;
import java.util.function.Function;
import java.util.logging.Logger;

@ApplicationScoped
public class BackCover implements Function<Image, Image> {

    private static final Logger LOGGER =
            Logger.getLogger(
                    BackCover.class.getName());

    @Inject
    CloverProperties cloverProperties;
    @Inject
    IssueConfig issueConfig;
    @Inject
    StoryListFormatter storyListFormatter;
    @Inject
    SimpleTextEffect<Image> simpleTextEffect;
    @Inject @net.kemitix.clover.spi.BackCover
    Instance<Element<Graphics2D>> backCoverElements;

    @Override
    public Image apply(Image image) {
        final FontFace fontFace =
                FontFace.of(
                        cloverProperties.getFontFile(),
                        48,
                        issueConfig.getTextColour(),
                        XY.at(
                                cloverProperties.getDropShadowXOffset(),
                                cloverProperties.getDropShadowYOffset()));
        return drawFantasyStories(fontFace)
                .andThen(elements())
                .apply(image);
    }

    private Function<Image, Image> elements() {
        return image -> image.withGraphics(graphics2D ->
                Drawable.draw(backCoverElements, graphics2D));
    }

    private Function<Image, Image> drawFantasyStories(final FontFace fontFace) {
        return image -> {
            LOGGER.info("Drawing Fantasy Stories...");
            int top = 1100;
            int left = 500;
            return simpleTextEffect.fontFace(fontFace)
                    .region(Region.builder()
                            .top(top).left(left)
                            .width(image.getRegion().getWidth() - left)
                            .height(image.getRegion().getHeight() - top)
                            .build())
                    .text(String.join("\n",
                            storyListFormatter.format(
                                    "Fantasy Stories",
                                    issueConfig.getStories().getFantasy())))
                    .apply(image);
        };
    }

}
