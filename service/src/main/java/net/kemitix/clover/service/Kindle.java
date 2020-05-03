package net.kemitix.clover.service;

import lombok.Getter;
import net.kemitix.clover.spi.CloverProperties;
import net.kemitix.clover.spi.images.ImageService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.logging.Logger;

@ApplicationScoped
public class Kindle extends FrontCoverFormat {

    private static final Logger LOGGER =
            Logger.getLogger(
                    Kindle.class.getName());
    @Getter
    private Issue issue;
    @Getter
    private ImageService imageService;
    private CloverProperties config;

    public Kindle() {
    }

    @Inject
    protected Kindle(
            final CloverProperties config,
            final Issue issue,
            final ImageService imageService
    ) {
        this.config = config;
        this.issue = issue;
        this.imageService = imageService;
    }

    @Override
    protected CloverProperties getCloverConfig() {
        return config;
    }

    @Override
    protected int getHeight() {
        return config.height();
    }

    @Override
    protected int getWidth() {
        return config.width();
    }

    @Override
    protected int frontPageXOffset() {
        return 0;
    }

    @Override
    protected int getCropYOffset() {
        return issue.getKindleYOffset();
    }

    @Override
    protected int getCropXOffset() {
        return issue.getKindleXOffset();
    }

    @Override
    protected String getName() {
        return "kindle";
    }

    @Override
    protected float writeScale() {
        return 1;
    }
}
