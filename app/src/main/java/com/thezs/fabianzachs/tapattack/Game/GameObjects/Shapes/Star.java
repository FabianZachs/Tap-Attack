package com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.thezs.fabianzachs.tapattack.Constants;

/**
 * Created by fabianzachs on 04/03/18.
 */

public class Star extends ShapeObject {


    public Star(float durationAlive, String color, Point centerLocation, Bitmap shapeImg, Bitmap shapeClickImg) {
        super(durationAlive, color, centerLocation, shapeImg, shapeClickImg);
        setLives(1);
        setProgressBarAddition(0);


        // handling touch events
        setmDetector(new GestureDetectorCompat(Constants.CURRENT_CONTEXT, new MyGestureListener()));

    }


    // TODO in the update we want the star to rotate every x milliseconds
    @Override
    public void update() {

    }


    // listens for specific touch events
    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";


        @Override
        public boolean onDown(MotionEvent event) {
            //Log.d(DEBUG_TAG,"onDown: " + event.toString());
            setState(1); // TODO only briefly
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

        @Override
        public void onLongPress(MotionEvent event) {
            Log.d(DEBUG_TAG, "onLongPress: " + event.toString());
        }
    }

}
