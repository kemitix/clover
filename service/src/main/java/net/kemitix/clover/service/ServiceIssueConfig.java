package net.kemitix.clover.service;

import lombok.Getter;
import lombok.Setter;
import net.kemitix.clover.spi.*;

import javax.enterprise.inject.Vetoed;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Vetoed
@Setter
@Getter
public class ServiceIssueConfig implements IssueConfig {

    public ServiceIssueConfig() {
    }

    private IssueType type;
    private String issue;
    private String date;
    private String titleColour;
    private String subTitleColour;
    private String textColour;
    private float spine;
    private float height;
    private float width;
    private int paperbackXOffset;
    private int paperbackYOffset;
    private int kindleXOffset;
    private int kindleYOffset;
    private int frontFontSize;
    private ServiceAuthorsConfig authors;
    private Cards cards;
    private String coverArt;
    private String coverArtist;
    private String publicationTitle;
    private int frontWidth;
    private StoryCards storyCards;
    private BackCoverBackgroundBox backCoverBackgroundBox;
    private AuthorStrapBox authorStrapBox;
    private ServiceContents contents;

    @Override
    public IssueStories getAllStories() {
        Stories stories = new Stories();
        contents.getSections()
                .forEach(section -> {
                    switch (section.label) {
                        case FANTASY -> stories.setFantasy(section.getStories());
                        case SCIENCE_FICTION -> stories.setSf(section.getStories());
                        case SCIENCE_FANTASY -> stories.setScienceFantasy(section.getStories());
                        case ORIGINAL -> stories.setOriginal(section.getStories());
                    }
                });
        return stories;
    }

    @Setter
    @Getter
    public static class Stories implements IssueStories {
        private List<Story> sf;
        private List<Story> fantasy;
        private List<Story> scienceFantasy;
        private List<Story> reprint;
        private List<Story> original;
    }

    @Setter
    @Getter
    public static class Story implements IssueStory {
        private Author author;
        private String title;
        private String sample;
    }

    @Setter
    @Getter
    public static class Author implements IssueAuthor {
        private String surname;
        private String forename;

    }

    @Setter
    @Getter
    public static class Cards implements IssueCards {
        private Crop crop;
    }

    @Setter
    @Getter
    public static class Crop implements IssueCrop {
        private int top;
        private int left;
        private int width;
        private int height;
    }

    @Setter
    @Getter
    public static class StoryCards implements IssueStoryCards {
        int top;
        int left;
        int width;
        int titleFontSize;
    }

    @Setter
    @Getter
    public static class ServiceContents implements Contents {
        TextEffect.HAlignment alignment;
        int fontSize;
        List<ServiceSection> sections;
    }

    @Setter
    @Getter
    public static class ServiceSection implements Section {
        Section.Label label;
        int top;
        int left;
        List<Story> stories;
    }

    @Setter
    @Getter
    public static class ServiceAuthorsConfig implements AuthorsConfig {
        private int top;
        private int width;
        private int left;
        private TextEffect.HAlignment alignment;
    }
}
