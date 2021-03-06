package net.kemitix.clover.ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;

public interface CloverLabel {

    static Label text(String label) {
        return new Label(label);
    }

    static CloverLabel.Builder boundTo(SimpleStringProperty property) {
        return format -> {
            Label label = new Label(format.formatted(property.getValue()));
            property.addListener((observable, oldValue, newValue) ->
                    label.setText(
                            format.formatted(newValue)));
            return label;
        };
    }

    interface Builder {
        Label format(String format);
    }

}
