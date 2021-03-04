package net.kemitix.clover.ui.fragment.menu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCombination;
import net.kemitix.clover.ui.AbstractActionMenuItem;

public class QuitMenuItem
        extends AbstractActionMenuItem {

    public QuitMenuItem(EventHandler<ActionEvent> eventHandler) {
        super("Quit", KeyCombination.keyCombination("Ctrl+q"), eventHandler);
    }

}
