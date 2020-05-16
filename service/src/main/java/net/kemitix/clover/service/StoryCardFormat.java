package net.kemitix.clover.service;

import lombok.Getter;
import net.kemitix.clover.spi.*;
import net.kemitix.clover.spi.images.Image;
import net.kemitix.clover.spi.images.Region;
import net.kemitix.properties.typed.TypedProperties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@ApplicationScoped
public class StoryCardFormat implements CloverFormat {

    private IssueConfig issueConfig;
    private Image coverArtImage;
    private Dimensions dimensions;
    private BiFunction<IssueConfig.Story, Image, Image> storyCard;
    @Getter
    private List<Image> images;
    private StoryCardBranding storyCardBranding;

    public StoryCardFormat() {
    }

    @Inject
    public StoryCardFormat(
            IssueConfig issueConfig,
            Image coverArtImage,
            Dimensions dimensions,
            BiFunction<IssueConfig.Story, Image, Image> storyCard,
            StoryCardBranding storyCardBranding
    ) {
        this.issueConfig = issueConfig;
        this.coverArtImage = coverArtImage;
        this.dimensions = dimensions;
        this.storyCard = storyCard;
        this.storyCardBranding = storyCardBranding;
    }

    @PostConstruct
    public void init() {
        images = issueConfig.getStories().stream()
                .filter(story -> story.getTitle().contains("Study"))
                .map(story ->
                        storyCard.apply(story,
                                rescale(dimensions.getScaleFromOriginal())
                                        .andThen(crop(dimensions.getWrapCrop()))
                                        .andThen(storyCardBranding)
                                        .apply(coverArtImage)))
                .collect(Collectors.toList());
    }

    @Override
    public String getName() {
        return "story";
    }

    @Override
    public TypedProperties getImageProperties() {
        Region wrapCrop = dimensions.getWrapCrop();
        return TypedProperties.create()
                .with(PdfWidth.class, wrapCrop.getWidth())
                .with(PdfHeight.class, wrapCrop.getHeight());
    }
}
