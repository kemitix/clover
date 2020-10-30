package net.kemitix.clover.spi;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public interface IssueConfig {

    String getIssue();

    String getTitleColour();

    String getSubTitleColour();

    int getKindleYOffset();

    int getKindleXOffset();

    int getFrontWidth();

    float getSpine();

    String getTextColour();

    String getCoverArt();

    String getPublicationTitle();

    String getDate();

    int getAuthorsYOffset();

    int getAuthorsXOffset();

    default List<String> authors() {
        return getStories().stream()
                .map(IssueStory::getAuthor)
                .sorted(byAuthorName())
                .map(IssueAuthor::authorName)
                .collect(Collectors.toList());
    }

    private Comparator<IssueAuthor> byAuthorName() {
        return Comparator.comparing(
                (IssueAuthor author1) -> author1.getSurname().toLowerCase())
                .thenComparing(
                        author2 -> author2.getForename().toLowerCase());
    }

    TextEffect.HAlignment getStoriesAlignment();

    IssueStories getStories();


    default String getSpineText() {
        return String.format("%s - Issue %s - %s",
                getPublicationTitle(),
                getIssue(),
                getDate());
    }

    IssueStoryCards getStoryCards();

    int getReprintTop();

    int getReprintLeft();

    int getFantasyTop();

    int getFantasyLeft();

    int getSfTop();

    int getSfLeft();

    BackCoverBackgroundBox getBackCoverBackgroundBox();
}
