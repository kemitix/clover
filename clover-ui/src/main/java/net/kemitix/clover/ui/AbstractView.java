package net.kemitix.clover.ui;

public abstract class AbstractView<C extends Controller<C, V>, V extends View<C, V>>
        extends AbstractFragmentComponent<C, V>
        implements View<C, V> {
}
