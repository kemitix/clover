package net.kemitix.clover.ui;

import javafx.scene.Node;
import javafx.scene.Parent;

public interface View<C extends Controller<C, V>, V extends View<C, V>>
        extends FragmentComponent<C, V> {

    Parent getRoot();

    default Node getChildRoot(Fragment.FragmentName name) {
        return getFragment().getChildRoot(name);
    }
}
