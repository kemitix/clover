package net.kemitix.clover.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.SneakyThrows;

public class MainUI extends Application {

    @Override
    public void start(Stage stage) {
        stage.setScene(new Scene(loadFXML("clover"), 640, 480));
        stage.show();
    }

    @SneakyThrows
    private Parent loadFXML(String fxml) {
        return new FXMLLoader(MainUI.class.getResource(fxml + ".fxml"))
                .load();
    }

    public static void main(String[] args) {
        launch();
    }

}
