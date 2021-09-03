package net.kemitix.clover.issue;

import net.kemitix.clover.spi.*;
import net.kemitix.fontface.FontFace;

import javax.enterprise.inject.Produces;
import java.awt.*;
import java.util.function.Supplier;

public class SpineProducers {

    @Produces @Spine
    Supplier<String> text(IssueConfig issueConfig) {
        return issueConfig::getSpineText;
    }

    @Produces @Spine
    Paint paint(Colours colours) {
        return colours.getColor("black");
    }

    @Produces @Spine
    FontFace spineFontFace(
            CloverProperties cloverProperties,
            IssueConfig issueConfig
    ) {
        final String colour =
                switch (issueConfig.getType()) {
                    case ISSUE -> "yellow";
                    case YEAR -> "#b82248";
                };
        return FontFace.of(
                cloverProperties.getFontLocation(),
                62,
                colour,
                cloverProperties.dropShadowXOffset(),
                cloverProperties.dropShadowYOffset());
    }
}
