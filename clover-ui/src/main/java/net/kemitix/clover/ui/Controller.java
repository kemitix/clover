package net.kemitix.clover.ui;

public interface Controller<C extends Controller<C, V>, V extends View<C, V>>
        extends FragmentComponent<C, V> {

    void initModel(AppModel model);

}
