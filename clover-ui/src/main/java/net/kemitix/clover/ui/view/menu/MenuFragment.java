package net.kemitix.clover.ui.view.menu;

import net.kemitix.clover.ui.AbstractFragment;

public class MenuFragment
        extends AbstractFragment<MenuController, MenuView> {

    public MenuFragment() {
        super(
                new MenuController(),
                new MenuView()
        );
    }

}
