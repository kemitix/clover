package net.kemitix.clover.ui.view.main;

import lombok.Getter;
import net.kemitix.clover.ui.AbstractFragment;
import net.kemitix.clover.ui.Fragment;
import net.kemitix.clover.ui.view.menu.MenuFragment;
import net.kemitix.clover.ui.view.preview.PreviewFragment;

import java.util.Map;

public class MainFragment
        extends AbstractFragment<MainController, MainView> {

    public static final FragmentName PART_PREVIEW = FragmentName.define();
    public static final FragmentName PART_MENU = FragmentName.define();

    @Getter
    private final Map<FragmentName, Fragment<?, ?>> childFragments = Map.of(
            PART_PREVIEW, new PreviewFragment(),
            PART_MENU, new MenuFragment()
    );

    public MainFragment() {
        super(
                new MainController(),
                new MainView()
        );
    }

}
