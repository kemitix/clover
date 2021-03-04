package net.kemitix.clover.ui.view.settings;

import net.kemitix.clover.ui.AbstractFragment;

public class SettingsFragment
        extends AbstractFragment<SettingsController, SettingsView> {

    public SettingsFragment() {
        super(
                new SettingsController(),
                new SettingsView()
        );
    }
}
