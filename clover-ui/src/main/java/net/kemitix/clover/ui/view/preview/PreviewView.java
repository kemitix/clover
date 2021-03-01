package net.kemitix.clover.ui.view.preview;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import net.kemitix.clover.ui.AbstractView;
import net.kemitix.clover.ui.View;

class PreviewView
        extends AbstractView<PreviewController, PreviewView>
        implements View<PreviewController, PreviewView> {

    @Override
    public Parent getRoot() {
        String issueDirectory = getController().getIssueDirectory();
        return new StackPane(new Label("Issue Directory: " + issueDirectory));
    }

}
