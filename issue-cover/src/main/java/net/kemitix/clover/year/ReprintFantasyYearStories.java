package net.kemitix.clover.year;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
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

import java.util.List;

@BackCover
@ApplicationScoped
public class ReprintFantasyYearStories
        extends AbstractStoriesList
        implements ForYearCovers {

    @Getter
    private final int priority = 10;

    @Getter
    private final Section.Label sectionLabel = Section.Label.REPRINT_FANTASY;

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
        return "The %s Stories / %s".formatted(
                issueConfig.getDate(),
                Section.Label.FANTASY.getValue());
    }

    @Override
    protected Region region(Section.Label label) {
        return positions.regionFor(label);
    }

    @Override
    protected List<? extends IssueStory> getStories() {
        return issueConfig.getStories(Section.Label.REPRINT_FANTASY);
    }

}
