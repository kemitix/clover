package net.kemitix.clover.spi;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import net.kemitix.fontface.FontCache;
import net.kemitix.fontface.FontLoader;

@ApplicationScoped
public class FontFaceProvider {

    @Produces
    FontCache fontCache(FontLoader fontLoader) {
        return new FontCache(fontLoader);
    }

    @Produces
    FontLoader fontLoader() {
        return new FontLoader();
    }

}
