package com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.thezs.fabianzachs.tapattack.Constants;

/**
 * Created by fabianzachs on 02/03/18.
 */

public class Arrow extends ShapeObject {

    private int intendedFlickDirectionInDegrees; // unit circle time degrees
    private final int FLICK_DIRECTION_ERROR_ALLOWANCE = 20;



    public Arrow(float durationAlive, String color, Point centerLocation, Bitmap shapeImg, Bitmap shapeClickImg, int intendedFlickDirectionInDegrees) {
        // call super(durationAlive, color) then in super also make the rect to hold bitmap
        super(durationAlive, color, centerLocation, shapeImg, shapeClickImg);
        setIntendedFlickDirection(intendedFlickDirectionInDegrees);
        setLives(1);
        setProgressBarAddition(15);


        // handling touch events
        setmDetector(new GestureDetectorCompat(Constants.CURRENT_CONTEXT, new MyGestureListener()));

    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }


    @Override
    public void update() {
    }

    private void setIntendedFlickDirection(int direction) {
        this.intendedFlickDirectionInDegrees = direction % 360;
    }


    // listens for specific touch events
    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";


        @Override
        public boolean onDown(MotionEvent event) {
            //Log.d(DEBUG_TAG,"onDown: " + event.toString());
            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            Log.d(DEBUG_TAG, "onFling: " + event1.toString() + event2.toString());

            if (isCorrectFlick(event1.getX(), event1.getY(), event2.getX(), event2.getY()))
                ;
                //reduceLives(); // TODO arrow grave should move in flick direction
            else
                ; // TODO implement decriment to progress bar (add wrong flick attribute to decriment progress bar)

            return true;

        }

        private boolean isCorrectFlick(float x1, float y1, float x2, float y2) {
            Double angle = Math.toDegrees(Math.atan2(y1 - y2, x2 - x1));
            Log.d("flickdebug", "isCorrectFlick angle: " + angle);

            return (angle >= intendedFlickDirectionInDegrees - FLICK_DIRECTION_ERROR_ALLOWANCE &&
                    angle <= intendedFlickDirectionInDegrees + FLICK_DIRECTION_ERROR_ALLOWANCE);
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

        // TODO maybe use this if someone slowly drags in direction
        @Override
        public boolean onScroll(MotionEvent event1, MotionEvent event2, float distanceX,
                                float distanceY) {
            Log.d(DEBUG_TAG, "onScroll: " + event1.toString() + event2.toString());
            return true;
        }
    }



}
