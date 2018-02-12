package com.thezs.fabianzachs.tapattack.Game.GameObjects;

import android.content.SharedPreferences;
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

/**
 * Created by fabianzachs on 07/02/18.
 */

public abstract class ShapeObject {

    private int progressBarAddition;
    private float durationAlive;
    private Rect bitmapHolder;  // rectangle to hold bitmap ?? needed??
    private long initTime;
    private String color;  // will be used to find correct bitmap img
    private int points;
    private int lives;
    private Point centerLocation;

    private GestureDetectorCompat mDetector;


    // TODO
    // for arrow shape make at least a little direction movement neccessary (small flick)  , so that
    // a simple press (with fractional vertical/ horizontal movement doesnt trigger fail/sucess


    public abstract void draw(Canvas canvas);
    public abstract void update();

    public ShapeObject(float durationAlive, String color, Point centerLocation) {
        this.durationAlive = durationAlive;
        this.color = color;
        this.centerLocation = centerLocation;
        this.points = 1; // all shapes are worth one point?
        this.initTime = System.currentTimeMillis();
        this.progressBarAddition = progressBarAddition;
        // code to make rectangle for holding the shape, then have subclasses call super for this?


        // handling touch events:
        mDetector = new GestureDetectorCompat(Constants.CURRENT_CONTEXT, new MyGestureListener());

    }


    private boolean isAlive() {
        // if time alive > durationAlive, object should be removed
        // AND if it still has lives
        return !(System.currentTimeMillis() - initTime > durationAlive) && lives > 0;
    }


    public void recieveTouch(MotionEvent event) {
        this.mDetector.onTouchEvent(event);
    }

    public void reduceLives() {
        this.lives -= 1;
    }

    // SETTERS & GETTERS

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



    // listens for specific touch events
    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";


        @Override
        public boolean onDown(MotionEvent event) {
            Log.d(DEBUG_TAG,"onDown: " + event.toString());
            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            StyleableToast.makeText(Constants.CURRENT_CONTEXT,  "fling", R.style.successtoast).show();
            Log.d(DEBUG_TAG, "onFling: " + event1.toString() + event2.toString());
            return true;
        }

        // The user has performed a down MotionEvent and not performed a move or up yet.
        @Override
        public void onShowPress(MotionEvent event) {
            Log.d(DEBUG_TAG, "onShowPress: " + event.toString());
        }

        @Override
        public boolean onSingleTapUp(MotionEvent event) {
            StyleableToast.makeText(Constants.CURRENT_CONTEXT,  "tap", R.style.successtoast).show();
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
