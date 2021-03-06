package net.kemitix.clover.ui;

import javafx.scene.Parent;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractFragment<C extends Controller<C, V>, V extends View<C, V>>
        extends AbstractFragmentComponent<C, V>
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
    public Parent getViewRoot() {
        return getView().getRoot();
    }

    @Override
    public void initModel(AppModel model) {
        controller.initModel(model);
        getChildFragments().values().forEach(
                fragment -> fragment.initModel(model)
        );
    };

    @Getter
    private final List<AppEventListener> eventListeners = new ArrayList<>();

    @Override
    public void handle(AppEvent event) {
        getEventListeners()
                .forEach(handler -> handler.handleEvent(event));
        Optional.ofNullable(parent)
                .ifPresent(p -> p.handle(event));
    }

    protected void registerEventHandlers(AppEventListener... listeners) {
        eventListeners.addAll(Arrays.asList(listeners));
    }

}
