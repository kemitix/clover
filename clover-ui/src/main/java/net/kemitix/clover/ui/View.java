package net.kemitix.clover.ui;

import javafx.scene.Parent;

public interface View<C extends Controller<C, V>, V extends View<C, V>>
        extends FragmentComponent<C, V> {

    Parent getRoot();

}
