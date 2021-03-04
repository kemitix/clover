package net.kemitix.clover.ui.fragment.main;

import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import net.kemitix.clover.ui.AbstractView;
import net.kemitix.clover.ui.View;

class MainView
        extends AbstractView<MainController, MainView>
        implements View<MainController, MainView> {

    @Override
    public Parent getRoot() {
        var pane = new BorderPane(getChildRoot(MainFragment.PART_BODY));
        pane.setTop(getChildRoot(MainFragment.PART_MENU));
        pane.setLeft(getChildRoot(MainFragment.PART_SETTINGS));
        return pane;
    }

}
