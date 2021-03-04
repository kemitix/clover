package net.kemitix.clover.ui.fragment.menu;

import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import net.kemitix.clover.ui.AbstractView;
import net.kemitix.clover.ui.View;

public class MenuView
        extends AbstractView<MenuController, MenuView>
        implements View<MenuController, MenuView> {

    @Override
    public Parent getRoot() {
        return new MenuBar(
                new Menu(
                        "File", null,
                        new QuitMenuItem(e -> closeWindow())
                )
        );
    }

}
