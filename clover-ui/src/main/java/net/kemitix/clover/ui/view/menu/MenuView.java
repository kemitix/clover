package net.kemitix.clover.ui.view.menu;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import net.kemitix.clover.ui.AbstractView;
import net.kemitix.clover.ui.View;

public class MenuView
        extends AbstractView<MenuController, MenuView>
        implements View<MenuController, MenuView> {

    @Override
    public Parent getRoot() {
        return new StackPane(new Label("Menu Fragment"));
    }

}
