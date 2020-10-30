package net.kemitix.clover.issue;

import net.kemitix.clover.spi.ArtCredit;
import net.kemitix.clover.spi.CloverProperties;
import net.kemitix.fontface.FontFace;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class ArtCreditProducers {

    @Produces
    @ArtCredit
    FontFace spineFontFace(
            CloverProperties cloverProperties
    ) {
        return FontFace.of(
                cloverProperties.getFontLocation(),
                36,
                "white",
                cloverProperties.getDropShadowXOffset(),
                cloverProperties.getDropShadowYOffset());
    }

}
