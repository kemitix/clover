package net.kemitix.clover.ui;

import javafx.stage.Stage;

public class QuitAppEventListener
        implements AppEventListener {

    private final Stage stage;

    public QuitAppEventListener(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void onEvent(AppEvent event) {
        if (event instanceof AppEvent.QuitAppEvent) {
            stage.close();
        }
    }

}
