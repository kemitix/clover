package net.kemitix.clover.ui;

import javafx.stage.Stage;
import lombok.Getter;

public class QuitAppEventListener
        implements AppEventListener {

    @Getter
    private final Class<? extends AppEvent> appEventType =
            AppEvent.QuitAppEvent.class;

    private final Stage stage;

    public QuitAppEventListener(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void onEvent(AppEvent event) {
        stage.close();
    }

}
