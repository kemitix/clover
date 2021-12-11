package net.kemitix.clover.issue;

import jakarta.enterprise.inject.Produces;
import net.kemitix.clover.spi.*;
import net.kemitix.fontface.FontFace;

public class FrontCoverProducers {

    @Produces
    @FrontCover
    FontFace backCoverFontFace(
            CloverProperties cloverProperties,
            IssueConfig issueConfig
    ) {
        return FontFace.of(
                cloverProperties.getFontLocation(),
                issueConfig.getFrontFontSize(),//48,//TODO move to clover.json
                issueConfig.getTextColour(),
                cloverProperties.dropShadowXOffset(),
                cloverProperties.dropShadowYOffset());
    }
}
