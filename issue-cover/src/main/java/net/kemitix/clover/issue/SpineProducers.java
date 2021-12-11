package net.kemitix.clover.issue;

import jakarta.enterprise.inject.Produces;
import net.kemitix.clover.spi.*;
import net.kemitix.fontface.FontFace;

import java.awt.*;
import java.util.function.Supplier;

public class SpineProducers {

    @Produces
    @Spine
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
        String colour =
                switch (issueConfig.getType()) {
                    case ISSUE -> "yellow";
                    case YEAR -> "lightblue";
                };
        int size =
                switch (issueConfig.getType()) {
                    case ISSUE -> 62;
                    case YEAR -> 124;
                };
        return FontFace.of(
                cloverProperties.getFontLocation(),
                size,
                colour,
                cloverProperties.dropShadowXOffset(),
                cloverProperties.dropShadowYOffset());
    }
}
