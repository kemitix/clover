package net.kemitix.clover.ui.fragment.body;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import net.kemitix.clover.ui.AbstractView;
import net.kemitix.clover.ui.View;

class BodyView
        extends AbstractView<BodyController, BodyView>
        implements View<BodyController, BodyView> {

    @Override
    public Parent getRoot() {
        return new StackPane(
                new Label(
                        "Issue Directory: %s".formatted(
                                getController().getIssueDirectory()
                        )
                )
        );
    }

}
