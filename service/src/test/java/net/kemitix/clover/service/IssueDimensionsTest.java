package net.kemitix.clover.service;

import net.kemitix.clover.spi.CloverProperties;
import net.kemitix.clover.spi.Image;
import net.kemitix.clover.spi.Region;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class IssueDimensionsTest
        implements WithAssertions {

    private final Image coverArtImage;
    private final CloverProperties cloverProperties;
    private final ServiceIssueConfig issueConfig;
    private IssueDimensionsImpl dimensions;

    public IssueDimensionsTest(
            @Mock Image coverArtImage,
            @Mock CloverProperties cloverProperties,
            @Mock ServiceIssueConfig issueConfig
    ) {
        this.coverArtImage = coverArtImage;
        this.cloverProperties = cloverProperties;
        this.issueConfig = issueConfig;
    }

    @BeforeEach
    public void init() {
        given(coverArtImage.getRegion())
                .willReturn(Region.builder()
                        .width(4000)
                        .height(2000).build());
        given(issueConfig.getWidth()).willReturn(5f);// inches
        given(issueConfig.getHeight()).willReturn(8f);// inches
        given(cloverProperties.dpi()).willReturn(320);// dots per inch
        given(issueConfig.getKindleXOffset()).willReturn(2500);
        given(issueConfig.getKindleYOffset()).willReturn(0);
        given(issueConfig.getFrontWidth()).willReturn(1250);
        given(issueConfig.getSpine()).willReturn(0.53f);//inches
        dimensions =
                new IssueDimensionsImpl(coverArtImage, cloverProperties, issueConfig);
    }

    @Test
    @DisplayName("Calculated dimensions fit within their containers")
    public void dimensionsFit() {
        assertThatCode(dimensions::init)
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Rescaled Cover Art")
    public void scaledCoverArtIsAsExpected() {
        //when
        dimensions.init();
        //then
        assertThat(dimensions.getScaledCoverArt())
                .isEqualTo(Region.builder()
                        .top(0)
                        .left(0)
                        .width(5120)
                        .height(2560).build());
    }

    @Test
    @DisplayName("Front Cover")
    public void frontCoverIsAsExpected() {
        //when
        dimensions.init();
        //then
        assertThat(dimensions.getFrontCrop())
                .isEqualTo(Region.builder()
                        .top(0)
                        .left(1769)
                        .width(1600)
                        .height(2560).build());
    }

    @Test
    @DisplayName("Spine")
    public void spineIsAsExpected() {
        //when
        dimensions.init();
        //then
        assertThat(dimensions.getSpineCrop())
                .isEqualTo(Region.builder()
                        .top(0)
                        .left(1600)
                        .width(169)
                        .height(2560).build());
    }

    @Test
    @DisplayName("Wrap Cover")
    public void wrapIsAsExpected() {
        //when
        dimensions.init();
        //then
        assertThat(dimensions.getWrapCrop())
                .isEqualTo(Region.builder()
                        .top(0)
                        .left(1431)
                        .width(3369)
                        .height(2560).build());
    }
}