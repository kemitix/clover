package net.kemitix.clover.issue;

import lombok.Getter;
import net.kemitix.clover.spi.AbstractElement;
import net.kemitix.clover.spi.BackCover;
import net.kemitix.clover.spi.BackCoverBackgroundBox;
import net.kemitix.clover.spi.IssueConfig;
import net.kemitix.clover.spi.OpaqueFill;
import net.kemitix.clover.spi.Region;
import net.kemitix.clover.spi.SimpleTextEffect;
import net.kemitix.clover.spi.StoryListFormatter;
import net.kemitix.clover.spi.TextEffect;
import net.kemitix.fontface.FontCache;
import net.kemitix.fontface.FontFace;
import net.kemitix.properties.typed.TypedProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.HEAD;
import java.awt.*;
import java.awt.geom.Rectangle2D;

@BackCover
@ApplicationScoped
public class StoriesFantasy extends AbstractElement {

    private static final String HEADER = "Original Fantasy";

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
                .top(issueConfig.getFantasyTop())
                .left(issueConfig.getFantasyLeft())
                .build();
    }

    private String text() {
        return String.join("\n",
                storyListFormatter.format(
                        HEADER,
                        issueConfig.getStories().getFantasy()));
    }

}
