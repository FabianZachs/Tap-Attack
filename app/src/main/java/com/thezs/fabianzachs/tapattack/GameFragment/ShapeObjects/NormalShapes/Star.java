package com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes;

import android.graphics.Bitmap;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.thezs.fabianzachs.tapattack.Constants;

import java.util.ArrayList;
import java.util.Random;

public class Star extends ShapeObject {

    private float TIME_PER_COLOR = 0.5f;
    private long timeOfLastColorChange;
    private Integer[] intColors;
    private int colorIndex;

    public Star(Point centerLocation, Bitmap[] shapeImages, Integer color, Integer[] intColors, Paint paint, Rect bitmapHolder, int shapeRadius) {
        super(centerLocation, shapeImages, false, 1, color, paint, bitmapHolder, shapeRadius);
        this.timeOfLastColorChange = 0;
        this.intColors = intColors;
        Random randIndex = new Random();
        colorIndex = randIndex.nextInt(intColors.length);

        setmDetector(new GestureDetectorCompat(Constants.CURRENT_CONTEXT, new MyGestureListener()));
    }

    @Override
    public void update() {
        if (System.currentTimeMillis() - timeOfLastColorChange > TIME_PER_COLOR * 1000) {
            timeOfLastColorChange = System.currentTimeMillis();
            colorIndex++;
            colorIndex = colorIndex%(intColors.length);
            ColorFilter filter = new PorterDuffColorFilter(intColors[colorIndex], PorterDuff.Mode.SRC_IN);
            getPaint().setColorFilter(filter);
            setColor(intColors[colorIndex]);
        }
    }

    @Override
    protected void playDeathSoundEffect() {

    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            reduceLives();
            return true;
        }
    }
}
