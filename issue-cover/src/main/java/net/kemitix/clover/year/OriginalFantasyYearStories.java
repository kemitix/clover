package net.kemitix.clover.year;

import lombok.Getter;
import net.kemitix.clover.issue.AbstractStoriesList;
import net.kemitix.clover.issue.StoriesListBlock;
import net.kemitix.clover.spi.BackCover;
import net.kemitix.clover.spi.IssueConfig;
import net.kemitix.clover.spi.IssueStory;
import net.kemitix.clover.spi.Region;
import net.kemitix.clover.spi.Section;
import net.kemitix.clover.spi.StoryListFormatter;
import net.kemitix.fontface.FontFace;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@BackCover
@ApplicationScoped
public class OriginalFantasyYearStories
        extends AbstractStoriesList
        implements ForYearCovers {

    @Getter
    private final int priority = 10;

    @Getter
    private final Section.Label sectionLabel = Section.Label.FANTASY;

    @Inject
    YearStoryListPositions positions;

    @Inject
    @Getter
    IssueConfig issueConfig;

    @Inject
    @Getter
    StoryListFormatter storyListFormatter;

    @Inject
    @Getter
    StoriesListBlock storiesListBlock;

    @Inject @BackCover
    @Getter
    FontFace fontFace;

    @Override
    protected String getHeader() {
        return "The Bonus Stories / %s".formatted(
                Section.Label.FANTASY.getValue());
    }

    @Override
    protected Region region(Section.Label label) {
        return positions.regionFor(label);
    }

    @Override
    protected List<? extends IssueStory> getStories() {
        return issueConfig.getStories(Section.Label.FANTASY);
    }

}
