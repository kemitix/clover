package net.kemitix.cossmass.clover.images.imglib;

import net.kemitix.cossmass.clover.images.CloverConfig;
import net.kemitix.cossmass.clover.images.FontFace;
import net.kemitix.cossmass.clover.images.Image;
import net.kemitix.cossmass.clover.images.XY;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import static java.awt.Image.SCALE_SMOOTH;

class CloverImage implements Image {

    private static final Logger LOGGER =
            Logger.getLogger(
                    CloverImage.class.getName());

    private final BufferedImage image;
    private final CloverConfig config;
    private final FontLoader fontLoader;

    CloverImage(
            final BufferedImage image,
            final CloverConfig config,
            final FontLoader fontLoader
    ) {
        this.image = image;
        this.config = config;
        this.fontLoader = fontLoader;
    }

    @Override
    public Image scaleToCover(
            final int width,
            final int height
    ) {
        LOGGER.info(String.format("Scaling to cover: %d x %d", width, height));
        final int originalWidth = getWidth();
        final int originalHeight = getHeight();
        final int ratio = originalWidth / originalHeight;
        LOGGER.info("Ratio: " + ratio);
        final int newWidth;
        final int newHeight;
        if (ratio > 1) { // is wide
            newWidth = height * ratio;
            newHeight = height;
        } else { // is tall
            newWidth = width;
            newHeight = width / ratio;
        }
        LOGGER.info(String.format("Resizing to %dx%d",
                newWidth, newHeight));
        return scaleTo(newWidth, newHeight);
    }

    private Image scaleTo(final int width, final int height) {
        final BufferedImage resized =
                new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        resized.createGraphics()
                .drawImage(
                        image.getScaledInstance(width, height, SCALE_SMOOTH),
                        0,
                        0,
                        null);
        return new CloverImage(resized, config, fontLoader);
    }

    public int getHeight() {
        return image.getHeight();
    }

    public int getWidth() {
        return image.getWidth();
    }

    @Override
    public Image crop(
            final int xOffset,
            final int yOffset,
            final int width,
            final int height
    ) {
        LOGGER.info(String.format("Cropping from %d x %d by %d x %d",
                xOffset, yOffset,
                width, height
        ));
        final BufferedImage cropped =
                new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        cropped.createGraphics()
                .drawImage(
                        image.getSubimage(xOffset, yOffset, width, height),
                        0,
                        0,
                        null);
        return new CloverImage(cropped, config, fontLoader);
    }

    @Override
    public void write(
            final Path path,
            final String name
    ) {
        LOGGER.info(String.format("Writing %s to %s", name, path));
        config.getImageTypes()
                .forEach(format -> {
                    final File file =
                            path.resolve(name + "." + format)
                                    .toFile();
                    write(format, file);
                });
    }

    @Override
    public Image withText(
            final String text,
            final XY xy,
            final FontFace fontFace
    ) {
        final BufferedImage withText = copyImage();
        final Graphics2D graphics = withText.createGraphics();
        graphics.setFont(fontLoader.loadFont(fontFace));
        graphics.setPaint(Color.getColor(fontFace.getColour()));
        LOGGER.info(String.format("Drawing text: %s at %dx%d - %d",
                text,
                xy.getX(),
                xy.getY() + fontFace.getSize(),
                fontFace.getSize()));
        graphics.drawString(text,
                xy.getX(),
                xy.getY() + fontFace.getSize());
        return new CloverImage(withText, config, fontLoader);
    }

    private BufferedImage copyImage() {
        final BufferedImage copy =
                new BufferedImage(getWidth(), getHeight(),
                        BufferedImage.TYPE_INT_ARGB);
        copy.createGraphics()
                .drawImage(image, 0, 0, null);
        return copy;
    }

    private void write(
            final String format,
            final File file
    ) {
        LOGGER.info(String.format("Writing %s file as %s", format, file));
        try {
            if (ImageIO.write(image, format, file)) {
                LOGGER.info("Wrote: " + file);
            } else {
                LOGGER.severe("No writer found for " + format);
            }
        } catch (final IOException e) {
            LOGGER.severe("Failed to write " + file);
        }
    }

}
