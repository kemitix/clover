package net.kemitix.clover.ui;

import java.util.EventListener;

public interface AppEventListener
        extends EventListener {

    Class<? extends AppEvent> getAppEventType();

    /**
     * Receives the event and performs any actions.
     *
     * @param event the event
     */
    void onEvent(AppEvent event);

    default void handleEvent(AppEvent event) {
        if (getAppEventType().isInstance(event)) {
            onEvent(event);
        }
    }

}
