package net.kemitix.clover.ui;

import javafx.scene.Node;
import javafx.scene.Parent;

import java.util.Map;

public interface Fragment<C extends Controller<C, V>, V extends View<C, V>> {

    C getController();

    V getView();

    Map<FragmentName, Fragment<?, ?>> getChildFragments();

    Parent getRoot();

    void initModel(AppModel model);

    default Fragment<?,?> getChild(FragmentName name) {
        return getChildFragments().get(name);
    }

    default Node getChildRoot(FragmentName name) {
        return getChild(name).getRoot();
    }

    interface FragmentName {}

}
