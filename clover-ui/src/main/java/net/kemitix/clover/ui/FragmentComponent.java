package net.kemitix.clover.ui;

public interface FragmentComponent<C extends Controller<C, V>, V extends View<C, V>>
        extends HasResources{

    void setFragment(Fragment<C, V> fragment);

    Fragment<C, V> getFragment();

    default C getController() {
        return getFragment().getController();
    }

    default V getView() {
        return getFragment().getView();
    }

    default void emit(AppEvent appEvent) {
        getController().emit(appEvent);
    }

}
