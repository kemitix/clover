package net.kemitix.clover.ui.view.body;

import net.kemitix.clover.ui.AbstractFragment;

public class BodyFragment
        extends AbstractFragment<BodyController, BodyView> {

    public BodyFragment() {
        super(
                new BodyController(),
                new BodyView()
        );
    }

}
