package com.stealthcopter.colourmatrixexample.ColorMatrixHelpers;

/**
 * Created by matthew on 09/03/17.
 */

public class ColorMatrixPresets {

    private static final String NAME_IDENTITY = "Identify (Reset)";
    private static final String NAME_BLACK_AND_WHITE = "Greyscale";
    private static final String NAME_INVERT = "Invert";

    public static String[] getPresetNames() {
        return new String[]{NAME_IDENTITY, NAME_BLACK_AND_WHITE, NAME_INVERT};
    }

    public static ColorMatrixAndSaturation getPresetColorMatrix(String name) {
        switch (name) {
            case NAME_BLACK_AND_WHITE:
                return new ColorMatrixAndSaturation(new float[]{
                        1, 0, 0, 0, 0,
                        0, 1, 0, 0, 0,
                        0, 0, 1, 0, 0,
                        0, 0, 0, 1, 0
                }, 0f);
            case NAME_INVERT:
                return new ColorMatrixAndSaturation(new float[]{
                        -1, 0, 0, 0, 255,
                        0, -1, 0, 0, 255,
                        0, 0, -1, 0, 255,
                        0, 0, 0, 1, 0
                }, null);
            case NAME_IDENTITY:
            default:
                return new ColorMatrixAndSaturation(new float[]{
                        1, 0, 0, 0, 0,
                        0, 1, 0, 0, 0,
                        0, 0, 1, 0, 0,
                        0, 0, 0, 1, 0
                }, null);
        }


    }


}
