package net.kemitix.cossmass.clover;

import net.kemitix.cossmass.clover.images.CloverConfig;
import net.kemitix.cossmass.clover.images.Image;
import net.kemitix.cossmass.clover.images.ImageService;

import javax.enterprise.context.Dependent;
import java.util.function.Function;
import java.util.logging.Logger;

@Dependent
public class PaperbackPreview extends Paperback {
    private static final Logger LOGGER =
            Logger.getLogger(
                    Kindle.class.getName());

    protected PaperbackPreview(
            final CloverConfig config,
            final Issue issue,
            final ImageService imageService
    ) {
        super(config, issue, imageService);
        LOGGER.info("PaperbackPreview");
    }

    @Override
    protected String getName() {
        return super.getName() + "-preview";
    }

    @Override
    protected Function<Image, Image> backCover() {
        return super.backCover()
                .andThen(image -> {
                    //TODO: draw barcode space indicator
                    return image;
                });
    }
}