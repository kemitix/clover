package net.kemitix.clover.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public interface CloverButton {

    static Builder builder() {
        return label -> action -> {
            Button button = new Button(label);
            button.setOnAction(action);
            return button;
        };
    }

    interface Builder {
        Stage0 label(String label);
        interface Stage0 {Button action(EventHandler<ActionEvent> action);}
    }

}
