package net.kemitix.clover.spi;

public interface Element<T> extends Drawable<T> {

    default boolean typeFilter(IssueType type) {
        return true;
    }

}
