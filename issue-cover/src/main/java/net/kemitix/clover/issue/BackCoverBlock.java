package net.kemitix.clover.issue;

import lombok.Getter;
import net.kemitix.clover.spi.*;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.awt.*;

@ApplicationScoped
public class BackCoverBlock implements Block<Graphics2D> {

    @Getter
    private final int priority = 20;

    @Inject @BackCover Instance<Element<Graphics2D>> elements;

    @Override
    public void draw(Graphics2D drawable) {
        Drawable.draw(elements, drawable);
    }

}
