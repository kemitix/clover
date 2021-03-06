package net.kemitix.clover.ui;

import javafx.event.ActionEvent;

public interface AppEvent {

    static AppEvent quitApp(ActionEvent e) {
        return new QuitAppEvent() {};
    }

    static AppEvent saveConfig(ActionEvent e) {
        return new SaveConfigEvent() {};
    }

    static AppEvent loadConfig(ActionEvent e) {
        return new LoadConfigEvent() {};
    }

    interface LoadConfigEvent extends AppEvent {}
    interface SaveConfigEvent extends AppEvent {}
    interface QuitAppEvent extends AppEvent {}
}
