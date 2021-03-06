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
        var mainFragment = new MainFragment();
        mainFragment.setStage(stage);
        mainFragment.init();
        mainFragment.initModel(model);
        mainFragment.registerEventHandlers(
                new QuitAppEventListener(stage),
                new SaveConfigEventListener(model)
        );

        Scene scene = new Scene(
                mainFragment.getViewRoot(),
                mainFragment.getIntResource("app", "width"),
                mainFragment.getIntResource("app", "height")
        );
        scene.getStylesheets().add(appCss());
        stage.setScene(scene);
        stage.setTitle(mainFragment.getStringResource("app", "title"));
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
