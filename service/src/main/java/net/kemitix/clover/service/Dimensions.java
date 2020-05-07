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
    private Region backCrop;
    private Region wrapCrop;
    private Region scaledCoverArt;
    private Region frontWithinWrapCrop;

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
        frontCrop = kindleCover.toBuilder()
                .top((int) (topFrontCoverOriginal * scaleFromOriginal))
                .left((int) (leftFrontCoverOriginal * scaleFromOriginal)).build();
        int spineWidth = (int) (spineWidthInches * dpi);
        spineCrop = frontCrop.toBuilder()
                .left((int) (frontCrop.getLeft() - spineWidth))
                .width((int) spineWidth).build();
        backCrop = frontCrop.toBuilder()
                .left((int) (frontCrop.getLeft() - spineWidth - frontCrop.getWidth()))
                .build();
        wrapCrop = backCrop.toBuilder()
                .width(backCrop.getWidth() + spineCrop.getWidth() + frontCrop.getWidth())
                .build();
        frontWithinWrapCrop = Region.builder()
                .top(0)
                .left((int) (kindleCover.getWidth() + spineWidth))
                .width(kindleCover.getWidth())
                .height(wrapCrop.getHeight()).build();
        wrapCrop.mustContain(frontWithinWrapCrop);
        wrapCrop.mustContain(frontCrop);
        wrapCrop.mustContain(spineCrop);
        wrapCrop.mustContain(backCrop);
        scaledCoverArt.mustContain(wrapCrop);
    }
}
