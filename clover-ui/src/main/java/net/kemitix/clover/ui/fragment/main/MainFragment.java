package net.kemitix.clover.ui.fragment.main;

import lombok.Getter;
import net.kemitix.clover.ui.AbstractFragment;
import net.kemitix.clover.ui.Fragment;
import net.kemitix.clover.ui.fragment.menu.MenuFragment;
import net.kemitix.clover.ui.fragment.body.BodyFragment;
import net.kemitix.clover.ui.fragment.settings.SettingsFragment;

import java.util.Map;

public class MainFragment
        extends AbstractFragment<MainController, MainView> {

    public static final FragmentName PART_BODY = FragmentName.define();
    public static final FragmentName PART_MENU = FragmentName.define();
    public static final FragmentName PART_SETTINGS = FragmentName.define();

    @Getter
    private final Map<FragmentName, Fragment<?, ?>> childFragments = Map.of(
            PART_BODY, new BodyFragment(),
            PART_MENU, new MenuFragment(),
            PART_SETTINGS, new SettingsFragment()
    );

    public MainFragment() {
        super(
                new MainController(),
                new MainView()
        );
    }

}
