package net.kemitix.clover.service;

import lombok.Getter;
import lombok.extern.java.Log;
import net.kemitix.clover.spi.CloverProperties;
import net.kemitix.clover.spi.images.Area;
import net.kemitix.clover.spi.images.Image;
import net.kemitix.clover.spi.images.Region;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

@Log
@Getter
@ApplicationScoped
public class StoryCardDimensions {

    // Inputs
    private Region fullImageOriginal; // to size of the loaded image
    private Region cropRegion; // the region within the loaded image
    private Area cardSize; // the area to rescale the cropped region to
    private Region storyTitleRegion; // the region to fit the title into
    private Region authorRegion; // the region to fit the author name into
    private Region brandingRegion; // the region to fit the magazien title into
    private Region sampleRegion;// the region to fit the sample text into

    // Calculated
    private float scaleFromOriginal; // the factor to scale the cropRegion by

    public StoryCardDimensions() {
    }
    public StoryCardDimensions(
            Image coverArtImage,
            CloverProperties cloverProperties,
            IssueConfig issueConfig
    ) {
        fullImageOriginal = coverArtImage.getRegion();
        cropRegion = issueConfig.getCards().getCrop().getRegion();
        cardSize = cloverProperties.getCardSize();
        storyTitleRegion = cloverProperties.getCardTitleRegion();
        authorRegion = cloverProperties.getCardAuthorRegion();
        brandingRegion = cloverProperties.getCardBrandingRegion();
        sampleRegion = cloverProperties.getCardSampleRegion();
    }

    @PostConstruct
    public void init() {
        cropRegion.mustBeBiggerThan(Region.from(cardSize));
        scaleFromOriginal = cropRegion.getWidth() / cardSize.getWidth();
    }

}
