package net.kemitix.clover.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import net.kemitix.clover.spi.CloverFormat;
import net.kemitix.clover.spi.CloverProperties;

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
            image.write(Paths.get(cloverProperties.issueDir()),
                    format.getName() + image.getNameQualifier(),
                    format.getImageProperties());
        });
    }
}
