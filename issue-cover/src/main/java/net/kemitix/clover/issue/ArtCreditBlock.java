package net.kemitix.clover.issue;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import net.kemitix.clover.spi.*;
import net.kemitix.fontface.FontFace;
import net.kemitix.properties.typed.TypedProperties;

import java.awt.*;

@BackCover
@ApplicationScoped
public class ArtCreditBlock
        extends AbstractElement {

    @Inject @GuideLines BarcodeGuide barcodeGuide;

    @Getter
    private final int priority = 10;

    @Inject SimpleTextEffect<Graphics2D> simpleTextEffect;
    @Inject CloverProperties cloverProperties;
    @Inject IssueConfig issueConfig;
    @Inject @ArtCredit FontFace fontFace;
    @Inject IssueDimensions issueDimensions;

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
        int trim = dpi(cloverProperties.trimLeft());
        Region backCrop = issueDimensions.getFrontCrop().withLeft(trim);
        Region barcode = barcodeGuide.getBarcodeRegion();
        return barcode.withLeft(backCrop.getLeft())
                .withRight(barcode.getLeft())
                .withMargin(20);
    }

    private int dpi(float inches) {
        return (int) (inches * cloverProperties.dpi());
    }

}
