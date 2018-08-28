package com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class Star extends ShapeObject {

    public Star(Point centerLocation, Bitmap[] shapeImages, Integer color, Paint paint, Rect bitmapHolder, int shapeRadius) {
        super(centerLocation, shapeImages, false, 1, color, paint, bitmapHolder, shapeRadius);
    }

    @Override
    protected void playDeathSoundEffect() {

    }
}
