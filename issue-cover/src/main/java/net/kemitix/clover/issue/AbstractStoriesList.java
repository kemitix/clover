package net.kemitix.clover.issue;

import net.kemitix.clover.spi.*;
import net.kemitix.fontface.FontFace;
import net.kemitix.properties.typed.TypedProperties;

import java.awt.*;
import java.util.List;
import java.util.Optional;

abstract class AbstractStoriesList extends AbstractElement {

    protected abstract IssueConfig getIssueConfig();
    protected abstract StoryListFormatter getStoryListFormatter();
    protected abstract StoriesListBlock getStoriesListBlock();
    protected abstract FontFace getFontFace();
    protected abstract String getHeader();
    protected abstract int getLeft();
    protected abstract int getTop();
    protected abstract List<? extends IssueStory> getStories();

    @Override
    public void draw(
            Graphics2D drawable,
            TypedProperties typedProperties
    ) {
        if (isNotEmpty()) {
            getStoriesListBlock()
                    .draw(getFontFace(), drawable, text(), region(), getHeader());
        }
    }

    private boolean isNotEmpty() {
        return Optional.ofNullable(getStories())
                .map(issueStories -> !issueStories.isEmpty())
                .orElse(false);
    }


    private Region region() {
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
