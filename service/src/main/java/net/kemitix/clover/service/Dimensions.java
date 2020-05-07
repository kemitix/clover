package net.kemitix.clover.service;

import lombok.Getter;
import net.kemitix.clover.spi.CloverProperties;
import net.kemitix.clover.spi.images.Image;
import net.kemitix.clover.spi.images.Region;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Getter
@ApplicationScoped
public class Dimensions {

    // Inputs
    private Region fullImageOriginal;
    private int topFrontCoverOriginal;
    private int leftFrontCoverOriginal;
    private float widthFrontCoverOriginal;
    private float dpi;
    private float kindleWidthInches;
    private float kindleHeightInches;
    private float spineWidthInches;
    // Calculated
    private float scaleFromOriginal;
    private Region frontCrop;
    private Region spineCrop;
    private Region wrapCrop;
    private Region scaledCoverArt;

    public Dimensions() {
    }

    @Inject
    public Dimensions(
            Image coverArtImage,
            CloverProperties cloverProperties,
            IssueConfig issueConfig
    ) {
        this.fullImageOriginal = coverArtImage.getRegion();
        this.topFrontCoverOriginal = issueConfig.getKindleYOffset();
        this.leftFrontCoverOriginal = issueConfig.getKindleXOffset();
        this.kindleWidthInches = cloverProperties.getWidth();
        this.kindleHeightInches = cloverProperties.getHeight();
        this.widthFrontCoverOriginal = issueConfig.getFrontWidth();
        this.dpi = cloverProperties.getDpi();
        this.spineWidthInches = issueConfig.getSpine();
    }

    @PostConstruct
    public void init() {
        Region kindleCover = Region.builder()
                .width((int) (kindleWidthInches * dpi))
                .height((int) (kindleHeightInches * dpi)).build();
        scaleFromOriginal = kindleCover.getWidth() / widthFrontCoverOriginal;
        scaledCoverArt = fullImageOriginal.toBuilder()
                .width((int) (fullImageOriginal.getWidth() * scaleFromOriginal))
                .height((int) (fullImageOriginal.getHeight() * scaleFromOriginal))
                .build();

        Region frontRegion = kindleCover.toBuilder()
                .top((int) (topFrontCoverOriginal * scaleFromOriginal))
                .left((int) (leftFrontCoverOriginal * scaleFromOriginal)).build();
        scaledCoverArt.mustContain(frontRegion);

        int spineWidth = (int) (spineWidthInches * dpi);

        // backCrop is relative to scaledCoverArt
        Region backRegion = frontRegion.toBuilder()
                .left(frontRegion.getLeft() - spineWidth - frontRegion.getWidth())
                .build();
        scaledCoverArt.mustContain(backRegion);

        // wrapCrop is relative to scaledCoverArt
        wrapCrop = backRegion.toBuilder()
                .width(backRegion.getWidth() + spineWidth + frontRegion.getWidth())
                .build();
        scaledCoverArt.mustContain(wrapCrop);

        // frontCrop is relative to backCrop
        frontCrop = Region.builder()
                .top(0)
                .left(kindleCover.getWidth() + spineWidth)
                .width(kindleCover.getWidth())
                .height(wrapCrop.getHeight()).build();
        wrapCrop.mustContain(frontCrop);

        // spineCrop is relative to backCrop
        spineCrop = frontCrop.toBuilder()
                .left(frontCrop.getLeft() - spineWidth)
                .width(spineWidth).build();
        wrapCrop.mustBeBiggerThan(spineCrop);
    }
}
