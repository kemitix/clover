package net.kemitix.clover.service;

import lombok.Getter;
import lombok.extern.java.Log;
import net.kemitix.clover.spi.*;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Log
@Getter
@ApplicationScoped
public class IssueDimensionsImpl implements IssueDimensions {

    // Inputs
    private Region fullImageOriginal;
    private int topFrontCoverOriginal;
    private int leftFrontCoverOriginal;
    private float widthFrontCoverOriginal;
    private float dpi;
    private float kindleWidthInches;
    private float kindleHeightInches;
    private float spineWidthInches;
    private float paperbackTrimInches;
    // Calculated
    private float scaleFromOriginal;
    private Region frontCrop;
    private Region backCrop;
    private Region spineCrop;
    private Region wrapCrop;
    private Region scaledCoverArt;
    private Region paperbackCover;
    private Region paperbackCoverWithTrim;

    public IssueDimensionsImpl() {
    }

    @Inject
    public IssueDimensionsImpl(
            Image coverArtImage,
            CloverProperties cloverProperties,
            IssueConfig issueConfig
    ) {
        this.fullImageOriginal = coverArtImage.getRegion();
        this.topFrontCoverOriginal = issueConfig.getKindleYOffset();
        this.leftFrontCoverOriginal = issueConfig.getKindleXOffset();
        this.kindleWidthInches = issueConfig.getWidth();
        this.kindleHeightInches = issueConfig.getHeight();
        this.widthFrontCoverOriginal = issueConfig.getFrontWidth();
        this.dpi = cloverProperties.dpi();
        this.spineWidthInches = issueConfig.getSpine();
        this.paperbackTrimInches = cloverProperties.trim();//0.25f;
    }

    @PostConstruct
    public void init() {
        // The area of the output Kindle Cover in pixels
        Region kindleCover = Region.builder()
                .width((int) (kindleWidthInches * dpi))
                .height((int) (kindleHeightInches * dpi)).build();
        // The selected Width for the front cover on the ORIGINAL image fits
        // both a minimum resolution and within the ORIGINAL image
        isBetween(widthFrontCoverOriginal, 1000, fullImageOriginal.getWidth());
        // Factor to scale the original image by to fit the output Kindle image size
        scaleFromOriginal = kindleCover.getWidth() / widthFrontCoverOriginal;
        // Dimensions of image once scaled for output resolution
        scaledCoverArt = fullImageOriginal.toBuilder()
                .width((int) (fullImageOriginal.getWidth() * scaleFromOriginal))
                .height((int) (fullImageOriginal.getHeight() * scaleFromOriginal))
                .build();
        // width of spine in pixels
        int spineWidth = (int) (spineWidthInches * dpi);
        // The area of the output Paperback Cover in pixels
        paperbackCover = kindleCover.toBuilder()
                .width((kindleCover.getWidth() * 2) + spineWidth)
                .build();
        // How much additional trim to include for Paperback in pixels
        int trim = (int) (paperbackTrimInches * dpi);
        // The area of the output Paperback Cover with Trim in pixels
        paperbackCoverWithTrim = paperbackCover.toBuilder()
                .width(paperbackCover.getWidth() + trim)
                .height(paperbackCover.getHeight() + trim)
                .build();
        scaledCoverArt.mustContain(paperbackCoverWithTrim);

        log.info("Select front cover region on scaled cover art");
        // Area on scaled image to be used for the Kindle Cover
        Region frontRegion = kindleCover.toBuilder()
                .top((int) (topFrontCoverOriginal * scaleFromOriginal))
                .left((int) (leftFrontCoverOriginal * scaleFromOriginal)).build();
        scaledCoverArt.mustContain(frontRegion);

        // backCrop is relative to scaledCoverArt
        log.info("Select back cover region on scaled cover art");
        // Area on scaled image to be used for the Back Cover of the Paperback
        backCrop = frontRegion
                .withLeft(left -> left - (spineWidth + frontRegion.getWidth()));
        scaledCoverArt.mustContain(backCrop);

        // wrapCrop is relative to scaledCoverArt
        log.info("Select spine region on scaled cover art");
        // Area of scaled image to be used for the PaperbackCover
        wrapCrop = backCrop.toBuilder()
                .width(backCrop.getWidth() + spineWidth + frontRegion.getWidth())
                .build();
        scaledCoverArt.mustContain(wrapCrop);

        // frontCrop is relative to backCrop
        log.info("Select front cover on wrap cover");
        // Area WITHIN wrapCrop for the front cover
        frontCrop = wrapCrop
                .withTop(0)
                .withLeft(kindleCover.getWidth() + spineWidth)
                .withWidth(kindleCover.getWidth());
        wrapCrop.mustContain(frontCrop.getArea());

        // spineCrop is relative to backCrop
        log.info("Select spine on wrap cover");
        // Area WITHIN wrapCrop for the spine
        spineCrop = frontCrop
                .withLeft(left -> left - spineWidth)
                .withWidth(spineWidth);
        wrapCrop.mustBeBiggerThan(spineCrop);
    }

    private void isBetween(float value, int min, int max) {
        if (value < min) {
            throw new IllegalStateException(String.format(
                    "Value %f is below minimum %d", value, min));
        }
        if (value > max) {
            throw new IllegalStateException(String.format(
                    "Value %f is above maximum %d", value, max));
        }
    }
}
