package net.kemitix.clover.ui.view.preview;

import net.kemitix.clover.ui.AbstractFragment;

public class PreviewFragment
        extends AbstractFragment<PreviewController, PreviewView> {

    public PreviewFragment() {
        super(
                new PreviewController(),
                new PreviewView()
        );
    }

}
