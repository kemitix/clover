package net.kemitix.clover.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import net.kemitix.clover.spi.CloverFormat;

@ApplicationScoped
public class CloverService implements Runnable {

    @Inject Instance<CloverFormat> formats;
    @Inject FormatWriter formatWriter;

    @Override
    public void run() {
        formats.stream()
                .filter(CloverFormat::isEnabled)
                .forEach(format -> formatWriter.write(format));
    }
}
