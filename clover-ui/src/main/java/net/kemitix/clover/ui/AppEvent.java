package net.kemitix.clover.ui;

import javafx.event.ActionEvent;

public interface AppEvent {

    static AppEvent quitApp(ActionEvent e) {
        return new QuitAppEvent() {};
    }

    static AppEvent saveConfig(ActionEvent e) {
        return new SaveConfigEvent() {};
    }

    interface QuitAppEvent extends AppEvent {}
    interface SaveConfigEvent extends AppEvent {}
}
