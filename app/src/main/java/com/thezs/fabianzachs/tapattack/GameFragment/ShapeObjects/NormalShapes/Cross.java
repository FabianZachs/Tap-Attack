package com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.GameFragment.Mediator;

public class Cross extends ShapeObject {

    public Cross(Mediator mediator, Point centerLocation, Bitmap[] shapeImages, Integer color, Paint paint, Rect bitmapHolder, int shapeRadius) {
        super(mediator, centerLocation, shapeImages, false, 1, color, paint, bitmapHolder, shapeRadius);
        setmDetector(new GestureDetectorCompat(Constants.CURRENT_CONTEXT, new MyGestureListener()));
    }

    @Override
    public void playDeathSoundEffect() {

    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent event) {
            reduceLives();
            return true;
        }

    }
}
