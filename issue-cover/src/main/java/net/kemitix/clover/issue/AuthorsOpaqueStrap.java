package net.kemitix.clover.issue;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import lombok.extern.java.Log;
import net.kemitix.clover.spi.*;
import net.kemitix.properties.typed.TypedProperties;

import java.awt.*;

@Log
@FrontCover
@ApplicationScoped
public class AuthorsOpaqueStrap
        extends AbstractElement {

    @Getter
    private final int priority = 5;

    @Inject OpaqueFill<Graphics2D> opaqueFill;
    @Inject IssueDimensions dimensions;
    @Inject AuthorStrapBox authorStrapBox;
    @Inject CloverProperties cloverProperties;

    @Override
    public void draw(
            Graphics2D drawable,
            TypedProperties typedProperties
    ) {
        log.info("AUTHOR-OPAQUE: " + authorStrapBox);
        if (authorStrapBox.isShow()) {
            fill(drawable,
                    authorStrapBox.getColour().getOpacity(),
                    authorStrapBox.getColour().getColour()
            );
        }
    }

    private Region getRegion() {
        int trim = dpi(cloverProperties.trimLeft());
        return dimensions.getFrontCrop()
                .withTop(authorStrapBox.getTop())
                .withHeight(authorStrapBox.getHeight())
                ;
    }

    private int dpi(float inches) {
        return (int) (inches * cloverProperties.dpi());
    }

    private void fill(
            Graphics2D drawable,
            double opacity,
            String colour
    ) {
        opaqueFill.opacity(opacity)
                .colour(colour)
                .region(getRegion())
                .accept(drawable);
    }

}
