package net.kemitix.clover.ui.view.menu;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import lombok.Getter;
import lombok.Setter;
import net.kemitix.clover.ui.Fragment;
import net.kemitix.clover.ui.View;

public class MenuView
        implements View<MenuController, MenuView> {

    @Getter
    @Setter
    private Fragment<MenuController, MenuView> fragment;

    @Override
    public Parent getRoot() {
        return new StackPane(new Label("Menu Fragment"));
    }

}
