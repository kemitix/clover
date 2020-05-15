package net.kemitix.clover;

import io.quarkus.arc.config.ConfigProperties;
import lombok.Getter;
import lombok.Setter;
import net.kemitix.clover.spi.CloverProperties;
import net.kemitix.clover.spi.Area;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Setter
@Getter
@ConfigProperties(prefix = "clover")
public class ServiceCloverProperties implements CloverProperties {

    String configFile;
    String imageTypes;
    String issueDir;
    float width;
    float height;
    int dpi;
    int dropShadowXOffset;
    int dropShadowYOffset;
    String fontFile;
    int barcodeTop;
    int barcodeLeft;
    float barcodeWidth;
    float barcodeHeight;
    String barcodeFillColour;

    @Override
    public List<String> getImageTypes() {
        return Arrays.asList(imageTypes.split(","));
    }

    @Override
    public File getFontFile() {
        return new File(fontFile);
    }

    @Override
    public int getBarcodeWidth() {
        return (int) (barcodeWidth * dpi);
    }

    @Override
    public int getBarcodeHeight() {
        return (int) (barcodeHeight * dpi);
    }

    @Override
    @Deprecated
    public Area getKindleFrontArea() {
        return Area.builder()
                .width((int) getWidth())
                .height((int) getHeight()).build();
    }
}
