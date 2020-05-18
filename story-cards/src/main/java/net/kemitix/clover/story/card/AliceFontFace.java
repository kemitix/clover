package net.kemitix.clover.story.card;

import net.kemitix.clover.spi.*;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.net.URI;
import java.net.URISyntaxException;

@StoryCard
@Named("alice")
@ApplicationScoped
public class AliceFontFace implements FontFace {

    private FontFace delegate;
    @Inject IssueConfig issueConfig;
    @Inject CloverProperties cloverProperties;

    @PostConstruct
    void init() throws URISyntaxException {
        delegate = FontFace.of(
                this.getClass()
                        .getResource("alice/Alice-Regular.ttf")
                        .toURI(),
                10,
                issueConfig.getTitleColour(),
                XY.at(0,0));
    }

    @Override
    public URI getFontLocation() {
        return delegate.getFontLocation();
    }

    @Override
    public int getSize() {
        return delegate.getSize();
    }

    @Override
    public String getColour() {
        return delegate.getColour();
    }

    @Override
    public String getShadowColour() {
        return delegate.getShadowColour();
    }

    @Override
    public XY getShadowOffset() {
        return delegate.getShadowOffset();
    }

    @Override
    public FontFace withSize(int size) {
        return delegate.withSize(size);
    }
}
