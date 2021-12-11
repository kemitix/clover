package net.kemitix.clover.issue;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import net.kemitix.clover.spi.ArtCredit;
import net.kemitix.clover.spi.CloverProperties;
import net.kemitix.clover.spi.IssueConfig;
import net.kemitix.fontface.FontFace;

@ApplicationScoped
public class ArtCreditProducers {

    @Produces
    @ArtCredit
    FontFace spineFontFace(
            CloverProperties cloverProperties,
            IssueConfig issueConfig
    ) {
        int size = switch (issueConfig.getType()) {
            case ISSUE -> 36;
            case YEAR -> 48;
        };
        return FontFace.of(
                cloverProperties.getFontLocation(),
                size,
                "white",
                cloverProperties.dropShadowXOffset(),
                cloverProperties.dropShadowYOffset());
    }

}
