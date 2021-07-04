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
public class AuthorStrapBox {

    private boolean show;
    private int top;
    private int height;
    private OpaqueColour colour;

}
