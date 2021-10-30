package net.kemitix.clover.spi;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public interface IssueConfig {

    IssueType getType();

    String getIssue();

    String getTitleColour();

    String getSubTitleColour();

    int getKindleYOffset();

    int getKindleXOffset();

    int getFrontWidth();

    float getSpine();

    /**
     * Front cover height in inches.
     */
    float getHeight();

    /**
     * Front cover width in inches.
     */
    float getWidth();

    String getTextColour();

    String getCoverArt();

    String getCoverArtist();

    String getPublicationTitle();

    String getDate();

    AuthorsConfig getAuthors();

    default List<String> authors() {
        return getAllStories()
                .stream()
                .map(IssueStory::getAuthor)
                .sorted(byAuthorName())
                .map(IssueAuthor::authorName)
                .distinct()
                .collect(Collectors.toList());
    }

    IssueStories getAllStories();

    default List<? extends IssueStory> getStories(Section.Label label) {
        return getContents()
                .findSection(label)
                .map(Section::getStories)
                .orElseGet(Collections::emptyList);
    }

    private Comparator<IssueAuthor> byAuthorName() {
        return Comparator.comparing(
                        (IssueAuthor author1) -> author1.getSurname().toLowerCase())
                .thenComparing(
                        author2 -> author2.getForename().toLowerCase());
    }

    default String getSpineText() {
        return switch (getType()) {
            case ISSUE -> String.format("%s - Issue %s - %s",
                    getPublicationTitle(),
                    getIssue(),
                    getDate());
            case YEAR -> String.format("%s%nThe %s Year - %s",
                    getPublicationTitle(),
                    getIssue(),
                    getDate());
        };
    }

    IssueStoryCards getStoryCards();

    BackCoverBackgroundBox getBackCoverBackgroundBox();

    AuthorStrapBox getAuthorStrapBox();

    Contents getContents();

    default boolean hasSection(Section.Label label) {
        return getContents().findSection(label).isPresent();
    }

    default int getSectionLeft(Section.Label label) {
        return getContents()
                .findSection(label)
                .map(Section::getLeft)
                .orElseThrow(() -> new IllegalStateException("Section %s 'left' value missing".formatted(label)));
    }

    default int getSectionTop(Section.Label label) {
        return getContents()
                .findSection(label)
                .map(Section::getTop)
                .orElseThrow(() -> new IllegalStateException("Section %s 'top' value missing".formatted(label)));
    }

    int getFrontFontSize();
}
