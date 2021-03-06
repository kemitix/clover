package net.kemitix.clover.ui;

import lombok.Getter;
import lombok.Setter;

public abstract class AbstractFragmentComponent<C extends Controller<C, V>, V extends View<C, V>>
        implements FragmentComponent<C, V> {

    @Setter
    @Getter
    private Fragment<C, V> fragment;

    public void emit(AppEvent event) {
        getFragment().handle(event);
    }

    @Override
    public String getStringResource(String prefix, String name) {
        return getFragment().getParent().getStringResource(prefix, name);
    }

    @Override
    public int getIntResource(String prefix, String name) {
        return getFragment().getParent().getIntResource(prefix, name);
    }
}
