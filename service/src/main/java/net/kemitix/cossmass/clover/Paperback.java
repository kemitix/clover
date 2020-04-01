package net.kemitix.cossmass.clover;

import net.kemitix.cossmass.clover.images.*;

import javax.enterprise.context.Dependent;
import java.util.function.Function;
import java.util.logging.Logger;

@Dependent
public class Paperback extends FrontCoverFormat {

    private static final Logger LOGGER =
            Logger.getLogger(
                    Paperback.class.getName());
    private final Issue issue;
    private final CloverConfig config;
    private final StoryListFormatter storyListFormatter;

    protected Paperback(
            final CloverConfig config,
            final Issue issue,
            final ImageService imageService,
            final StoryListFormatter storyListFormatter
    ) {
        super(config, issue, imageService);
        this.config = config;
        this.issue = issue;
        this.storyListFormatter = storyListFormatter;
    }

    @Override
    protected int frontPageXOffset() {
        return (int) ((config.width() + issue.getSpine()) / 2);
    }

    @Override
    protected int getCropYOffset() {
        return issue.getPaperbackYOffset();
    }

    @Override
    protected int getCropXOffset() {
        return issue.getPaperbackXOffset();
    }

    @Override
    protected String getName() {
        return "paperback";
    }

    @Override
    protected int getHeight() {
        return config.height();
    }

    @Override
    protected int getWidth() {
        return (int) ((config.width() * 2) + issue.getSpine());
    }

    @Override
    protected Function<Image, Image> backCover() {
        return super.backCover()
                .andThen(drawSFStories())
//                .andThen(drawFantasyStories())
//                .andThen(drawReprintStories())
                ;
    }

    private Function<Image, Image> drawSFStories() {
        final FontFace fontFace =
                FontFace.of(
                        config.getFontFile(),
                        48,
                        issue.getTextColour(),
                        XY.at(
                                config.getDropShadowXOffset(),
                                config.getDropShadowYOffset()));
        return image -> {
            LOGGER.info("Drawing SF Stories...");
            return image
                    .withText(
                            storyListFormatter.format(
                                    "Science Fiction Stories",
                                    issue.getSfStories()),
                            XY.at(150, 200),
                            fontFace);
        };
    }

    @Override
    protected Function<Image, Image> spine() {
        //TODO draw spine
        return super.spine();
    }
}
