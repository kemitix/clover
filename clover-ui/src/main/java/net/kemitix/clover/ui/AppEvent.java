package net.kemitix.clover.ui;

import javafx.event.ActionEvent;
import net.kemitix.clover.service.ConfigLoadException;

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

    static AppEvent loadConfigError(ConfigLoadException e) {
        return (LoadConfigErrorEvent) () -> e;
    }

    interface LoadConfigEvent extends AppEvent {}
    interface LoadConfigErrorEvent extends AppEvent {
        ConfigLoadException getException();
    }
    interface SaveConfigEvent extends AppEvent {}
    interface QuitAppEvent extends AppEvent {}
}
