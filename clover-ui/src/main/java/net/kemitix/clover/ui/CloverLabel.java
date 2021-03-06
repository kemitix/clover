package net.kemitix.clover.ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;

public interface CloverLabel {

    static Label text(String label) {
        return new Label(label);
    }

    static CloverLabel.Builder bound() {
        return property -> format -> {
            Label label = new Label(format.formatted(property.getValue()));
            property.addListener((observable, oldValue, newValue) ->
                    label.setText(
                            format.formatted(newValue)));
            return label;
        };
    }

    interface Builder {
        Stage0 bindTo(SimpleStringProperty property);
        interface Stage0 {Label format(String format);}
    }

}
