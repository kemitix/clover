package net.kemitix.cossmass.clover;

import net.kemitix.cossmass.clover.images.CloverConfig;
import net.kemitix.cossmass.clover.images.ImageService;

import javax.enterprise.context.Dependent;
import java.util.logging.Logger;

@Dependent
public class Kindle extends CloverFormat {

    private static final Logger LOGGER =
            Logger.getLogger(
                    Kindle.class.getName());
    private final Issue issue;

    protected Kindle(
            final CloverConfig config,
            final Issue issue,
            final ImageService imageService
    ) {
        super(config, issue, imageService);
        this.issue = issue;
        LOGGER.info("Kindle");
    }

    @Override
    protected int getCropYOffset() {
        return issue.kindleYOffset;
    }

    @Override
    protected int getCropXOffset() {
        return issue.kindleXOffset;
    }
}
