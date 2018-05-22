package com.bobbythorne.ledcontroller;

import java.util.UUID;

/**
 * Created by Bobby Thorne on 11/28/2016.
 */
public class ColorPick {
    private UUID mPresetId;
    private UUID mColorId;
    private int mColor;
//    private int mAlpha;
//    private int mRed;
//    private int mGreen;
//    private int mBlue;


    public ColorPick(UUID presetId) {
        mPresetId = presetId;
        mColorId = UUID.randomUUID();
    }

    public ColorPick(UUID colorId, UUID presetId) {
        mColorId = colorId;
        mPresetId = presetId;
    }

    public UUID getColorId() {
        return mColorId;
    }

    public UUID getPresetId() {
        return mPresetId;
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        mColor = color;
    }


//    public void setColor(int alpha, int red, int green, int blue){
//        mRed = red;
//        mGreen = green;
//        mBlue = blue;
//        mAlpha = alpha;
//
//        mColor = Color.argb(alpha,red,green,blue);

//    }

}
