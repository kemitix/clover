package net.kemitix.clover.spi;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public interface Section {

    Section.Label getLabel();
    int getTop();
    int getLeft();
    List<? extends IssueStory> getStories();

    @Getter
    @AllArgsConstructor
    enum Label {
        FANTASY("Fantasy"),
        SCIENCE_FICTION("Science Fiction"),
        SCIENCE_FANTASY("Science Fantasy"),
        REPRINT_FANTASY("Fantasy"),
        REPRINT_SCIENCE_FICTION("Science Fiction"),
        REPRINT_SCIENCE_FANTASY("Science Fantasy"),
        ;

        final String value;
    }
}
