package net.kemitix.clover.issue;

import lombok.Getter;
import lombok.extern.java.Log;
import net.kemitix.clover.spi.*;
import net.kemitix.fontface.FontFace;
import net.kemitix.properties.typed.TypedProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.awt.*;

@Log
@FrontCover
@ApplicationScoped
public class AuthorsElementIssue extends AbstractElement {

    @Getter private final int priority = 50;

    @Inject IssueConfig issueConfig;
    @Inject SimpleTextEffect<Graphics2D> simpleTextEffect;
    @Inject @FrontCover FontFace fontFace;
    @Inject IssueDimensions issueDimensions;

    @Override
    public void draw(Graphics2D drawable, TypedProperties typedProperties) {
        if (IssueType.ISSUE.equals(issueConfig.getType())) {
            doDraw(drawable);
        }
    }

    protected void doDraw(Graphics2D drawable) {
        log.info("Drawing author list");
        switch (issueConfig.getAuthors().getAlignment()) {
            case CENTRE: drawCenterAligned(drawable);break;
            case LEFT: drawLeftAligned(drawable);break;
            case RIGHT: {throw new UnsupportedOperationException("Right Aligned text not available");}
        }
    }

    protected void drawCenterAligned(Graphics2D drawable) {
        final AuthorsConfig authors = issueConfig.getAuthors();
        int top = authors.getTop();
        int width = authors.getWidth();
        int left = issueDimensions.getFrontCrop().getLeft()
                + (issueDimensions.getFrontCrop().getWidth() / 2)
                - (width / 2);
        Region region = Region.builder()
                .top(top)
                .left(left)
                .width(width)
                .build();
        simpleTextEffect.fontFace(fontFace)
                .wrap()
                .text(authorNames())
                .vAlign(TextEffect.VAlignment.TOP)
                .hAlign(TextEffect.HAlignment.CENTRE)
                .region(region)
                .accept(drawable);
    }

    protected void drawLeftAligned(Graphics2D drawable) {
        AuthorsConfig authors = issueConfig.getAuthors();
        int top = authors.getTop();
        int left = authors.getLeft() +
                issueDimensions.getFrontCrop().getLeft();
        simpleTextEffect.fontFace(fontFace)
                .wrap()
                .text(authorNames())
                .vAlign(TextEffect.VAlignment.TOP)
                .hAlign(TextEffect.HAlignment.LEFT)
                .region(region(top, left))
                .accept(drawable);
    }

    protected String authorNames() {
        return String.join("\n", issueConfig.authors());
    }

    protected Region region(int top, int left) {
        Region frontCrop = issueDimensions.getFrontCrop();
        return frontCrop
                .withTop(top)
                .withLeft(left)
                .withBottom(frontCrop.getBottom())
                .withWidth(frontCrop.getRight() - left);
    }

}
