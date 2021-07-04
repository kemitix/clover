package net.kemitix.clover.issue;

import lombok.Getter;
import net.kemitix.clover.spi.AbstractElement;
import net.kemitix.clover.spi.BackCover;
import net.kemitix.clover.spi.IssueConfig;
import net.kemitix.clover.spi.Region;
import net.kemitix.clover.spi.StoryListFormatter;
import net.kemitix.fontface.FontFace;
import net.kemitix.properties.typed.TypedProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.awt.*;

@BackCover
@ApplicationScoped
public class StoriesScienceFantasy extends AbstractElement {

    private static final String HEADER = "Original Science Fantasy";

    @Getter private final int priority = 10;

    @Inject @BackCover FontFace fontFace;
    @Inject StoryListFormatter storyListFormatter;
    @Inject IssueConfig issueConfig;
    @Inject StoriesListBlock storiesListBlock;

    @Override
    public void draw(
            Graphics2D drawable,
            TypedProperties typedProperties
    ) {
        storiesListBlock.draw(fontFace, drawable, text(), region(), HEADER);
    }

    private Region region() {
        return Region.builder()
                .top(issueConfig.getScienceFantasyTop())
                .left(issueConfig.getScienceFantasyLeft())
                .build();
    }

    private String text() {
        return String.join("\n",
                storyListFormatter.format(
                        HEADER,
                        issueConfig.getStories().getScienceFantasy()));
    }

}
