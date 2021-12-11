package net.kemitix.clover.spi;

import jakarta.enterprise.inject.Vetoed;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Vetoed
@Setter
@Getter
@NoArgsConstructor
@ToString
public class BackCoverBackgroundBox {

    private boolean show;
    private OpaqueColour outerColour;
    private OpaqueColour middleColour;
    private OpaqueColour innerColour;
    private int outerMargin;
    private int marginStep;

}
