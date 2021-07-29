package net.kemitix.clover.issue;

import lombok.Getter;
import net.kemitix.clover.spi.*;
import net.kemitix.fontface.FontFace;
import net.kemitix.properties.typed.TypedProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.awt.*;

@BackCover
@ApplicationScoped
public class ArtCreditBlock
        extends AbstractElement {

    @Getter
    private final int priority = 10;

    @Inject SimpleTextEffect<Graphics2D> simpleTextEffect;
    @Inject CloverProperties cloverProperties;
    @Inject IssueConfig issueConfig;
    @Inject @ArtCredit FontFace fontFace;

    @Override
    public void draw(Graphics2D drawable, TypedProperties typedProperties) {
        String text = "Cover art by\n" + issueConfig.getCoverArtist();
        simpleTextEffect.fontFace(fontFace)
                .text(text)
                .vAlign(TextEffect.VAlignment.CENTRE)
                .hAlign(TextEffect.HAlignment.CENTRE)
                .region(region())
                .accept(drawable);
    }

    private Region region() {
        final Region region = Region.builder()
                .top(cloverProperties.barcodeTop())
                .left(0)
                .width(cloverProperties.barcodeLeft())
                .height(cloverProperties.getBarcodeHeight())
                .build();
        return region.withMargin(20);
    }

}
