package net.kemitix.clover.ui.view.preview;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import net.kemitix.clover.ui.AbstractController;
import net.kemitix.clover.ui.AppModel;
import net.kemitix.clover.ui.Controller;

class PreviewController
        extends AbstractController<PreviewController, PreviewView>
        implements Controller<PreviewController, PreviewView> {

    @Setter
    @Getter
    @NonNull
    private String issueDirectory;

    @Override
    public void initModel(AppModel model) {
        setIssueDirectory(model.getIssueDirectory());
    }

}
