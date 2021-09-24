package net.kemitix.clover.issue;

import lombok.Getter;
import net.kemitix.clover.spi.AbstractElement;
import net.kemitix.clover.spi.CloverProperties;
import net.kemitix.clover.spi.Colours;
import net.kemitix.clover.spi.GuideLines;
import net.kemitix.clover.spi.IssueConfig;
import net.kemitix.clover.spi.IssueDimensions;
import net.kemitix.properties.typed.TypedProperties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Vetoed;
import javax.inject.Inject;
import java.awt.*;
import java.util.stream.IntStream;

@Vetoed
//@GuideLines
//@ApplicationScoped
public class GridGuide
        extends AbstractElement {

    @Inject CloverProperties cloverProperties;
    @Inject IssueConfig issueConfig;
    @Inject IssueDimensions dimensions;
    @Inject Colours colours;
    private Paint black;
    private Paint white;
    private float widthInches;
    private float heightInches;
    private int widthPx;
    private int heightPx;

    @PostConstruct
    public void init() {
        black = colours.getColor("black");
        white = colours.getColor("white");
        widthInches = (2 * issueConfig.getWidth()) + issueConfig.getSpine();
        heightInches = issueConfig.getHeight();
        widthPx = dpi(widthInches);
        heightPx = dpi(heightInches);
    }

    @Getter
    private final int priority = 10;
    @Override
    public void draw(
            Graphics2D drawable,
            TypedProperties typedProperties
    ) {
        int stepsPerInch = cloverProperties.guideLinesPerInch();
        float stepSizeInches = (float) 1 / stepsPerInch;

        int xSteps = (int) (widthInches * stepsPerInch);
        IntStream.range(0, xSteps)
                .mapToDouble(step -> (step * stepSizeInches))
                .mapToInt(this::dpi)
                .forEach(x -> vLine(drawable, x));
        int ySteps = (int) (heightInches * stepsPerInch);
        IntStream.range(0, ySteps)
                .mapToDouble(step -> (step * stepSizeInches))
                .mapToInt(this::dpi)
                .forEach(y -> hLine(drawable, y));
    }

    private void hLine(
            Graphics2D drawable,
            int y
    ) {
        drawable.setPaint(white);
        drawable.drawLine(0, y, widthPx, y);
        drawable.setPaint(black);
        drawable.drawLine(0, y - 1, widthPx, y - 1);
        drawable.drawLine(0, y + 1, widthPx, y + 1);
    }

    private void vLine(
            Graphics2D drawable,
            int x
    ) {
        drawable.setPaint(white);
        drawable.drawLine(x, 0, x, heightPx);
        drawable.setPaint(black);
        drawable.drawLine(x - 1, 0, x + 1, heightPx);
        drawable.drawLine(x + 1, 0, x + 1, heightPx);
    }


    private int dpi(double inches) {
        return (int) (inches * cloverProperties.dpi());
    }

}
