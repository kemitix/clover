package net.kemitix.clover.image.effects;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import net.kemitix.text.fit.BoxFitter;
import net.kemitix.text.fit.TextFit;
import net.kemitix.text.fit.WordWrapper;

public class Producers {

    @Produces
    @ApplicationScoped
    WordWrapper wordWrapper() {
        return TextFit.wrapper();
    }

    @Produces
    @ApplicationScoped
    BoxFitter boxFitter() {
        return TextFit.fitter();
    }
}
