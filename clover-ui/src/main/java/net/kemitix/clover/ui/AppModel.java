package net.kemitix.clover.ui;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import lombok.Getter;
import net.kemitix.clover.service.IssueConfigLoader;
import net.kemitix.clover.service.ServiceIssueConfig;
import net.kemitix.files.FileReaderWriter;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

public class AppModel {

    @Getter
    private final SimpleStringProperty issueDirectoryProperty;

    private final FileReaderWriter fileReader = new FileReaderWriter();
    private final IssueConfigLoader loader = new IssueConfigLoader();

    private ServiceIssueConfig serviceIssueConfig;

    public AppModel(Application.Parameters parameters) {
        List<String> unnamed = parameters.getUnnamed();
        if (unnamed.isEmpty()) {
            throw new RuntimeException("Issue Directory not specified");
        }
        var initialIssueDirectory = unnamed.get(0);

        issueDirectoryProperty = new SimpleStringProperty();
        issueDirectoryProperty.addListener((observable, oldValue, newValue) ->
                serviceIssueConfig = load(newValue));
        issueDirectoryProperty.setValue(initialIssueDirectory);

    }

    private ServiceIssueConfig load(String newValue) {
        File file = Paths.get(newValue).resolve("clover.yaml").toFile();
        return loader.parseYamlFromFile(
                file,
                ServiceIssueConfig.class,
                fileReader);
    }

}
