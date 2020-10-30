package net.kemitix.clover.issue;

import lombok.Getter;
import net.kemitix.clover.spi.*;
import net.kemitix.fontface.FontFace;
import net.kemitix.properties.typed.TypedProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.awt.*;

@BackCover
@ApplicationScoped
public class StoriesReprints extends AbstractElement {

    private static final String HEADER = "Plus";

    @Getter private final int priority = 30;

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

    private String text() {
        var reprints = issueConfig.getStories().getReprint();
        if (reprints.isEmpty()) {
            return "";
        }
        return String.join("\n",
                storyListFormatter.format(
                        HEADER,
                        reprints));
    }

    private Region region() {
        return Region.builder()
                .top(issueConfig.getReprintTop())
                .left(issueConfig.getReprintLeft())
                .build();
    }

}
