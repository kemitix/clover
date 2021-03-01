package net.kemitix.clover.ui.view.menu;

import lombok.Getter;
import lombok.Setter;
import net.kemitix.clover.ui.AppModel;
import net.kemitix.clover.ui.Controller;
import net.kemitix.clover.ui.Fragment;

public class MenuController
        implements Controller<MenuController, MenuView> {

    @Getter
    @Setter
    private Fragment<MenuController, MenuView> fragment;

    @Override
    public void initModel(AppModel model) {

    }

}
