package com.thezs.fabianzachs.tapattack.Game.GameObjects;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.R;

import static com.thezs.fabianzachs.tapattack.Game.MainThread.canvas;

/**
 * Created by fabianzachs on 07/02/18.
 */

public abstract class ShapeObject {

    private int progressBarAddition;
    private long distructionTime;
    private Bitmap[] shapeImages;
    private int stateAnimation = 0;
    private float durationAlive;
    private Rect bitmapHolder;  // rectangle to hold bitmap ?? needed??
    private long initTime;
    private String color;  // will be used to find correct bitmap img
    private int points;
    private int lives;
    private Point centerLocation;

    private float shapeScaling = .6f;

    private GestureDetectorCompat mDetector;


    // TODO
    // for arrow shape make at least a little direction movement neccessary (small flick)  , so that
    // a simple press (with fractional vertical/ horizontal movement doesnt trigger fail/sucess


    public abstract void draw(Canvas canvas);
    public abstract void update();

    public ShapeObject(float durationAlive, String color, Point centerLocation, Bitmap shapeImg, Bitmap shapeClickImg) {
        this.durationAlive = durationAlive;
        this.color = color;
        this.centerLocation = centerLocation;
        this.points = 1; // all shapes are worth one point?
        this.initTime = System.currentTimeMillis();
        this.progressBarAddition = progressBarAddition;
        this.shapeImages = new Bitmap[] {shapeImg, shapeClickImg};
        setBitmapHolder(new Rect((int) (centerLocation.x - (shapeScaling * Constants.SHAPE_WIDTH)), (int) (centerLocation.y - (shapeScaling * Constants.SHAPE_HEIGHT)),
                (int) (centerLocation.x + (shapeScaling * Constants.SHAPE_WIDTH)), (int) (centerLocation.y + (shapeScaling) * Constants.SHAPE_HEIGHT)));

        // handling touch events:
        mDetector = new GestureDetectorCompat(Constants.CURRENT_CONTEXT, new MyGestureListener());
    }


    // only for timing out
    public boolean isTimedOut() {
        //return !(System.currentTimeMillis() - initTime > durationAlive * 1000) && lives > 0;
        return (System.currentTimeMillis() - initTime > durationAlive * 1000);
    }



    public void reduceLives() {
        this.lives -= 1;
    }

    // SETTERS & GETTERS

    public Point getCenterLocation() {
        return this.centerLocation;
    }

    public Bitmap getCurrentShapeImg() {
        return this.shapeImages[getState()];
    }

    public Bitmap getShapeImg() {
        return this.shapeImages[0];
    }

    public Bitmap getShapeClickImg() {
        return this.shapeImages[1];
    }


    public void setProgressBarAddition(int progressBarAddition) {
        this.progressBarAddition = progressBarAddition;
    }

    public int getProgressBarAddition() {
        return this.progressBarAddition;
    }

    public void setBitmapHolder(Rect rect) {
        this.bitmapHolder = rect;
    }

    public Rect getBitmapHolder() {
        return this.bitmapHolder;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getLives() {
        return this.lives;
    }

    public void setDurationAlive(float durationAlive) {
        this.durationAlive = durationAlive;
    }

    public float getDurationAlive() {
        return this.durationAlive;
    }

    public void setInitTime(long initTime) {
        this.initTime = initTime;
    }

    public long getInitTime() {
        return this.initTime;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPoints() {
        return this.points;
    }

    private void setState(int state) {
        this.stateAnimation = state;
    }

    public int getState() {
        return this.stateAnimation;
    }

    public void recieveTouch(MotionEvent event) {
        this.mDetector.onTouchEvent(event);
    }



    // listens for specific touch events
    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";


        @Override
        public boolean onDown(MotionEvent event) {
            //Log.d(DEBUG_TAG,"onDown: " + event.toString());
            reduceLives();
            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            return true;
        }

        // The user has performed a down MotionEvent and not performed a move or up yet.
        @Override
        public void onShowPress(MotionEvent event) {
            Log.d(DEBUG_TAG, "onShowPress: " + event.toString());
        }

        @Override
        public boolean onSingleTapUp(MotionEvent event) {
            Log.d(DEBUG_TAG, "onSingleTapUp: " + event.toString());
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent event) {
            Log.d(DEBUG_TAG, "onDoubleTap: " + event.toString());
            return true;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent event) {
            Log.d(DEBUG_TAG, "onDoubleTapEvent: " + event.toString());
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent event) {
            Log.d(DEBUG_TAG, "onSingleTapConfirmed: " + event.toString());
            return true;
        }

        // we want to put shape back to original state
        @Override
        public void onLongPress(MotionEvent event) {
            Log.d(DEBUG_TAG, "onLongPress: " + event.toString());
        }

    }


}
