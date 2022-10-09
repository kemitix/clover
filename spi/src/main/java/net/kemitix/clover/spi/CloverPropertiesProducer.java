package net.kemitix.clover.spi;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;

import java.io.File;

@ApplicationScoped
public class CloverPropertiesProducer {

    @Produces
    CloverProperties cloverProperties() {
        Config config = ConfigProvider.getConfig();
        return new CloverProperties() {
            @Override
            public String imageTypes() {
                return config.getValue("clover.image-types", String.class);
            }

            @Override
            public String configFile() {
                return config.getValue("clover.config-file", String.class);
            }

            @Override
            public File fontFile() {
                return config.getValue("clover.font-file", File.class);
            }

            @Override
            public int dropShadowXOffset() {
                return config.getValue("clover.drop-shadow-x-offset", Integer.class);
            }

            @Override
            public int dropShadowYOffset() {
                return config.getValue("clover.drop-shadow-y-offset", Integer.class);
            }

            @Override
            public int dpi() {
                return config.getValue("clover.dpi", Integer.class);
            }

            @Override
            public int barcodeRightPadding() {
                return config.getValue("clover.barcode-right-padding", Integer.class);
            }

            @Override
            public int barcodeBottomPadding() {
                return config.getValue("clover.barcode-bottom-padding", Integer.class);
            }

            @Override
            public float barcodeWidth() {
                return config.getValue("clover.barcode-width", Float.class);
            }

            @Override
            public float barcodeHeight() {
                return config.getValue("clover.barcode-height", Float.class);
            }

            @Override
            public String barcodeFillColour() {
                return config.getValue("clover.barcode-fill-colour", String.class);
            }

            @Override
            public boolean enablePdf() {
                return config.getValue("clover.enable-pdf", Boolean.class);
            }

            @Override
            public boolean enableWebp() {
                return config.getValue("clover.enable-webp", Boolean.class);
            }

            @Override
            public boolean enableJpg() {
                return config.getValue("clover.enable-jpg", Boolean.class);
            }

            @Override
            public boolean enableKindle() {
                return config.getValue("clover.enable-kindle", Boolean.class);
            }

            @Override
            public boolean enablePaperback() {
                return config.getValue("clover.enable-paperback", Boolean.class);
            }

            @Override
            public boolean enablePaperbackPreview() {
                return config.getValue("clover.enable-paperback-preview", Boolean.class);
            }

            @Override
            public float trim() {
                return config.getValue("clover.trim", Float.class);
            }

            @Override
            public int guideThickness() {
                return config.getValue("clover.guide-thickness", Integer.class);
            }

            @Override
            public String trimColour() {
                return config.getValue("clover.trim-colour", String.class);
            }

            @Override
            public float trimTop() {
                return config.getValue("clover.trim-top", Float.class);
            }

            @Override
            public float trimLeft() {
                return config.getValue("clover.trim-left", Float.class);
            }

            @Override
            public float trimRight() {
                return config.getValue("clover.trim-right", Float.class);
            }

            @Override
            public float trimBottom() {
                return config.getValue("clover.trim-bottom", Float.class);
            }

            @Override
            public int guideLinesPerInch() {
                return config.getValue("clover.guide-lines-per-inch", Integer.class);
            }
        };
    }

}
