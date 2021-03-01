package net.kemitix.clover.ui.view.main;

import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import net.kemitix.clover.ui.AbstractView;
import net.kemitix.clover.ui.View;

class MainView
        extends AbstractView<MainController, MainView>
        implements View<MainController, MainView> {

    @Override
    public Parent getRoot() {
        var pane = new BorderPane(getFragment().getChildRoot(MainFragment.PART_PREVIEW));
        pane.setTop(getFragment().getChildRoot(MainFragment.PART_MENU));
        //TODO pane.setTop(new TopView().getView());
        //     pane.setLeft(new LeftView().getView());
        return pane;
    }

}
