package net.kemitix.clover.ui;

import javafx.stage.Stage;
import lombok.Getter;

public class QuitAppEventListener
        implements AppEventListener<AppEvent.QuitAppEvent> {

    @Getter
    private final Class<AppEvent.QuitAppEvent> appEventType =
            AppEvent.QuitAppEvent.class;

    private final Stage stage;

    public QuitAppEventListener(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void onEvent(AppEvent.QuitAppEvent event) {
        stage.close();
    }

}
