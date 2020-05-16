package net.kemitix.clover.image.effects;

import lombok.Getter;
import net.kemitix.clover.spi.FontCache;
import net.kemitix.clover.spi.FontFace;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Split the string text such that when rendered using the FontFace in the
 * Graphics2D context it fits within width.
 */
@ApplicationScoped
public class WordWrapper {

    @Inject FontCache fontCache;

    public String[] wrap(
            String text,
            FontFace fontFace,
            Graphics2D graphics2D,
            int width
    ) {
        System.out.println("width = " + width);
        String source = String.join(" ", text.split("\n"));
        Font font = fontCache.loadFont(fontFace);
        List<Word> words = wordLengths(source.split(" "), font, graphics2D);
        List<String> lines = wrapWords(words, width);
        return lines.toArray(new String[]{});
    }

    private List<String> wrapWords(List<Word> words, int width) {
        List<String> lines = new ArrayList<>();
        int end = 0;
        List<String> line = new ArrayList<>();
        for (Word word : words) {
            if ((end + word.width) > width) {
                lines.add(String.join(" ", line));
                line.clear();
                end = 0;
            }
            line.add(word.word);
            end += word.width;
            System.out.println(String.format("end %d - (%d - %s)",
                    end, word.width, word.word));
        }
        lines.add(String.join(" ", line));
        return lines.stream()
                .filter(l -> l.length() > 0)
                .collect(Collectors.toList());
    }

    private List<Word> wordLengths(String[] words, Font font, Graphics2D graphics2D) {
        FontRenderContext fontRenderContext = graphics2D.getFontRenderContext();
        return Arrays.stream(words)
                .map(word -> new Word(word, font, fontRenderContext))
                .collect(Collectors.toList());
    }

    @Getter
    private static class Word {
        private final String word;
        private final int width;

        public Word(String word, Font font, FontRenderContext fontRenderContext) {
            this.word = word;
            Rectangle2D stringBounds = font.getStringBounds(word + " ", fontRenderContext);
            this.width = Double.valueOf(stringBounds.getWidth()).intValue();
        }
    }
}
