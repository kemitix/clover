package net.kemitix.clover.issue;

import net.kemitix.clover.spi.*;
import net.kemitix.fontface.FontFace;

import javax.enterprise.inject.Produces;

public class BackCoverProducers {

    @Produces @BackCover
    FontFace backCoverFontFace(
            CloverProperties cloverProperties,
            IssueConfig issueConfig
    ) {
        return FontFace.of(
                cloverProperties.getFontLocation(),
                42,//TODO move to clover.json
                issueConfig.getTextColour(),
                cloverProperties.dropShadowXOffset(),
                cloverProperties.dropShadowYOffset());
    }

    @Produces
    BackCoverBackgroundBox backCoverOpaqueProperties(IssueConfig issueConfig) {
        return issueConfig.getBackCoverBackgroundBox();
    }
}
