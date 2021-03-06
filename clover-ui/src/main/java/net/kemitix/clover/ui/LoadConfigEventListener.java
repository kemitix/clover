package net.kemitix.clover.ui;

import lombok.Getter;

public class LoadConfigEventListener
        implements AppEventListener {

    @Getter
    private final Class<? extends AppEvent> appEventType =
            AppEvent.LoadConfigEvent.class;

    private final AppModel model;

    public LoadConfigEventListener(AppModel model) {
        this.model = model;
    }

    @Override
    public void onEvent(AppEvent event) {
        if (event instanceof AppEvent.LoadConfigEvent) {
            System.out.println("Load Event handler");
            //TODO save config
        }
    }
}
