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
        REPRINTS("Reprints"),
        FANTASY("Fantasy"),
        SCIENCE_FICTION("Science Fiction"),
        SCIENCE_FANTASY("Science Fantasy"),
        ORIGINAL("Original"),
        ;

        final String value;
    }
}
