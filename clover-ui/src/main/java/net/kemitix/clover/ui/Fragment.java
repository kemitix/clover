package net.kemitix.clover.ui;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.util.Map;

public interface Fragment<C extends Controller<C, V>, V extends View<C, V>>
        extends HasResources {

    C getController();

    V getView();

    Map<FragmentName, Fragment<?, ?>> getChildFragments();

    Parent getViewRoot();

    void initModel(AppModel model);

    default Fragment<?,?> getChild(FragmentName name) {
        return getChildFragments().get(name);
    }

    default Node getChildRoot(FragmentName name) {
        return getChild(name).getViewRoot();
    }

    void setParent(Fragment<?, ?> parent);
    Fragment<?, ?> getParent();

    Stage getStage();

    default void init() {
        getChildFragments().values()
                .forEach(child -> {
                    child.setParent(this);
                });
    }

    void handle(AppEvent event);

    interface FragmentName {
        static FragmentName define() {
            return new FragmentName() {
            };
        }
    }

}
