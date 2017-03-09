package com.stealthcopter.colourmatrixexample.ColorMatrixHelpers;

/**
 * Created by matthew on 09/03/17.
 */

public class ColorMatrixAndSaturation{

    float[] colorMatrix;
    Float saturation;

    public ColorMatrixAndSaturation(float[] colorMatrix, Float saturation){
        this.colorMatrix = colorMatrix;
        this.saturation = saturation;
    }

    public float[] getColorMatrix() {
        return colorMatrix;
    }

    public Float getSaturation() {
        return saturation;
    }

}
