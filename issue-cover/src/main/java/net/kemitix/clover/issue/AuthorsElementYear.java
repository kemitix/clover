package net.kemitix.clover.issue;

import lombok.Getter;
import lombok.extern.java.Log;
import net.kemitix.clover.spi.FrontCover;
import net.kemitix.clover.spi.IssueConfig;
import net.kemitix.clover.spi.IssueDimensions;
import net.kemitix.clover.spi.IssueType;
import net.kemitix.clover.spi.SimpleTextEffect;
import net.kemitix.fontface.FontFace;
import net.kemitix.properties.typed.TypedProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.awt.*;
import java.util.List;

@Log
@FrontCover
@ApplicationScoped
public class AuthorsElementYear extends AuthorsElementIssue {

    @Getter
    private final int priority = 50;

    @Inject
    IssueConfig issueConfig;
    @Inject
    SimpleTextEffect<Graphics2D> simpleTextEffect;
    @Inject @FrontCover
    FontFace fontFace;
    @Inject
    IssueDimensions issueDimensions;

    @Override
    public void draw(Graphics2D drawable, TypedProperties typedProperties) {
        if (IssueType.YEAR.equals(issueConfig.getType())) {
            doDraw(drawable);
        }
    }

    @Override
    protected String authorNames() {
        List<String> authors = issueConfig.authors();
        int lastIndex = authors.size() - 1;
        return String.join(", ", authors.subList(0, lastIndex))
                + " and " + authors.get(lastIndex);
    }

}
