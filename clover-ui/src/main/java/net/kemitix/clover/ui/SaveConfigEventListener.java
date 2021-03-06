package net.kemitix.clover.ui;

import lombok.Getter;

public class SaveConfigEventListener
        implements AppEventListener {

    @Getter
    private final Class<? extends AppEvent> appEventType =
            AppEvent.SaveConfigEvent.class;

    private final AppModel model;

    public SaveConfigEventListener(AppModel model) {
        this.model = model;
    }

    @Override
    public void onEvent(AppEvent event) {
        System.out.println("Save Event handler");
        //TODO save config
    }

}
