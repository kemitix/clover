package net.kemitix.clover.ui.fragment.settings;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import net.kemitix.clover.ui.AbstractView;
import net.kemitix.clover.ui.AppEvent;
import net.kemitix.clover.ui.CloverButton;
import net.kemitix.clover.ui.CloverLabel;
import net.kemitix.clover.ui.CloverTextField;

public class SettingsView
        extends AbstractView<SettingsController, SettingsView> {

    @Override
    public Parent getRoot() {
        var heading = getStringResource("settings", "heading");
        var style = getStringResource("settings", "style");
        int width = getIntResource("settings", "width");

        var controller = getController();
        var root = new GridPane();
        root.add(CloverLabel.text(heading), 0, 0, 2, 1);
        root.addRow(1,
                CloverButton.builder()
                        .label("Load")
                        .action(e -> emit(AppEvent.loadConfig(e))),
                CloverButton.builder()
                        .label("Save")
                        .action(e -> emit(AppEvent.saveConfig(e))));
        root.add(
                CloverLabel.boundTo(controller.getIssueDirectoryProperty())
                        .format("Directory: %s"),
                0, 2, 2, 1);
        addPropertyRow(3, "Issue: ", controller.getIssueProperty(), root);
        addPropertyRow(4, "Date: ", controller.getDateProperty(), root);
        addPropertyRow(5, "Title Colour: ", controller.getTitleColourProperty(), root);
        addPropertyRow(6, "Subtitle Colour: ", controller.getSubTitleColourProperty(), root);
        addPropertyRow(7, "Text Colour: ", controller.getTextColourProperty(), root);
        addPropertyRow(8, "Spine Width(\")", controller.getSpineProperty(), root);
        addPropertyRow(9, "Cover Art: ", controller.getCoverArtProperty(), root);
        addPropertyRow(10, "Cover Artist: ", controller.getCoverArtistProperty(), root);
        addPropertyRow(11, "Paperback X Offset: ", controller.getPaperbackXOffsetProperty(), root);
        addPropertyRow(12, "Paperback Y Offset: ", controller.getPaperbackYOffsetProperty(), root);
        addPropertyRow(13, "Kindle X Offset: ", controller.getKindleXOffsetProperty(), root);
        addPropertyRow(14, "Kindle Y Offset: ", controller.getKindleYOffsetProperty(), root);
        //TODO more fields
        root.addRow(20,
                CloverButton.builder()
                        .label("Quit")
                        .action(e -> emit(AppEvent.quitApp(e))));
        root.setHgap(8);
        root.setVgap(8);
        root.setStyle(style);
        root.setPrefWidth(width);
        return root;
    }

    private void addPropertyRow(int rowNumber, String label, SimpleIntegerProperty property, GridPane root) {
        root.addRow(rowNumber,
                CloverLabel.text(label),
                CloverTextField.boundTo(property));
    }

    private void addPropertyRow(int rowNumber, String label, SimpleFloatProperty property, GridPane root) {
        root.addRow(rowNumber,
                CloverLabel.text(label),
                CloverTextField.boundTo(property));
    }

    private void addPropertyRow(int rowNumber, String label, SimpleStringProperty property, GridPane root) {
        root.addRow(rowNumber,
                CloverLabel.text(label),
                CloverTextField.boundTo(property));
    }

}
