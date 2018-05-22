package com.bobbythorne.ledcontroller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by NH229U11 on 11/29/2016.
 */
public class ColorView extends View {

    private final int paramOuterPadding = 2; // outer padding of the whole color picker view
    private final int paramInnerPadding = 3; // distance between value slider wheel and inner color wheel
    private final int paramValueSliderWidth = 7; // width of the value slider
    private final int paramArrowPointerSize = 4; // size of the arrow pointer; set to 0 to hide the pointer

    private Paint colorWheelPaint;






    public ColorView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public ColorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ColorView(Context context) {
        super(context);
        init();
    }

    private void init() {



        colorWheelPaint = new Paint();





    }
}
