package net.kemitix.clover.issue;

import lombok.Getter;
import net.kemitix.clover.spi.*;
import net.kemitix.fontface.FontFace;
import net.kemitix.properties.typed.TypedProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.awt.*;
import java.util.function.Supplier;
import java.util.logging.Logger;

@Spine
@ApplicationScoped
public class SpineText extends AbstractElement {

    private static final Logger LOG =
            Logger.getLogger(
                    SpineText.class.getName());

    @Getter private final int priority = 20;

    @Inject IssueDimensions dimensions;
    @Inject RotatedCenteredTextEffect<Graphics2D> rotatedCenteredTextEffect;
    @Inject @Spine Supplier<String> spineText;
    @Inject @Spine FontFace fontFace;

    @Override
    public void draw(Graphics2D graphics2D, TypedProperties typedProperties) {
        String text = spineText.get();
        LOG.info("Draw Spine Text: " + text);
        rotatedCenteredTextEffect.fontFace(fontFace)
                .text(text)
                .vAlign(TextEffect.VAlignment.CENTRE)
                .hAlign(TextEffect.HAlignment.CENTRE)
                .region(dimensions.getSpineCrop()
                        .withOffset(0, (int) (-fontFace.getSize() * 0.8)))
                .accept(graphics2D);
    }
}
