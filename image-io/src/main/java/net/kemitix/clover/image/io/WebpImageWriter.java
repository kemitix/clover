package net.kemitix.clover.image.io;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import net.kemitix.clover.spi.CloverProperties;

@ApplicationScoped
public class WebpImageWriter
        extends AbstractImageIOImageWriter {

    private static final String FORMAT_NAME = "webp";

    @Inject
    CloverProperties cloverProperties;

    @Override
    public boolean accepts(final String format) {
        return FORMAT_NAME.equals(format);
    }

    @Override
    public boolean isEnabled() {
        return cloverProperties.enableWebp();
    }

    @Override
    protected String getFormatName() {
        return FORMAT_NAME;
    }

}
