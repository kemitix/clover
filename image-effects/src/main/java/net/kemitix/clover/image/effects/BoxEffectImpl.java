package net.kemitix.clover.image.effects;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;
import net.kemitix.clover.spi.BoxEffect;
import net.kemitix.clover.spi.Colours;
import net.kemitix.clover.spi.Effect;
import net.kemitix.clover.spi.OpaqueFill;
import net.kemitix.clover.spi.Region;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;


@With
@ApplicationScoped
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class BoxEffectImpl
        implements BoxEffect<Graphics2D>,
        Effect.RegionNext<Graphics2D>,
        BoxEffect.ThicknessNext<Graphics2D>,
        BoxEffect.ColourNext<Graphics2D> {

    @Inject
    Colours colours;
    @Inject OpaqueFill<Graphics2D> opaqueFill;

    private Region region;
    private int thickness;
    private String colour;
    private double opacity;

    @Override
    public void accept(Graphics2D drawable) {
        var pen =
                opaqueFill
                        .opacity(opacity)
                        .colour(colour);
        getSides()
                .forEach(side ->
                        pen.region(side).accept(drawable));
    }

    private List<Region> getSides() {
        Region top = region.withHeight(thickness);
        Region left = region.withWidth(thickness);
        Region right = region.withLeft(region.getRight() - thickness)
                .withWidth(thickness);
        Region bottom = region.withTop(region.getBottom() - thickness)
                .withHeight(thickness);
        return Arrays.asList(
                top, left,
                bottom, right
        );
    }

    @Override
    public ThicknessNext<Graphics2D> opacity(double opacity) {
        return withOpacity(opacity);
    }

    @Override
    public RegionNext<Graphics2D> colour(String colour) {
        return withColour(colour);
    }

    @Override
    public Consumer<Graphics2D> region(Region region) {
        return withRegion(region);
    }

    @Override
    public ColourNext<Graphics2D> thickness(int thickness) {
        return withThickness(thickness);
    }
}
