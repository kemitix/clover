package net.kemitix.clover.spi;

public interface Drawable<T> extends Prioritised  {
    void draw(T drawable);
}
