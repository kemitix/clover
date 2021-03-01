package net.kemitix.clover.ui.view.preview;

import net.kemitix.clover.ui.AbstractFragment;
import net.kemitix.clover.ui.Fragment;

import java.util.Map;

public class PreviewFragment
        extends AbstractFragment<PreviewController, PreviewView> {

    public PreviewFragment() {
        super(
                new PreviewController(),
                new PreviewView()
        );
    }

    @Override
    public Map<FragmentName, Fragment<?, ?>> getChildFragments() {
        return Map.of();
    }
}
