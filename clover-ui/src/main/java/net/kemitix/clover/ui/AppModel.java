package net.kemitix.clover.ui;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import net.kemitix.clover.service.IssueConfigLoader;
import net.kemitix.clover.service.ServiceIssueConfig;
import net.kemitix.files.FileReaderWriter;

import java.nio.file.Paths;
import java.util.List;

public class AppModel {

    @Getter
    private final SimpleStringProperty issueDirectoryProperty;

    @Getter
    private final ServiceIssueConfig serviceIssueConfig;

    public AppModel(Application.Parameters parameters) {
        List<String> unnamed = parameters.getUnnamed();
        if (unnamed.isEmpty()) {
            throw new RuntimeException("Issue Directory not specified");
        }
        var issueDirectory = unnamed.get(0);

        issueDirectoryProperty = new SimpleStringProperty(issueDirectory);

        serviceIssueConfig = new IssueConfigLoader().parseYamlFromFile(
                Paths.get(issueDirectory).resolve("clover.yaml").toFile(),
                ServiceIssueConfig.class,
                new FileReaderWriter());

    }

}
