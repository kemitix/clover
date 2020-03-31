package net.kemitix.cossmass.clover.images.imglib;

import net.kemitix.cossmass.clover.images.CloverConfig;
import net.kemitix.cossmass.clover.images.Image;
import net.kemitix.cossmass.clover.images.ImageService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class CloverImageService implements ImageService {

    private static final Logger LOGGER =
            Logger.getLogger(
                    CloverImageService.class.getName());
    private final CloverConfig config;
    private final FontLoader fontLoader;

    public CloverImageService(
            final CloverConfig config,
            final FontLoader fontLoader
    ) {
        this.config = config;
        this.fontLoader = fontLoader;
    }

    @Override
    public Image load(final File file) throws IOException {
        LOGGER.info("Loading " + file);
        final BufferedImage image = ImageIO.read(file);
        LOGGER.info(String.format("Loaded: (%dx%d)",
                image.getWidth(),
                image.getHeight()));
        return new CloverImage(image, config, fontLoader);
    }
}
