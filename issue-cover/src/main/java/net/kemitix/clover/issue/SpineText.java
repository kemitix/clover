package net.kemitix.clover.issue;

import net.kemitix.clover.spi.*;

import javax.annotation.PostConstruct;
import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.awt.*;
import java.util.function.Supplier;
import java.util.logging.Logger;

@Spine
@Priority(20)
@ApplicationScoped
public class SpineText implements Element<Graphics2D> {

    private static final Logger LOG =
            Logger.getLogger(
                    SpineText.class.getName());

    @Inject
    @Issue
    Dimensions dimensions;
    @Inject
    Colours colours;
    @Inject
    RotatedCenteredTextEffect<Graphics2D> rotatedCenteredTextEffect;

    @Inject @Spine
    Supplier<String> spineText;

    @Inject @Spine
    FontFace fontFace;

    @Override
    public void draw(Graphics2D graphics2D) {
        String text = spineText.get();
        LOG.info("Draw Spine Text: " + text);
        rotatedCenteredTextEffect.fontFace(fontFace)
                .region(dimensions.getSpineCrop()
                        .withOffset(0, (int) (-fontFace.getSize() * 0.8)))
                .text(text)
                .apply(graphics2D);
    }
}
