package net.kemitix.clover.ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.stage.DirectoryChooser;
import lombok.Getter;

import java.io.File;
import java.util.function.Consumer;

public class LoadConfigEventListener
        implements AppEventListener<AppEvent.LoadConfigEvent> {

    @Getter
    private final Class<AppEvent.LoadConfigEvent> appEventType =
            AppEvent.LoadConfigEvent.class;

    private final AppModel model;

    public LoadConfigEventListener(AppModel model) {
        this.model = model;
    }

    @Override
    public void onEvent(AppEvent.LoadConfigEvent event) {
        show().accept(model.getIssueDirectoryProperty());
    }

    private Consumer<SimpleStringProperty> show() {
        return dir -> dir.setValue(chooser(dir.getValue()));
    }

    private String chooser(String directory) {
        var directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File(directory));
        return directoryChooser.showDialog(null)
                .getAbsolutePath();
    }
}
