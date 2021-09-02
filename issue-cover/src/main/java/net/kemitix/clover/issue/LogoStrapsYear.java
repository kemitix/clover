package net.kemitix.clover.issue;

import lombok.Getter;
import lombok.extern.java.Log;
import net.kemitix.clover.spi.CloverProperties;
import net.kemitix.clover.spi.FrontCover;
import net.kemitix.clover.spi.IssueConfig;
import net.kemitix.clover.spi.IssueDimensions;
import net.kemitix.clover.spi.IssueType;
import net.kemitix.clover.spi.Region;
import net.kemitix.clover.spi.SimpleTextEffect;
import net.kemitix.clover.spi.TextEffect;
import net.kemitix.fontface.FontFace;
import net.kemitix.properties.typed.TypedProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.awt.*;
import java.util.function.Consumer;

@Log
@FrontCover
@ApplicationScoped
public class LogoStrapsYear extends LogoStrapsIssue {

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
        int top = 390;
        return simpleTextEffect.fontFace(fontFace)
                .text(String.format("The %s Year", issueConfig.getIssue()))
                .vAlign(TextEffect.VAlignment.TOP)
                .hAlign(TextEffect.HAlignment.RIGHT)
                .region(issueDimensions.getFrontCrop()
                        .withTop(top)
                        .withWidth(w -> w - 30)
                        .withPadding(85))
                ;
    }

    @Override
    protected Consumer<Graphics2D> bottomLeftStrap(FontFace fontFace) {
        int top = 475;
        int left = 85;
        return simpleTextEffect.fontFace(fontFace)
                .text(issueConfig.getDate())
                .vAlign(TextEffect.VAlignment.TOP)
                .hAlign(TextEffect.HAlignment.LEFT)
                .region(Region.builder()
                        .top(top)
                        .left(left + issueDimensions.getFrontCrop().getLeft() + 30)
                        .width(issueDimensions.getFrontCrop().getWidth() -
                                (left + issueDimensions.getFrontCrop().getLeft()))
                        .height(issueDimensions.getFrontCrop().getHeight() - top)
                        .build())
                ;
    }

}
