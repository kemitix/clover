package net.kemitix.clover;

import org.jboss.weld.environment.se.Weld;
import net.kemitix.clover.service.CloverService;

public class CloverMain {

    public static void main(String[] args) {
        try (var container = new Weld().initialize()) {
            container.select(CloverService.class)
                    .get()
                    .run();
        }
    }

}
