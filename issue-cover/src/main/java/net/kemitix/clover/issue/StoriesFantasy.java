package net.kemitix.clover.issue;

import lombok.Getter;
import net.kemitix.clover.spi.BackCover;
import net.kemitix.clover.spi.IssueConfig;
import net.kemitix.clover.spi.IssueStory;
import net.kemitix.clover.spi.StoryListFormatter;
import net.kemitix.fontface.FontFace;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@Getter
@BackCover
@ApplicationScoped
public class StoriesFantasy extends AbstractStoriesList {

    private final String header = "Original Fantasy";

    private final int priority = 10;

    @Inject @BackCover FontFace fontFace;
    @Inject StoryListFormatter storyListFormatter;
    @Inject IssueConfig issueConfig;
    @Inject StoriesListBlock storiesListBlock;

    @Override
    protected int getLeft() {
        return issueConfig.getFantasyLeft();
    }

    @Override
    protected int getTop() {
        return issueConfig.getFantasyTop();
    }

    @Override
    protected List<? extends IssueStory> getStories() {
        return issueConfig.getStories().getFantasy();
    }

}
