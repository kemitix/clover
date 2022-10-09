package net.kemitix.clover.year;

import jakarta.enterprise.context.ApplicationScoped;
import net.kemitix.clover.spi.AbstractLogoStraps;
import net.kemitix.clover.spi.FrontCover;
import net.kemitix.clover.spi.TextEffect;
import net.kemitix.fontface.FontFace;

import java.awt.*;
import java.util.function.Consumer;

@FrontCover
@ApplicationScoped
public class LogoStrapsYear extends AbstractLogoStraps
        implements ForYearCovers {

    private static final int BOTTOM_STRAP_TOP = 484;
    private static final int STRAP_SIDE_MARGIN = 30;
    private static final int STRAP_PADDING = 85;

    protected Consumer<Graphics2D> bottomRightStrap(FontFace fontFace) {
        return getSimpleTextEffect().fontFace(fontFace)
                .text(String.format("The %s Year", getIssueConfig().getIssue()))
                .vAlign(TextEffect.VAlignment.TOP)
                .hAlign(TextEffect.HAlignment.RIGHT)
                .region(getIssueDimensions().getFrontCrop()
                        .withTop(BOTTOM_STRAP_TOP)
                        .withWidth(w -> w - STRAP_SIDE_MARGIN)
                        .withPadding(STRAP_PADDING))
                ;
    }

    @Override
    protected Consumer<Graphics2D> bottomLeftStrap(FontFace fontFace) {
        return getSimpleTextEffect().fontFace(fontFace)
                .text(getIssueConfig().getDate())
                .vAlign(TextEffect.VAlignment.TOP)
                .hAlign(TextEffect.HAlignment.LEFT)
                .region(getIssueDimensions().getFrontCrop()
                        .withTop(BOTTOM_STRAP_TOP)
                        .withLeft(l -> l + STRAP_SIDE_MARGIN)
                        .withPadding(STRAP_PADDING))
                ;
    }

}
