package net.kemitix.cossmass.clover;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.function.Function;

@Dependent
public class FontLoader {

    @Produces
    public Function<String, Font> fontLoader() {
        return font -> {
            try {
                return Font.createFont(Font.TRUETYPE_FONT, new File(font));
            } catch (final FontFormatException | IOException e) {
                throw new RuntimeException("Font load error", e);
            }
        };
    }

}
