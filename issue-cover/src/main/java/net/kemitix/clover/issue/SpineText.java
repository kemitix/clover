package net.kemitix.clover.issue;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import net.kemitix.clover.spi.AbstractElement;
import net.kemitix.clover.spi.IssueConfig;
import net.kemitix.clover.spi.IssueDimensions;
import net.kemitix.clover.spi.RotatedCenteredTextEffect;
import net.kemitix.clover.spi.Spine;
import net.kemitix.clover.spi.TextEffect;
import net.kemitix.fontface.FontFace;
import net.kemitix.properties.typed.TypedProperties;

import java.awt.*;
import java.util.function.Supplier;

@Spine
@ApplicationScoped
public class SpineText extends AbstractElement {

    @Getter private final int priority = 20;

    @Inject
    IssueDimensions dimensions;
    @Inject RotatedCenteredTextEffect<Graphics2D> rotatedCenteredTextEffect;
    @Inject @Spine Supplier<String> spineText;
    @Inject @Spine FontFace fontFace;
    @Inject IssueConfig issueConfig;

    @Override
    public void draw(Graphics2D graphics2D, TypedProperties typedProperties) {
        String text = spineText.get();
        int yOffset = (int) switch (issueConfig.getType()) {
            case ISSUE -> -fontFace.getSize() * 0.8;
            case YEAR -> -fontFace.getSize() * 0.4;
        };
        rotatedCenteredTextEffect.fontFace(fontFace)
                .text(text)
                .vAlign(TextEffect.VAlignment.CENTRE)
                .hAlign(TextEffect.HAlignment.CENTRE)
                .region(dimensions.getSpineCrop().withOffset(0, yOffset))
                .accept(graphics2D);
    }
}
