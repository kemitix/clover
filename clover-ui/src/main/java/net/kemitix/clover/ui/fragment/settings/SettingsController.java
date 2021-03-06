package net.kemitix.clover.ui.fragment.settings;

import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import net.kemitix.clover.ui.AbstractController;
import net.kemitix.clover.ui.AppModel;

public class SettingsController
extends AbstractController<SettingsController, SettingsView> {

    @Getter
    SimpleStringProperty issueDirectoryProperty;

    @Override
    public void initModel(AppModel model) {
        issueDirectoryProperty = model.getIssueDirectoryProperty();
    }

}
