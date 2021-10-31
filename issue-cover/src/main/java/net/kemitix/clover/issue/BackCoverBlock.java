package net.kemitix.clover.issue;

import lombok.Getter;
import net.kemitix.clover.spi.*;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.awt.*;
import java.util.stream.Stream;

@ApplicationScoped
public class BackCoverBlock extends AbstractBlock {

    @Inject
    IssueConfig issueConfig;

    @Getter private final int priority = 20;

    @Inject @BackCover Instance<Element<Graphics2D>> elements;

    @Override
    protected Stream<? extends Drawable<Graphics2D>> elements() {
        return elements.stream()
                .filter(e -> e.typeFilter(issueConfig.getType()));
    }
}
