package net.kemitix.clover.ui;

public class SaveConfigEventListener
        implements AppEventListener {

    private final AppModel model;

    public SaveConfigEventListener(AppModel model) {
        this.model = model;
    }

    @Override
    public void onEvent(AppEvent event) {
        if (event instanceof AppEvent.SaveConfigEvent) {
            System.out.println("Save Event handler");
        }
    }

}
