package com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes;

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
    private int animationState;
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
        this.animationState = 0;

        updateBitmapHolder();
    }


    public void draw(Canvas canvas) {
        canvas.drawBitmap(getCurrentShapeImg(), null, bitmapHolder, paint);

    }

    public void recieveTouch(MotionEvent event) {
        this.mDetector.onTouchEvent(event);
    }

    public void update() {

    }

    public void incrementY(float amount) {
        centerLocation.y += amount;
        bitmapHolder.bottom += amount;
        bitmapHolder.top += amount;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public Paint getPaint() {
        return paint;
    }

    public Bitmap getGraveImage() {
        return shapeImages[shapeImages.length - 1];
    }

    public boolean isGraveable() {
        return graveable;
    }

    public Rect getBitmapHolder() {
        return bitmapHolder;
    }

    public int getLives() {
        return lives;
    }

    void reduceLives() {
        lives--;
    }

    void increaseLives() {
        lives++;
    }
    protected abstract void playDeathSoundEffect();


    private Bitmap getCurrentShapeImg() {
        return this.shapeImages[getAnimationState()];
    }

    void setAnimationState(int index) {
        animationState = index;
    }

    int getAnimationState() {
        return animationState;
    }

    void setIncorrectTouchAndReason(String reason) {
        this.incorrectTouch = true;
        this.typeOfIncorrectTouch = reason;
    }

    public boolean wasIncorrectTouch() {
        return this.incorrectTouch;
    }

    public String getTypeOfIncorrectTouch() {
        return this.typeOfIncorrectTouch;
    }

    private void updateBitmapHolder() {
        bitmapHolder.set(centerLocation.x - shapeRadius, centerLocation.y - shapeRadius,
                centerLocation.x + shapeRadius, centerLocation.y + shapeRadius);

    }

    public void updateCenterLocation(Point point) {
        // todo or relative, so like updateXCenterLocation/ updateYCenterLocation
        updateBitmapHolder();
    }

    public int getShapeRadius() {
        return shapeRadius;
    }

    public Point getCenterLocation() {
        return centerLocation;
    }

    public Integer getColor() {
        return color;
    }

    void setmDetector(GestureDetectorCompat mDetector) {
        this.mDetector = mDetector;
    }
}
