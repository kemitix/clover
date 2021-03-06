package net.kemitix.clover.ui;

import lombok.Getter;

public class SaveConfigEventListener
        implements AppEventListener<AppEvent.SaveConfigEvent> {

    @Getter
    private final Class<AppEvent.SaveConfigEvent> appEventType =
            AppEvent.SaveConfigEvent.class;

    private final AppModel model;

    public SaveConfigEventListener(AppModel model) {
        this.model = model;
    }

    @Override
    public void onEvent(AppEvent.SaveConfigEvent event) {
        System.out.println("Save Event handler");
        //TODO save config
    }

}
