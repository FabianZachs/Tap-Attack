package com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.GraveObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes.ShapeObject;


public abstract class GraveObject {

    float GRAVE_DURATION;
    private long initializationTime;
    private Bitmap graveImage;
    Rect graveHolder;
    private Paint gravePaint;

    public GraveObject(ShapeObject shape, float GRAVE_DURATION) {
        this.GRAVE_DURATION = GRAVE_DURATION;
        this.initializationTime = System.currentTimeMillis();
        this.graveImage = shape.getGraveImage();
        this.graveHolder = shape.getBitmapHolder();
        this.gravePaint = shape.getPaint();
    }

    public Paint getGravePaint() {
        return gravePaint;
    }

    public Rect getGraveHolder() {
        return graveHolder;
    }

    public void setGravePaint(Paint gravePaint) {
        this.gravePaint = gravePaint;
    }

    public boolean isGraveDestroyed() {
        return System.currentTimeMillis() - initializationTime> GRAVE_DURATION * 1000;

    }
    long getTimeLeft() {
        return  (long) (GRAVE_DURATION * 1000) - (System.currentTimeMillis() - initializationTime);
    }


    public void update() {

    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(graveImage, null, graveHolder, gravePaint);

    }
}
