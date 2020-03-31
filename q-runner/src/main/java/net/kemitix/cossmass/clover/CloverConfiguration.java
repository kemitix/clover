package net.kemitix.cossmass.clover;

import net.kemitix.cossmass.clover.images.CloverConfig;
import net.kemitix.cossmass.clover.images.ImageService;
import net.kemitix.cossmass.clover.images.imglib.CloverImageService;
import net.kemitix.cossmass.clover.images.imglib.FontLoader;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

@Dependent
public class CloverConfiguration {

    private final CloverConfig config;

    public CloverConfiguration(final CloverConfig config) {
        this.config = config;
    }

    @Produces
    FontLoader fontLoader() {
        return new FontLoader();
    }

    @Produces
    ImageService imageService(final FontLoader fontLoader) {
        return new CloverImageService(config, fontLoader);
    }
}
