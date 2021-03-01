package net.kemitix.clover.ui;

public abstract class AbstractController<C extends Controller<C, V>, V extends View<C, V>>
        extends AbstractFragmentComponent<C, V>
        implements Controller<C, V> {

    @Override
    public void initModel(AppModel model) {
        // Controller doesn't use the model
    }

}
