package net.kemitix.clover.ui.fragment.settings;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import net.kemitix.clover.ui.AbstractController;
import net.kemitix.clover.ui.AppModel;

@Getter
public class SettingsController
extends AbstractController<SettingsController, SettingsView> {

    private SimpleStringProperty issueDirectoryProperty;
    private SimpleStringProperty issueProperty;
    private SimpleStringProperty dateProperty;
    private SimpleStringProperty titleColourProperty;
    private SimpleStringProperty subTitleColourProperty;
    private SimpleStringProperty textColourProperty;
    private SimpleFloatProperty spineProperty;
    private SimpleStringProperty coverArtProperty;
    private SimpleStringProperty coverArtistProperty;
    private SimpleIntegerProperty paperbackXOffsetProperty;
    private SimpleIntegerProperty paperbackYOffsetProperty;
    private SimpleIntegerProperty kindleXOffsetProperty;
    private SimpleIntegerProperty kindleYOffsetProperty;
    private SimpleIntegerProperty sfTopProperty;
    private SimpleIntegerProperty sfLeftProperty;
    private SimpleIntegerProperty fantasyTopProperty;
    private SimpleIntegerProperty fantasyLeftProperty;
    private SimpleIntegerProperty reprintTopProperty;
    private SimpleIntegerProperty reprintLeftProperty;
    private SimpleStringProperty storiesAlignmentProperty;

    @Override
    public void initModel(AppModel model) {
        issueDirectoryProperty = model.getIssueDirectoryProperty();
        issueProperty = model.getIssue();
        dateProperty = model.getDate();
        titleColourProperty = model.getTitleColour();
        subTitleColourProperty = model.getSubTitleColour();
        textColourProperty = model.getTextColour();
        spineProperty = model.getSpine();
        coverArtProperty = model.getCoverArt();
        coverArtistProperty = model.getCoverArtist();
        paperbackXOffsetProperty = model.getPaperbackXOffset();
        paperbackYOffsetProperty = model.getPaperbackYOffset();
        kindleXOffsetProperty = model.getKindleXOffset();
        kindleYOffsetProperty = model.getKindleYOffset();
        sfTopProperty = model.getSfTop();
        sfLeftProperty = model.getSfLeft();
        fantasyTopProperty = model.getFantasyTop();
        fantasyLeftProperty = model.getFantasyLeft();
        reprintTopProperty = model.getReprintTop();
        reprintLeftProperty = model.getReprintLeft();
        storiesAlignmentProperty = model.getStoriesAlignment();
    }

}
