package net.kemitix.clover.ui;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TextField;

import java.text.NumberFormat;

public interface CloverTextField {

    static TextField boundTo(SimpleStringProperty property) {
        var textField = new TextField(property.getValue());
        textField.textProperty().bindBidirectional(property);
        return textField;
    }

    static TextField boundTo(SimpleIntegerProperty property) {
        var textField = new TextField(property.getValue().toString());
        textField.textProperty().bindBidirectional(property, NumberFormat.getInstance());
        return textField;
    }

    static TextField boundTo(SimpleFloatProperty property) {
        var textField = new TextField(property.getValue().toString());
        textField.textProperty().bindBidirectional(property, NumberFormat.getInstance());
        return textField;
    }

}
