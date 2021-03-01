package net.kemitix.clover.ui.view.main;

import lombok.Getter;
import lombok.Setter;
import net.kemitix.clover.ui.AppModel;
import net.kemitix.clover.ui.Controller;
import net.kemitix.clover.ui.Fragment;

class MainController
        implements Controller<MainController, MainView> {

    @Setter
    @Getter
    private Fragment<MainController, MainView> fragment;

    @Override
    public void initModel(AppModel model) {

    }
}
