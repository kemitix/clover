package net.kemitix.clover.ui.fragment.main;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.control.SplitPane;
import net.kemitix.clover.ui.AbstractView;
import net.kemitix.clover.ui.View;

class MainView
        extends AbstractView<MainController, MainView>
        implements View<MainController, MainView> {

    @Override
    public Parent getRoot() {
        var pane = new SplitPane(
                getChildRoot(MainFragment.PART_SETTINGS),
                getChildRoot(MainFragment.PART_BODY)
        );
        pane.setOrientation(Orientation.HORIZONTAL);
        pane.widthProperty()
                .addListener((observable, oldValue, newValue) ->
                        pane.setDividerPositions(0.2));
        return pane;
    }

}
