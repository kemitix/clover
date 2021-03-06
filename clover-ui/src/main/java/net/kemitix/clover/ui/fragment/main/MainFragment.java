package net.kemitix.clover.ui.fragment.main;

import lombok.Getter;
import net.kemitix.clover.ui.AbstractFragment;
import net.kemitix.clover.ui.Fragment;
import net.kemitix.clover.ui.fragment.body.BodyFragment;
import net.kemitix.clover.ui.fragment.settings.SettingsFragment;

import java.util.Map;
import java.util.ResourceBundle;

public class MainFragment
        extends AbstractFragment<MainController, MainView> {

    public static final FragmentName PART_BODY = FragmentName.define();
    public static final FragmentName PART_SETTINGS = FragmentName.define();

    private static final ResourceBundle BUNDLE =
            ResourceBundle.getBundle("messages");

    @Getter
    private final Map<FragmentName, Fragment<?, ?>> childFragments = Map.of(
            PART_BODY, new BodyFragment(),
            PART_SETTINGS, new SettingsFragment()
    );

    public MainFragment() {
        super(
                new MainController(),
                new MainView()
        );
    }

    @Override
    public String getStringResource(String prefix, String name) {
        return BUNDLE.getString("%s.%s".formatted(prefix, name));
    }

    @Override
    public int getIntResource(String prefix, String name) {
        return Integer.parseInt(getStringResource(prefix, name));
    }

}
