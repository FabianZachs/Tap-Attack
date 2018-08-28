package com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class Arrow extends ShapeObject {
    public Arrow(Point centerLocation, Bitmap[] shapeImages, Integer color, Paint paint, Rect bitmapHolder, int shapeRadius) {
        super(centerLocation, shapeImages, true, 1, color, paint, bitmapHolder, shapeRadius);
    }

    @Override
    protected void playDeathSoundEffect() {

    }
}
