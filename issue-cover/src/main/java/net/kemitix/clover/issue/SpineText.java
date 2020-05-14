package net.kemitix.clover.issue;

import net.kemitix.clover.spi.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.awt.*;

@Spine
@ApplicationScoped
public class SpineText implements Element {

    @Inject
    @Issue
    Dimensions dimensions;
    @Inject
    Colours colours;
    @Inject
    RotatedCenteredTextEffect<Graphics2D> rotatedCenteredTextEffect;
    @Inject
    @Spine
    FontFace fontFace;
    @Inject
    IssueConfig issueConfig;

    @Override
    public void draw(Graphics2D graphics2D) {
        rotatedCenteredTextEffect.fontFace(fontFace)
                .region(dimensions.getSpineCrop()
                        .withOffset(0, (int) (-fontFace.getSize() * 0.8)))
                .text(issueConfig.getSpineText())
                .apply(graphics2D);
    }
}
