package net.kemitix.clover.ui.view.preview;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import lombok.Getter;
import lombok.Setter;
import net.kemitix.clover.ui.Fragment;
import net.kemitix.clover.ui.View;

class PreviewView
        implements View<PreviewController, PreviewView> {

    @Setter
    @Getter
    private Fragment<PreviewController, PreviewView> fragment;

    @Override
    public Parent getRoot() {
        PreviewController controller = fragment.getController();
        System.out.println("controller = " + controller);
        String issueDirectory = controller.getIssueDirectory();
        return new StackPane(new Label("Issue Directory: " + issueDirectory));
    }

}
