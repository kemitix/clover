package net.kemitix.clover.ui.view.main;

import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import lombok.Getter;
import lombok.Setter;
import net.kemitix.clover.ui.Fragment;
import net.kemitix.clover.ui.View;

class MainView
        implements View<MainController, MainView> {

    @Setter
    @Getter
    private Fragment<MainController, MainView> fragment;

    @Override
    public Parent getRoot() {
        var pane = new BorderPane(fragment.getChildRoot(MainFragment.PART_PREVIEW));
        pane.setTop(fragment.getChildRoot(MainFragment.PART_MENU));
        //TODO pane.setTop(new TopView().getView());
        //     pane.setLeft(new LeftView().getView());
        return pane;
    }

}
