package net.kemitix.clover.spi;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithName;

import java.io.File;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@ConfigMapping(prefix = "clover")
public interface CloverProperties {

    String imageTypes();

    default List<String> getImageTypes() {
        return Arrays.asList(imageTypes().split(","));
    }

    /**
     * Front cover height in inches.
     */
    float height();

    /**
     * Front cover width in inches.
     */
    float width();

    default String issueDir() {
        return environment("CLOVER_DIR")
                .replaceFirst("^~", property("user.home"));
    }

    private String environment(String s) {
        return Objects.requireNonNull(
                System.getenv(s),
                "Undefined environment variable: " + s);
    }

    private String property(String s) {
        return Objects.requireNonNull(
                System.getProperty(s),
                "Undefined property: " + s);
    }

    String configFile();

    File fontFile();

    default URI getFontLocation() {
        return fontFile().toURI();
    }

    @WithName("drop-shadow-x-offset")
    int dropShadowXOffset();

    @WithName("drop-shadow-y-offset")
    int dropShadowYOffset();

    int dpi();

    int barcodeLeft();

    int barcodeTop();

    float barcodeWidth();

    default int getBarcodeWidth() {
        return (int) (barcodeWidth() * dpi());
    }

    float barcodeHeight();

    default int getBarcodeHeight() {
        return (int) (barcodeHeight() * dpi());
    }

    String barcodeFillColour();

    boolean enablePdf();
    boolean enableWebp();
    boolean enableJpg();

    boolean enableKindle();
    boolean enablePaperback();
    boolean enablePaperbackPreview();

    float trim();//inches

    int guideThickness();

    String trimColour();

    float trimTop();//inches

    float trimLeft();//inches

    float trimRight();//inches

    float trimBottom();//inches

    int guideLinesPerInch();
}
