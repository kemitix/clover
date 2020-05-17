package net.kemitix.clover.spi;

import java.util.function.Function;

public interface Effect<T> {
    interface RegionNext<T> { Function<T, T> region(Region region);}
}
