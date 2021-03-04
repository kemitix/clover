package net.kemitix.clover.ui.fragment.menu;

import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import net.kemitix.clover.ui.AbstractView;
import net.kemitix.clover.ui.CloverMenuItem;
import net.kemitix.clover.ui.View;

public class MenuView
        extends AbstractView<MenuController, MenuView>
        implements View<MenuController, MenuView> {

    @Override
    public Parent getRoot() {
        var quit = CloverMenuItem.accelerated()
                .label("Quit")
                .keys("Ctrl+q")
                .action(e -> closeWindow());
        var help = CloverMenuItem.plain()
                .label("Help")
                .action(e -> {
                });
        return new MenuBar(
                new Menu(
                        "File", null,
                        help,
                        quit
                )
        );
    }

}
