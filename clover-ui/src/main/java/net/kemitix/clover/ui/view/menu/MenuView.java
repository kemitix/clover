package net.kemitix.clover.ui.view.menu;

import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import net.kemitix.clover.ui.AbstractView;
import net.kemitix.clover.ui.View;

public class MenuView
        extends AbstractView<MenuController, MenuView>
        implements View<MenuController, MenuView> {

    @Override
    public Parent getRoot() {
        var menuBar = new MenuBar();
        var menuFile = new Menu("File");
        var menuItemQuit = new MenuItem("Quit");
        menuBar.getMenus().add(menuFile);
        menuFile.getItems().add(menuItemQuit);
        menuItemQuit.setOnAction(e -> closeWindow());
        return menuBar;
    }

}
