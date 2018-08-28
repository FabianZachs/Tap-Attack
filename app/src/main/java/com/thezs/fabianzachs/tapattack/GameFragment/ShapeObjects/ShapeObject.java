package com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v4.view.GestureDetectorCompat;
import android.view.MotionEvent;

public abstract class ShapeObject {

    //private Mediator mediator; //todo for sound effect

    private Point centerLocation;
    private Bitmap[] shapeImages;
    private Bitmap currentShapeImage;
    private boolean graveable;
    private int lives;
    private Integer color;
    private Paint paint;
    private Rect bitmapHolder;
    private int shapeRadius;
    private GestureDetectorCompat mDetector;


    private boolean incorrectTouch;
    private String typeOfIncorrectTouch;

    public ShapeObject(Point centerLocation, Bitmap[] shapeImages, boolean graveable, int lives, Integer color, Paint paint, Rect bitmapHolder, int shapeRadius) {
        this.centerLocation = centerLocation;
        this.shapeImages = shapeImages;
        this.graveable = graveable;
        this.lives = lives;
        this.color = color;
        this.paint = paint;
        this.bitmapHolder = bitmapHolder;
        this.shapeRadius = shapeRadius;

        updateBitmapHolder();
    }


    public void draw(Canvas canvas) {
        canvas.drawBitmap(currentShapeImage, null, bitmapHolder, paint);

    }

    public void recieveTouch(MotionEvent event) {
        this.mDetector.onTouchEvent(event);
    }

    public void update() {

    }

    void reduceLives() {
        lives--;
    }

    protected abstract void playDeathSoundEffect();

    protected void setCurrentShapeImage(int index) {
        currentShapeImage = shapeImages[index];
    }

    private void updateBitmapHolder() {
        bitmapHolder.set(centerLocation.x - shapeRadius, centerLocation.y - shapeRadius,
                centerLocation.x + shapeRadius, centerLocation.y + shapeRadius);

    }

    public void updateCenterLocation(Point point) {
        // todo or relative, so like updateXCenterLocation/ updateYCenterLocation
        updateBitmapHolder();
    }

    void setmDetector(GestureDetectorCompat mDetector) {
        this.mDetector = mDetector;
    }
}
