package net.kemitix.clover.story.card;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import lombok.extern.java.Log;
import net.kemitix.clover.spi.Drawable;
import net.kemitix.clover.spi.Element;
import net.kemitix.clover.spi.Image;
import net.kemitix.clover.spi.IssueStory;
import net.kemitix.properties.typed.TypedProperties;

import java.awt.*;

@Log
@ApplicationScoped
public class StoryCardFactory {

    @Inject
    Image coverArtImage;
    @Inject StoryCardDimensions dimensions;
    @Inject @StoryCard
    Instance<Element<Graphics2D>> elements;

    private Image backgroundImage;

    @PostConstruct
    void init() {
        backgroundImage =
                coverArtImage
                        .rescale(dimensions.getScaleFromOriginal())
                        .crop(dimensions.getSourceRegion())
        ;
    }

    public Image create(IssueStory issueStory) {
        log.info("Generating Story Card: " + issueStory.getTitle());
        return backgroundImage
                .withGraphics(graphics2D ->
                        Drawable.draw(elements.stream(), graphics2D,
                                TypedProperties.create().
                                        with(TypedKeys.Story.class, issueStory)))
                .withNameQualifier(slug(issueStory));
    }

    private String slug(IssueStory issueStory) {
        return "-" +
                issueStory.getTitle()
                        .replace(" ", "-")
                        .replace("'", "-")
                        .replace("`", "-")
                        .replace("‘", "-")
                        .replace("’", "-")
                        .replace(",", "-")
                        .replace("\n", "-")
                        .toLowerCase()
                        .replace("--", "-")
                        .replaceAll("-$", "")
                ;
    }
}
