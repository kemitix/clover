package net.kemitix.clover.ui.fragment.body;

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
