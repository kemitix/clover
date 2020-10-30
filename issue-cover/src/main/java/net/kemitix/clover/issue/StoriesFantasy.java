package net.kemitix.clover.issue;

import lombok.Getter;
import net.kemitix.clover.spi.AbstractElement;
import net.kemitix.clover.spi.BackCover;
import net.kemitix.clover.spi.BackCoverBackgroundBox;
import net.kemitix.clover.spi.IssueConfig;
import net.kemitix.clover.spi.OpaqueFill;
import net.kemitix.clover.spi.Region;
import net.kemitix.clover.spi.SimpleTextEffect;
import net.kemitix.clover.spi.StoryListFormatter;
import net.kemitix.clover.spi.TextEffect;
import net.kemitix.fontface.FontCache;
import net.kemitix.fontface.FontFace;
import net.kemitix.properties.typed.TypedProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.awt.*;
import java.awt.geom.Rectangle2D;

@BackCover
@ApplicationScoped
public class StoriesFantasy extends AbstractElement {

    private static final String HEADER = "Original Fantasy";

    @Getter private final int priority = 10;

    @Inject @BackCover FontFace fontFace;
    @Inject SimpleTextEffect<Graphics2D> simpleTextEffect;
    @Inject StoryListFormatter storyListFormatter;
    @Inject IssueConfig issueConfig;
    @Inject OpaqueFill<Graphics2D> opaqueFill;
    @Inject FontCache fontCache;
    @Inject BackCoverBackgroundBox backgroundBox;

    @Override
    public void draw(Graphics2D drawable, TypedProperties typedProperties) {
        simpleTextEffect.fontFace(fontFace)
                .text(text())
                .vAlign(TextEffect.VAlignment.TOP)
                .hAlign(issueConfig.getStoriesAlignment())
                .region(region())
                .accept(drawable);
        opaqueFill.opacity(1d)
                .colour(fontFace.getColour())
                .region(hRuleRegion(drawable))
                .accept(drawable);
    }

    private Region hRuleRegion(Graphics2D drawable) {
        int width = lineWidth(drawable);
        Region region = region()
                .withTop(top -> top + lineHeight(drawable))
                .withHeight(backgroundBox.getMarginStep())
                .withWidth(width);
        if (issueConfig.getStoriesAlignment()
                .equals(TextEffect.HAlignment.CENTRE)) {
            return region
                    .withLeft(left -> left - (width / 2));
        } else {
            return region;
        }
    }

    private int lineWidth(Graphics2D drawable) {
        Font font = fontCache.loadFont(fontFace);
        Rectangle2D stringBounds =
                font.getStringBounds(HEADER, drawable.getFontRenderContext());
        return (int) stringBounds.getWidth();
    }

    private int lineHeight(Graphics2D drawable) {
        Font font = fontCache.loadFont(fontFace);
        Rectangle2D stringBounds =
                font.getStringBounds(HEADER, drawable.getFontRenderContext());
        return (int) (stringBounds.getHeight() * 1.2);
    }

    private Region region() {
        return Region.builder()
                .top(issueConfig.getFantasyTop())
                .left(issueConfig.getFantasyLeft())
                .build();
    }

    private String text() {
        return String.join("\n",
                storyListFormatter.format(
                        HEADER,
                        issueConfig.getStories().getFantasy()));
    }

}
