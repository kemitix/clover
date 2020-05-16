package net.kemitix.clover.story.card;

import net.kemitix.clover.spi.Drawable;
import net.kemitix.clover.spi.Element;
import net.kemitix.clover.spi.Image;
import net.kemitix.clover.spi.IssueStory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.awt.*;

@ApplicationScoped
public class StoryCardFactory {

    @Inject Image coverArtImage;
    @Inject StoryCardDimensions dimensions;
    @Inject @StoryCard Instance<Element<Graphics2D>> elements;

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
        return backgroundImage
                .withGraphics(graphics2D ->
                        Drawable.draw(elements, graphics2D))
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
                        .substring(0, Math.min(20, issueStory.getTitle().length()))
                        .replace("--", "-")
                        .replaceAll("-$", "")
                ;
    }
}
