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
public class OpaqueColour {
    private double opacity;
    private String colour;
}
