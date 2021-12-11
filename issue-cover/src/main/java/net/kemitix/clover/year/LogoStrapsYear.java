package net.kemitix.clover.year;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import net.kemitix.clover.issue.LogoStrapsIssue;
import net.kemitix.clover.spi.FrontCover;
import net.kemitix.clover.spi.IssueConfig;
import net.kemitix.clover.spi.IssueDimensions;
import net.kemitix.clover.spi.IssueType;
import net.kemitix.clover.spi.SimpleTextEffect;
import net.kemitix.clover.spi.TextEffect;
import net.kemitix.fontface.FontFace;
import net.kemitix.properties.typed.TypedProperties;

import java.awt.*;
import java.util.function.Consumer;

@FrontCover
@ApplicationScoped
public class LogoStrapsYear extends LogoStrapsIssue {

    private static final int BOTTOM_STRAP_TOP = 484;
    private static final int STRAP_SIDE_MARGIN = 30;
    private static final int STRAP_PADDING = 85;

    @Getter
    private final int priority = 20;

    @Inject
    IssueConfig issueConfig;
    @Inject
    SimpleTextEffect<Graphics2D> simpleTextEffect;
    @Inject
    IssueDimensions issueDimensions;

    @Override
    public void draw(Graphics2D drawable, TypedProperties typedProperties) {
        if (IssueType.YEAR.equals(issueConfig.getType())) {
            super.doDraw(drawable);
        }
    }

    protected Consumer<Graphics2D> bottomRightStrap(FontFace fontFace) {
        return simpleTextEffect.fontFace(fontFace)
                .text(String.format("The %s Year", issueConfig.getIssue()))
                .vAlign(TextEffect.VAlignment.TOP)
                .hAlign(TextEffect.HAlignment.RIGHT)
                .region(issueDimensions.getFrontCrop()
                        .withTop(BOTTOM_STRAP_TOP)
                        .withWidth(w -> w - STRAP_SIDE_MARGIN)
                        .withPadding(STRAP_PADDING))
                ;
    }

    @Override
    protected Consumer<Graphics2D> bottomLeftStrap(FontFace fontFace) {
        return simpleTextEffect.fontFace(fontFace)
                .text(issueConfig.getDate())
                .vAlign(TextEffect.VAlignment.TOP)
                .hAlign(TextEffect.HAlignment.LEFT)
                .region(issueDimensions.getFrontCrop()
                        .withTop(BOTTOM_STRAP_TOP)
                        .withLeft(l -> l + STRAP_SIDE_MARGIN)
                        .withPadding(STRAP_PADDING))
                ;
    }

}
