package net.kemitix.clover.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import net.kemitix.clover.ui.AbstractActionMenuItem;

public interface CloverMenuItem {

    static BuilderPlain plain() {
        return label -> action ->
                new  AbstractActionMenuItem(label, action) {};
    }
    static BuilderAccelerated accelerated() {
        return label -> accelerator -> action ->
                new AbstractActionMenuItem(
                        label,
                        KeyCombination.valueOf(accelerator),
                        action) {};
    }

    interface BuilderPlain {
        Stage0 label(String label);
        interface Stage0 {MenuItem action(EventHandler<ActionEvent> action);}
    }

    interface BuilderAccelerated {
        Stage0 label(String label);
        interface Stage0 {Stage1 keys(String accelerator);}
        interface Stage1 {MenuItem action(EventHandler<ActionEvent> action);}
    }

}
