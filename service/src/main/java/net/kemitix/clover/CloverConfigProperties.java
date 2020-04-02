package net.kemitix.clover;

import lombok.Getter;
import net.kemitix.clover.images.CloverConfig;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.Dependent;
import java.io.File;
import java.util.Arrays;
import java.util.List;

@Dependent
@Getter
public class CloverConfigProperties implements CloverConfig {

    @ConfigProperty(name = "base-dir")
    public String baseDir;
    @ConfigProperty(name = "config-file")
    String configFile;
    @ConfigProperty(name = "image-types")
    String imageTypes;
    @ConfigProperty(name = "issue-dir")
    String issueDir;
    @ConfigProperty(name = "width")
    int width;
    @ConfigProperty(name = "height")
    int height;
    @ConfigProperty(name = "inches-to-px")
    int inchesToPX;
    @ConfigProperty(name = "drop-shadow-x-offset")
    int dropShadowXOffset;
    @ConfigProperty(name = "drop-shadow-y-offset")
    int dropShadowYOffset;
    @ConfigProperty(name = "font-file")
    String fontFile;

    @Override
    public List<String> getImageTypes() {
        return Arrays.asList(imageTypes.split(","));
    }

    @Override
    public int height() {
        return height * inchesToPX;
    }

    @Override
    public int width() {
        return width * inchesToPX;
    }

    @Override
    public File getFontFile() {
        return new File(fontFile);
    }
}
