package net.kemitix.clover.ui.fragment.body;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import net.kemitix.clover.ui.AbstractController;
import net.kemitix.clover.ui.AppModel;
import net.kemitix.clover.ui.Controller;

class BodyController
        extends AbstractController<BodyController, BodyView>
        implements Controller<BodyController, BodyView> {

    @Setter
    @Getter
    @NonNull
    private String issueDirectory;

    @Override
    public void initModel(AppModel model) {
        setIssueDirectory(model.getIssueDirectoryProperty().getValue());
    }

}
