package net.kemitix.clover;

import net.kemitix.clover.spi.CloverProperties;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class CloverPropertiesTest
        implements WithAssertions {

    private final CloverProperties cloverConfig = mock(CloverProperties.class);

    @Test
    @DisplayName("ImageTypes are parsed as comma-delimited")
    public void imageTypesParsing() {
        //given
        given(cloverConfig.imageTypes()).willReturn("alpha,beta,gamma");
        given(cloverConfig.getImageTypes()).willCallRealMethod();
        //when
        final List<String> imageTypes = cloverConfig.getImageTypes();
        //then
        assertThat(imageTypes)
                .containsExactly(
                        "alpha",
                        "beta",
                        "gamma");
    }

    @Test
    @DisplayName("Height has NOT been converted from inches to PX")
    public void heightIsInInches() {
        //given
        final Random random = new Random();
        final float height = random.nextInt();
        given(cloverConfig.height()).willReturn(height);
        //when
        final float result = cloverConfig.height();
        //then
        assertThat(result)
                .isEqualTo(height);
    }

    @Test
    @DisplayName("Width has NOT been converted from inches to PX")
    public void widthIsInInches() {
        //given
        final Random random = new Random();
        final float width = random.nextInt();
        given(cloverConfig.width()).willReturn(width);
        //when
        final float result = cloverConfig.width();
        //then
        assertThat(result)
                .isEqualTo(width);
    }

    @Test
    @DisplayName("Get plain values")
    public void getPlainValues() {
        //given
        final String issueDir = "~/cossmass/issue";
        System.setProperty("user.dir", issueDir);
        final Random random = new Random();
        final float width = random.nextInt();
        final float height = random.nextInt();
        final int dpi = random.nextInt();
        final int dropShadowXOffset = random.nextInt();
        final int dropShadowYOffset = random.nextInt();
        //when
        final CloverProperties config = mock(CloverProperties.class);
        given(config.issueDir()).willCallRealMethod();
        given(config.width()).willReturn(width);
        given(config.height()).willReturn(height);
        given(config.dpi()).willReturn(dpi);
        given(config.dropShadowXOffset()).willReturn(dropShadowXOffset);
        given(config.dropShadowYOffset()).willReturn(dropShadowYOffset);
        //then
        SoftAssertions.assertSoftly(s -> {
            String configIssueDir = config.issueDir();
            s.assertThat(configIssueDir).isEqualTo(issueDir);
            s.assertThat(config.width()).isEqualTo(width);
            s.assertThat(config.height()).isEqualTo(height);
            s.assertThat(config.dpi()).isEqualTo(dpi);
            s.assertThat(config.dropShadowXOffset()).isEqualTo(dropShadowXOffset);
            s.assertThat(config.dropShadowYOffset()).isEqualTo(dropShadowYOffset);
        });
    }
}