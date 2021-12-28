package net.kemitix.clover.issue;

import lombok.Getter;
import net.kemitix.clover.spi.BackCover;
import net.kemitix.clover.spi.IssueConfig;
import net.kemitix.clover.spi.IssueStory;
import net.kemitix.clover.spi.Section;
import net.kemitix.clover.spi.StoryListFormatter;
import net.kemitix.fontface.FontFace;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@BackCover
@ApplicationScoped
public class StoriesFantasy extends AbstractStoriesList
        implements ForIssueCovers {

    @Getter
    private final String header = "Fantasy";

    @Getter
    private final int priority = 10;

    @Getter
    private final Section.Label sectionLabel = Section.Label.FANTASY;

    @Inject @BackCover
    @Getter
    FontFace fontFace;

    @Inject
    @Getter
    StoryListFormatter storyListFormatter;

    @Inject
    @Getter
    IssueConfig issueConfig;

    @Inject
    @Getter
    StoriesListBlock storiesListBlock;

    @Override
    protected int getLeft() {
        return issueConfig.getSectionLeft(Section.Label.FANTASY);
    }

    @Override
    protected int getTop() {
        return issueConfig.getSectionTop(Section.Label.FANTASY);
    }

    @Override
    protected List<? extends IssueStory> getStories() {
        return issueConfig.getStories(Section.Label.FANTASY);
    }

}
