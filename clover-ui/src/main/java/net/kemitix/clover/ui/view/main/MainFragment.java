package net.kemitix.clover.ui.view.main;

import net.kemitix.clover.ui.AbstractFragment;
import net.kemitix.clover.ui.Fragment;
import net.kemitix.clover.ui.view.menu.MenuFragment;
import net.kemitix.clover.ui.view.preview.PreviewFragment;

import java.util.Map;

public class MainFragment
        extends AbstractFragment<MainController, MainView> {

    public static final FragmentName PART_PREVIEW = FragmentName.define();
    public static final FragmentName PART_MENU = FragmentName.define();

    private final PreviewFragment previewFragment = new PreviewFragment();
    private final MenuFragment menuFragment = new MenuFragment();

    public MainFragment() {
        super(
                new MainController(),
                new MainView()
        );
    }

    @Override
    public Map<FragmentName, Fragment<?, ?>> getChildFragments() {
        return Map.of(
                PART_PREVIEW, previewFragment,
                PART_MENU, menuFragment
        );
    }
}
