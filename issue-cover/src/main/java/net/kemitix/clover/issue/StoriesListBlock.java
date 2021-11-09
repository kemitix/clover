package net.kemitix.clover.issue;

import net.kemitix.clover.spi.BackCoverBackgroundBox;
import net.kemitix.clover.spi.BoxEffect;
import net.kemitix.clover.spi.IssueConfig;
import net.kemitix.clover.spi.OpaqueFill;
import net.kemitix.clover.spi.Region;
import net.kemitix.clover.spi.SimpleTextEffect;
import net.kemitix.clover.spi.TextEffect;
import net.kemitix.fontface.FontCache;
import net.kemitix.fontface.FontFace;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.awt.*;

@ApplicationScoped
public class StoriesListBlock {

    public static final boolean drawBounds = false;

    @Inject SimpleTextEffect<Graphics2D> simpleTextEffect;
    @Inject IssueConfig issueConfig;
    @Inject FontCache fontCache;
    @Inject OpaqueFill<Graphics2D> opaqueFill;
    @Inject BackCoverBackgroundBox backgroundBox;
    @Inject BoxEffect<Graphics2D> boxEffect;

    public void draw(
            FontFace fontFace,
            Graphics2D drawable,
            String text,
            Region region,
            String header
    ) {
        if (drawBounds) {
            boxEffect.opacity(1)
                    .thickness(1)
                    .colour("red")
                    .region(region)
                    .accept(drawable);
        }
        if (text.isBlank()) {
            return;
        }
        simpleTextEffect.fontFace(fontFace)
                .text(text)
                .vAlign(TextEffect.VAlignment.TOP)
                .hAlign(issueConfig.getContents().getAlignment())
                .region(region)
                .accept(drawable);
        drawHeaderUnderline(drawable, header, region, fontFace);
    }

    private void drawHeaderUnderline(
            Graphics2D drawable,
            String header,
            Region region,
            FontFace fontFace
    ) {
        opaqueFill.opacity(1d)
                .colour(fontFace.getColour())
                .region(hRuleRegion(drawable, header, region, fontFace))
                .accept(drawable);
    }

    private Region hRuleRegion(
            Graphics2D drawable,
            String header,
            Region region,
            FontFace fontFace
    ) {
        int width = lineWidth(drawable, header, fontFace);
        Region r = region
                .withTop(top -> top + lineHeight(drawable, header, fontFace))
                .withHeight(backgroundBox.getMarginStep())
                .withWidth(width);
        if (issueConfig.getContents()
                .getAlignment()
                .equals(TextEffect.HAlignment.CENTRE)) {
            return r.withLeft(l -> l + ((region.getWidth() - width) / 2));
        } else {
            return r;
        }
    }

    private int lineWidth(
            Graphics2D drawable,
            String header,
            FontFace fontFace
    ) {
        return (int) fontCache.loadFont(fontFace)
                .getStringBounds(header, drawable.getFontRenderContext())
                .getWidth();
    }

    private int lineHeight(
            Graphics2D drawable,
            String header,
            FontFace fontFace
    ) {
        return (int) (fontCache.loadFont(fontFace)
                .getStringBounds(header, drawable.getFontRenderContext())
                .getHeight() * 1.2);
    }

}
