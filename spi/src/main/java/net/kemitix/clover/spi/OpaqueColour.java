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
public class OpaqueColour {
    private double opacity;
    private String colour;
}
