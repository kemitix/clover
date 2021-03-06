package net.kemitix.clover.ui;

import java.util.EventListener;

public interface AppEventListener<T extends AppEvent>
        extends EventListener {

    Class<T> getAppEventType();

    /**
     * Receives the event and performs any actions.
     *
     * @param event the event
     */
    void onEvent(T event);

    default void handleEvent(AppEvent event) {
        Class<T> appEventType = getAppEventType();
        if (appEventType.isInstance(event)) {
            T e = appEventType.cast(event);
            onEvent(e);
        }
    }

}
