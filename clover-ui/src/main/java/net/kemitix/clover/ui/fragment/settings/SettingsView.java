package net.kemitix.clover.ui.fragment.settings;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import net.kemitix.clover.ui.AbstractView;
import net.kemitix.clover.ui.AppEvent;
import net.kemitix.clover.ui.CloverButton;

public class SettingsView
        extends AbstractView<SettingsController, SettingsView> {

    @Override
    public Parent getRoot() {
        String heading = getStringResource("settings", "heading");
        String style = getStringResource("settings", "style");
        int width = getIntResource("settings", "width");

        var root = new VBox(
                new StackPane(new Label(heading)),
                new FlowPane(
                        CloverButton.builder()
                                .label("Load")
                                .action(e -> {/* TODO */}),
                        CloverButton.builder()
                                .label("Save")
                                .action(e -> emit(AppEvent.saveConfig(e))),
                        CloverButton.builder()
                                .label("Quit")
                                .action(e -> emit(AppEvent.quitApp(e)))
                )
        );
        root.setStyle(style);
        root.setPrefWidth(width);
        return root;
    }

}
