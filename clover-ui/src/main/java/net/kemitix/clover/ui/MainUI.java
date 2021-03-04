package net.kemitix.clover.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import net.kemitix.clover.ui.fragment.main.MainFragment;

public class MainUI extends Application {

    @Override
    public void start(Stage stage) {
        AppModel model = new AppModel(getParameters());
        System.out.println("MainUI.model.getIssueDirectory() = " + model.getIssueDirectory());
        var mainFragment = new MainFragment();
        mainFragment.setStage(stage);
        mainFragment.init();
        mainFragment.initModel(model);

        Scene scene = new Scene(mainFragment.getRoot());
        scene.getStylesheets().add(appCss());
        stage.setScene(scene);
        stage.setTitle("Clover UI");
        stage.show();
    }

    private String appCss() {
        return resource("app.css");
    }

    private String resource(String name) {
        return getClass().getResource(name).toExternalForm();
    }

    @SneakyThrows
    public static void main(String[] args) {
        launch(args);
    }

}
