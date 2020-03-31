package net.kemitix.cossmass.clover;

import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.util.concurrent.Executors;

@ApplicationScoped
public class CloverMain {

    private final CloverService service;

    public CloverMain(final CloverService service) {
        this.service = service;
    }

    void onStart(@Observes final StartupEvent ev) throws InterruptedException {
        Executors.newSingleThreadExecutor()
                .submit(() -> {
                    try {
                        service.run();
                    } finally {
                        System.exit(0);
                    }
                });
    }

}
