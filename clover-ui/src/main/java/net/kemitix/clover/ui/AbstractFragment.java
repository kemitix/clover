package net.kemitix.clover.ui;

import javafx.scene.Parent;
import lombok.Getter;
import lombok.NonNull;

public abstract class AbstractFragment<C extends Controller<C, V>, V extends View<C, V>>
        implements Fragment<C, V> {

    @Getter
    @NonNull
    private final C controller;
    @Getter
    @NonNull
    private final V view;

    protected AbstractFragment(C controller, V view) {
        this.controller = controller;
        this.view = view;
        controller.setFragment(this);
        view.setFragment(this);
    }

    @Override
    public Parent getRoot() {
        return getView().getRoot();
    }

    @Override
    public void initModel(AppModel model) {
        controller.initModel(model);
        getChildFragments().values().forEach(
                fragment -> fragment.initModel(model)
        );
    };
}
