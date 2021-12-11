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
public class AuthorStrapBox {

    private boolean show;
    private int top;
    private int height;
    private OpaqueColour colour;

}
