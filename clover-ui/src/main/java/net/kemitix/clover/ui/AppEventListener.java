package net.kemitix.clover.ui;

import java.util.EventListener;

public interface AppEventListener
        extends EventListener {

    /**
     * Receives the event and performs any actions.
     *
     * @param event the event
     */
    void onEvent(AppEvent event);

}
