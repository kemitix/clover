package net.kemitix.clover.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;

public abstract class AbstractActionMenuItem
        extends MenuItem {

    public AbstractActionMenuItem(
            String label,
            KeyCombination accelerator,
            EventHandler<ActionEvent> eventHandler
    ) {
        super(label);
        setAccelerator(accelerator);
        setOnAction(eventHandler);
    }

    public AbstractActionMenuItem(
            String label,
            EventHandler<ActionEvent> eventHandler
    ) {
        super(label);
        setOnAction(eventHandler);
    }

}
