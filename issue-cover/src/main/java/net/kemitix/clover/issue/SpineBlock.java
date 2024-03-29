package net.kemitix.clover.issue;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import lombok.Getter;
import net.kemitix.clover.spi.*;

import java.awt.*;
import java.util.stream.Stream;

@ApplicationScoped
public class SpineBlock extends AbstractBlock {

    @Getter private final int priority = 30;

    @Inject @Spine Instance<Element<Graphics2D>> elements;

    @Override
    protected Stream<? extends Drawable<Graphics2D>> elements() {
        return elements.stream();
    }
}
