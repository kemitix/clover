package net.kemitix.clover.ui;

import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lombok.Getter;
import net.kemitix.clover.ui.fragment.main.MainFragment;

public class LoadConfigErrorEventListener
        implements AppEventListener<AppEvent.LoadConfigErrorEvent> {

    @Getter
    private final Class<AppEvent.LoadConfigErrorEvent> appEventType =
            AppEvent.LoadConfigErrorEvent.class;

    private final Stage stage;
    private final AppModel model;
    private final MainFragment mainFragment;

    public LoadConfigErrorEventListener(
            Stage stage,
            AppModel model,
            MainFragment mainFragment
    ) {
        this.stage = stage;
        this.model = model;
        this.mainFragment = mainFragment;
    }

    @Override
    public void onEvent(AppEvent.LoadConfigErrorEvent event) {
        System.err.println("Error loading config: " + event.getException());
        var alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error loading configuration");
        alert.setContentText(event.getException().getMessage());
        alert.showAndWait();
        mainFragment.emit(AppEvent.loadConfig());
    }
}
