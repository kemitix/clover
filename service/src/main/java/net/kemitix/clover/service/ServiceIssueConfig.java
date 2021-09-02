package net.kemitix.clover.service;

import lombok.Getter;
import lombok.Setter;
import net.kemitix.clover.spi.*;

import javax.enterprise.inject.Vetoed;
import java.util.List;

@Vetoed
@Setter
@Getter
public class ServiceIssueConfig implements IssueConfig {

    public ServiceIssueConfig() {
    }

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
    private int authorsXOffset;
    private int authorsYOffset;
    private Cards cards;
    private TextEffect.HAlignment storiesAlignment;
    private Stories stories;
    private String coverArt;
    private String coverArtist;
    private String publicationTitle;
    private int frontWidth;
    private StoryCards storyCards;
    private int sfTop;
    private int sfLeft;
    private int fantasyTop;
    private int fantasyLeft;
    private int scienceFantasyTop;
    private int scienceFantasyLeft;
    private int reprintTop;
    private int reprintLeft;
    private BackCoverBackgroundBox backCoverBackgroundBox;
    private AuthorStrapBox authorStrapBox;

    @Setter
    @Getter
    public static class Stories implements IssueStories {
        private List<Story> sf;
        private List<Story> fantasy;
        private List<Story> scienceFantasy;
        private List<Story> reprint;
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
}
