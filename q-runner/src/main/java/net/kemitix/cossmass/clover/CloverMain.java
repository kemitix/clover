package net.kemitix.cossmass.clover;

import io.quarkus.runtime.StartupEvent;
import net.kemitix.cossmass.clover.images.FatalCloverError;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

@ApplicationScoped
public class CloverMain {

    private static final Logger LOGGER =
            Logger.getLogger(
                    CloverMain.class.getName());

    private final CloverService service;

    public CloverMain(final CloverService service) {
        this.service = service;
    }

    void onStart(@Observes final StartupEvent ev) throws InterruptedException {
        Executors.newSingleThreadExecutor()
                .submit(() -> {
                    try {
                        service.run();
                    } catch (final FatalCloverError e) {
                        LOGGER.severe(e.getMessage());
                    } finally {
                        System.exit(0);
                    }
                });
    }

}
