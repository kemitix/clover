package net.kemitix.clover.service;

import net.kemitix.clover.spi.CloverProperties;
import net.kemitix.clover.spi.images.Image;
import net.kemitix.clover.spi.images.ImageService;
import net.kemitix.clover.spi.images.Region;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.nio.file.Paths;

@ApplicationScoped
public class FormatWriter {
    private CloverProperties cloverProperties;

    public FormatWriter() {
    }

    @Inject
    public FormatWriter(
            final CloverProperties cloverProperties
    ) {
        this.cloverProperties = cloverProperties;
    }

    public void write(final CloverFormat format) {
        format.getImages().forEach(image -> {
            image.write(Paths.get(cloverProperties.getIssueDir()),
                    format.getName() + image.getNameQualifier(),
                    format.getImageProperties());
        });
    }
}
