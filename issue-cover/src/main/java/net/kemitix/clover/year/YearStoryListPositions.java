package net.kemitix.clover.year;

import net.kemitix.clover.issue.BackCoverOpaque;
import net.kemitix.clover.spi.BackCover;
import net.kemitix.clover.spi.IssueConfig;
import net.kemitix.clover.spi.Region;
import net.kemitix.clover.spi.Section;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class YearStoryListPositions {

    @Inject @BackCover
    BackCoverOpaque backCoverOpaque;

    @Inject
    IssueConfig issueConfig;

    /**
     * Fantasy | Science Fiction
     * Science Fantasy
     * Original Fantasy | Original Science Fiction
     * Original Science Fantasy
     */
    Region regionFor(Section.Label label) {
        int gutter = 40;
        int padding = 85;
        Region opaque = backCoverOpaque.getRegion().withPadding(padding).withWidth(w -> w - padding);
        int columnWidth = (opaque.getWidth() + gutter) / 2;
        Region leftColumns = opaque.withWidth(columnWidth);
        Region rightColumns = leftColumns.withLeft(l -> l + columnWidth + gutter);
        Integer[] rows = issueConfig.getContents().getRows().toArray(new Integer[]{});
        return switch (label) {
            case REPRINT_FANTASY ->
                    leftColumns.withTop(t -> t + rows[0])
                            .withHeight(rows[1] - rows[0]);
            case REPRINT_SCIENCE_FICTION ->
                    rightColumns.withTop(t -> t + rows[0])
                            .withHeight(rows[1] - rows[0]);

            case REPRINT_SCIENCE_FANTASY ->
                    opaque.withTop(t -> t + rows[1])
                            .withHeight(rows[2] - rows[1]);

            case FANTASY ->
                    leftColumns.withTop(t -> t + rows[2])
                            .withHeight(rows[3] - rows[2]);
            case SCIENCE_FICTION ->
                    rightColumns.withTop(t -> t + rows[2])
                            .withHeight(rows[3] - rows[2]);

            case SCIENCE_FANTASY ->
                    opaque.withTop(t -> t + rows[3])
                            .withHeight(h -> h - rows[3]);
        };
    }

}
