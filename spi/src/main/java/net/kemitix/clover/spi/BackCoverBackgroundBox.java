package net.kemitix.clover.spi;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.enterprise.inject.Vetoed;

@Vetoed
@Setter
@Getter
@NoArgsConstructor
@ToString
public class BackCoverBackgroundBox {

    private boolean show;
    private Box outerBox;
    private Box middleBox;
    private Box innerBox;
    private int outerMargin;
    private int marginStep;

    @Vetoed
    @Setter
    @Getter
    @NoArgsConstructor
    @ToString
    public static class Box {
        private double opacity;
        private String colour;
    }

}
