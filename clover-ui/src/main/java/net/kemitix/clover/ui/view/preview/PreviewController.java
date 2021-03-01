package net.kemitix.clover.ui.view.preview;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import net.kemitix.clover.ui.AppModel;
import net.kemitix.clover.ui.Controller;
import net.kemitix.clover.ui.Fragment;

class PreviewController
        implements Controller<PreviewController, PreviewView> {

    @Setter
    @Getter
    private Fragment<PreviewController, PreviewView> fragment;

    @Setter
    @Getter
    @NonNull
    private String issueDirectory;

    @Override
    public void initModel(AppModel model) {
        setIssueDirectory(model.getIssueDirectory());
    }

}
