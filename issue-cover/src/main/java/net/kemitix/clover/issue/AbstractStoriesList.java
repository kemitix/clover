package net.kemitix.clover.issue;

import net.kemitix.clover.spi.*;
import net.kemitix.fontface.FontFace;
import net.kemitix.properties.typed.TypedProperties;

import java.awt.*;
import java.util.List;
import java.util.Optional;

public abstract class AbstractStoriesList extends AbstractElement {

    protected abstract IssueConfig getIssueConfig();
    protected abstract StoryListFormatter getStoryListFormatter();
    protected abstract StoriesListBlock getStoriesListBlock();
    protected abstract FontFace getFontFace();
    protected abstract String getHeader();
    protected abstract List<? extends IssueStory> getStories();
    protected abstract Section.Label getSectionLabel();

    @Override
    public void draw(
            Graphics2D drawable,
            TypedProperties typedProperties
    ) {
        if (isNotEmpty()) {
            getStoriesListBlock()
                    .draw(getFontFace(), drawable, text(), region(getSectionLabel()), getHeader());
        }
    }

    protected int getLeft() {
        return 0;
    }

    protected int getTop() {
        return 0;
    }


    private boolean isNotEmpty() {
        return Optional.ofNullable(getStories())
                .map(issueStories -> !issueStories.isEmpty())
                .orElse(false);
    }


    protected Region region(Section.Label label) {
        return Region.builder()
                .top(getTop())
                .left(getLeft())
                .build();
    }

    private String text() {
        return String.join("\n",
                getStoryListFormatter().format(
                        getHeader(),
                        getStories()));
    }

}
