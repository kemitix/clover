package net.kemitix.clover.ui;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import net.kemitix.clover.service.ConfigLoadException;
import net.kemitix.clover.service.IssueConfigLoader;
import net.kemitix.clover.service.ServiceIssueConfig;
import net.kemitix.clover.ui.fragment.main.MainFragment;
import net.kemitix.files.FileReaderWriter;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class AppModel {

    private final MainFragment mainFragment;

    @Getter
    private final SimpleStringProperty issueDirectoryProperty;

    private final FileReaderWriter fileReader = new FileReaderWriter();
    private final IssueConfigLoader loader = new IssueConfigLoader();

    private final AtomicReference<ServiceIssueConfig> serviceIssueConfig =
            new AtomicReference<>();

    public AppModel(
            Application.Parameters parameters,
            MainFragment mainFragment
    ) {
        this.mainFragment = mainFragment;
        List<String> unnamed = parameters.getUnnamed();
        if (unnamed.isEmpty()) {
            throw new RuntimeException("Issue Directory not specified");
        }
        var initialIssueDirectory = unnamed.get(0);

        issueDirectoryProperty = new SimpleStringProperty();
        issueDirectoryProperty.addListener((observable, oldValue, newValue) ->
                load(newValue).ifPresent(serviceIssueConfig::set));
        issueDirectoryProperty.setValue(initialIssueDirectory);

    }

    private Optional<ServiceIssueConfig> load(String newValue) {
        File file = Paths.get(newValue).resolve("clover.yaml").toFile();
        try {
            return Optional.of(loader.parseYamlFromFile(
                    file,
                    ServiceIssueConfig.class,
                    fileReader));
        } catch (ConfigLoadException e) {
            emit(AppEvent.loadConfigError(e));
        }
        return Optional.empty();
    }

    private void emit(AppEvent appEvent) {
        mainFragment.emit(appEvent);
    }

}
