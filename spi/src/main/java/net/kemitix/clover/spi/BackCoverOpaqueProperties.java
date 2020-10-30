package net.kemitix.clover.spi;

public interface BackCoverOpaqueProperties {


    default boolean isEnableBackCoverBox() {
        return true;
    }

    default double getBackCoverBoxOuterOpacity() {
        return 1D;
    }

    default double getBackCoverBoxMidOpacity() {
        return 1D;
    }

    default double getBackCoverBoxInnerOpacity() {
        return 0.5D;
    }

    default String getBackCoverBoxOuterColour() {
        return "white";
    }

    default String getBackCoverBoxMidColour() {
        return "black";
    }

    default String getBackCoverBoxInnerColour() {
        return "black";
    }

    default int getBackCoverBoxOuterMargin() {
        return 80;
    }

    default int getBackCoverBoxMarginStep() {
        return 5;
    }

}
