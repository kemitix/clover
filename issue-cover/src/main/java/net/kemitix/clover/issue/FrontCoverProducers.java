package net.kemitix.clover.issue;

import net.kemitix.clover.spi.*;
import net.kemitix.fontface.FontFace;

import javax.enterprise.inject.Produces;

public class FrontCoverProducers {

    @Produces @FrontCover
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
