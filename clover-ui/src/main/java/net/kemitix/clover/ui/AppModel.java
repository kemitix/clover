package net.kemitix.clover.ui;

import javafx.application.Application;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import net.kemitix.clover.service.ConfigLoadException;
import net.kemitix.clover.service.IssueConfigLoader;
import net.kemitix.clover.service.ServiceIssueConfig;
import net.kemitix.clover.spi.TextEffect;
import net.kemitix.clover.ui.fragment.main.MainFragment;
import net.kemitix.files.FileReaderWriter;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class AppModel {

    private final MainFragment mainFragment;

    @Getter
    private final SimpleStringProperty issueDirectoryProperty =
            new SimpleStringProperty();
    @Getter
    private final SimpleStringProperty issue =
            new SimpleStringProperty();
    @Getter
    private final SimpleStringProperty date =
            new SimpleStringProperty();
    @Getter
    private final SimpleStringProperty titleColour =
            new SimpleStringProperty();
    @Getter
    private final SimpleStringProperty subTitleColour =
            new SimpleStringProperty();
    @Getter
    private final SimpleStringProperty textColour =
            new SimpleStringProperty();
    @Getter
    private final SimpleFloatProperty spine =
            new SimpleFloatProperty();
    @Getter
    private final SimpleStringProperty coverArt =
            new SimpleStringProperty();
    @Getter
    private final SimpleStringProperty coverArtist =
            new SimpleStringProperty();
    @Getter
    private final SimpleIntegerProperty paperbackXOffset =
            new SimpleIntegerProperty();
    @Getter
    private final SimpleIntegerProperty paperbackYOffset =
            new SimpleIntegerProperty();
    @Getter
    private final SimpleIntegerProperty kindleXOffset =
            new SimpleIntegerProperty();
    @Getter
    private final SimpleIntegerProperty kindleYOffset =
            new SimpleIntegerProperty();
    @Getter
    private final SimpleIntegerProperty authorXOffset =
            new SimpleIntegerProperty();
    @Getter
    private final SimpleIntegerProperty authorYOffset =
            new SimpleIntegerProperty();
    @Getter
    private final SimpleIntegerProperty sfTop =
            new SimpleIntegerProperty();
    @Getter
    private final SimpleIntegerProperty sfLeft =
            new SimpleIntegerProperty();
    @Getter
    private final SimpleIntegerProperty fantasyTop =
            new SimpleIntegerProperty();
    @Getter
    private final SimpleIntegerProperty fantasyLeft =
            new SimpleIntegerProperty();
    @Getter
    private final SimpleIntegerProperty reprintTop =
            new SimpleIntegerProperty();
    @Getter
    private final SimpleIntegerProperty reprintLeft =
            new SimpleIntegerProperty();
    @Getter
    private final SimpleStringProperty storiesAlignment =
            new SimpleStringProperty();

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

        issueDirectoryProperty.addListener((observable, oldValue, newValue) ->
                load(newValue).ifPresent(serviceIssueConfig::set));
        issueDirectoryProperty.setValue(initialIssueDirectory);

        var config = serviceIssueConfig.get();

        bind(issue, config::getIssue, config::setIssue);
        bind(date, config::getDate, config::setDate);
        bind(titleColour, config::getTitleColour, config::setTitleColour);
        bind(subTitleColour, config::getSubTitleColour, config::setSubTitleColour);
        bind(textColour, config::getTextColour, config::setTextColour);
        bind(spine, config::getSpine, config::setSpine);
        bind(coverArt, config::getCoverArt, config::setCoverArt);
        bind(coverArtist, config::getCoverArtist, config::setCoverArtist);
        bind(paperbackXOffset, config::getPaperbackXOffset, config::setPaperbackXOffset);
        bind(paperbackYOffset, config::getPaperbackYOffset, config::setPaperbackYOffset);
        bind(kindleXOffset, config::getKindleXOffset, config::setKindleXOffset);
        bind(kindleYOffset, config::getKindleYOffset, config::setKindleYOffset);
        bind(sfTop, config::getSfTop, config::setSfTop);
        bind(sfLeft, config::getSfLeft, config::setSfLeft);
        bind(fantasyTop, config::getFantasyTop, config::setFantasyTop);
        bind(fantasyLeft, config::getFantasyLeft, config::setFantasyLeft);
        bind(reprintTop, config::getReprintTop, config::setReprintTop);
        bind(reprintLeft, config::getReprintLeft, config::setReprintLeft);
        bind(storiesAlignment,
                () -> config.getStoriesAlignment().toString(),
                v -> config.setStoriesAlignment(TextEffect.HAlignment.valueOf(v)));
    }

    private void bind(
            SimpleStringProperty property,
            Supplier<String> getter,
            Consumer<String> setter
    ) {
        property.setValue(getter.get());
        property.addListener((observable, oldValue, newValue) ->
                setter.accept(newValue));
    }

    private void bind(
            SimpleFloatProperty property,
            Supplier<Float> getter,
            Consumer<Float> setter
    ) {
        property.setValue(getter.get());
        property.addListener((observable, oldValue, newValue) ->
                setter.accept(newValue.floatValue()));
    }

    private void bind(
            SimpleIntegerProperty property,
            Supplier<Integer> getter,
            Consumer<Integer> setter
    ) {
        property.setValue(getter.get());
        property.addListener((observable, oldValue, newValue) ->
                setter.accept(newValue.intValue()));
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
