package net.kemitix.clover.ui.fragment.menu;

import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import net.kemitix.clover.ui.AbstractView;
import net.kemitix.clover.ui.AppEvent;
import net.kemitix.clover.ui.CloverMenuItem;
import net.kemitix.clover.ui.View;

public class MenuView
        extends AbstractView<MenuController, MenuView>
        implements View<MenuController, MenuView> {

    @Override
    public Parent getRoot() {
        var quit = CloverMenuItem.accelerated()
                .label("Quit").keys("Ctrl+q")
                .action(e -> emit(AppEvent.quitApp(e)));
        var save = CloverMenuItem.accelerated()
                .label("Save").keys("Ctrl+s")
                .action(e -> emit(AppEvent.saveConfig(e)));
        return new MenuBar(
                new Menu(
                        "File", null,
                        save,
                        quit
                ),
                new Menu(
                        "Help", null
                )
        );
    }

}
