package net.kemitix.clover.spi;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.enterprise.inject.Vetoed;
import javax.json.bind.annotation.JsonbProperty;

@Vetoed
@Setter
@Getter
@NoArgsConstructor
@ToString
public class BackCoverBackgroundBox {

    private boolean show;
    @JsonbProperty("outer-box")
    private Box outerBox;
    @JsonbProperty("middle-box")
    private Box middleBox;
    @JsonbProperty("inner-box")
    private Box innerBox;
    @JsonbProperty("outer-margin")
    private int outerMargin;
    @JsonbProperty("margin-step")
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
