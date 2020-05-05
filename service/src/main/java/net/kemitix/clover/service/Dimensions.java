package net.kemitix.clover.service;

import lombok.Getter;
import net.kemitix.clover.spi.CloverProperties;
import net.kemitix.clover.spi.images.Image;
import net.kemitix.clover.spi.images.Region;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

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

    public Dimensions() {
    }

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
                .width(kindleWidthInches * dpi)
                .height(kindleHeightInches * dpi).build();
        scaleFromOriginal = kindleCover.getWidth() / widthFrontCoverOriginal;
        scaledCoverArt = fullImageOriginal.toBuilder()
                .width(fullImageOriginal.getWidth() * scaleFromOriginal)
                .height(fullImageOriginal.getHeight() * scaleFromOriginal)
                .build();
        frontCrop = kindleCover.toBuilder()
                .top(topFrontCoverOriginal * scaleFromOriginal)
                .left(leftFrontCoverOriginal * scaleFromOriginal).build();
        float spineWidth = spineWidthInches * dpi;
        spineCrop = frontCrop.toBuilder()
                .left(frontCrop.getLeft() - spineWidth)
                .width(spineWidth).build();
        backCrop = frontCrop.toBuilder()
                .left(frontCrop.getLeft() - spineWidth - frontCrop.getWidth())
                .build();
        wrapCrop = backCrop.toBuilder()
                .width(backCrop.getWidth() + spineCrop.getWidth() + frontCrop.getWidth())
                .build();
        wrapCrop.mustContain(frontCrop);
        wrapCrop.mustContain(spineCrop);
        wrapCrop.mustContain(backCrop);
        scaledCoverArt.mustContain(wrapCrop);
    }
}
