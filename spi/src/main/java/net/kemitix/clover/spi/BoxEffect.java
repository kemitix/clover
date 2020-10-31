package net.kemitix.clover.spi;

public interface BoxEffect<T> extends Effect<T> {
    ThicknessNext<T> opacity(double opacity);
    interface ThicknessNext<T> {BoxEffect.ColourNext<T> thickness(int thickness);}
    interface ColourNext<T> { Effect.RegionNext<T> colour(String colour);}
}
