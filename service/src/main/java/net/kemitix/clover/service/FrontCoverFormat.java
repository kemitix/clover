package net.kemitix.clover.service;

import net.kemitix.clover.spi.CloverProperties;
import net.kemitix.clover.spi.images.ImageService;

import java.util.logging.Logger;

public abstract class FrontCoverFormat extends AbstractCloverFormat {

    private static final Logger LOGGER =
            Logger.getLogger(
                    FrontCoverFormat.class.getName());

    private final Issue issue;
    private final CloverProperties config;

    protected FrontCoverFormat(
            final CloverProperties config,
            final Issue issue,
            final ImageService imageService
    ) {
        super(config, issue, imageService);
        this.config = config;
        this.issue = issue;
    }


}
