package net.kemitix.clover.spi;

import java.net.URI;
import java.util.List;

public interface CloverProperties {
    List<String> getImageTypes();

    /**
     * Front cover height in inches.
     */
    float getHeight();

    /**
     * Front cover width in inches.
     */
    float getWidth();

    String getIssueDir();

    String getConfigFile();

    URI getFontLocation();

    int getDropShadowXOffset();

    int getDropShadowYOffset();

    int getDpi();

    int getBarcodeLeft();

    int getBarcodeTop();

    int getBarcodeWidth();

    int getBarcodeHeight();

    String getBarcodeFillColour();

    @Deprecated(since = "issue-3")
    Area getKindleFrontArea();

    boolean isEnablePdf();
    boolean isEnableWebp();
    boolean isEnableJpg();

    boolean isEnableKindle();
    boolean isEnablePaperback();
    boolean isEnablePaperbackPreview();

    float getTrim();//inches

    int getGuideThickness();

    String getTrimColour();

    float getTrimTop();//inches

    float getTrimLeft();//inches

    float getTrimRight();//inches

    float getTrimBottom();//inches
}
