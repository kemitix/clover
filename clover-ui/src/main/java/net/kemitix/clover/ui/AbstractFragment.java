package net.kemitix.clover.ui;

import javafx.scene.Parent;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Map;
import java.util.Optional;

public abstract class AbstractFragment<C extends Controller<C, V>, V extends View<C, V>>
        implements Fragment<C, V> {

    @Getter
    @NonNull
    private final C controller;
    @Getter
    @NonNull
    private final V view;
    @Getter
    private final Map<FragmentName, Fragment<?, ?>> childFragments = Map.of();
    @Setter
    private Stage stage;
    @Setter
    private Fragment<?, ?> parent;

    protected AbstractFragment(C controller, V view) {
        this.controller = controller;
        this.view = view;
        controller.setFragment(this);
        view.setFragment(this);
    }

    @Override
    public Stage getStage() {
        return Optional.ofNullable(stage)
                .orElseGet(() -> getParent().getStage());
    }

    @Override
    public Fragment<?, ?> getParent() {
        if (parent != null) return parent.getParent();
        return this;
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
