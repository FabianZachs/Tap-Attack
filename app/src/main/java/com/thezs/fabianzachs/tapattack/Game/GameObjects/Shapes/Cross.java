package com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.Mediator.CentralGameCommunication;

/**
 * Created by fabianzachs on 25/02/18.
 */

public class Cross extends ShapeObject {

    // TODO make cross flash (like in piano game) when clicked
    public Cross(float durationAlive, String color, Point centerLocation, Bitmap shapeImg, Bitmap shapeClickImg, Paint paint, Rect bitmapHolder, CentralGameCommunication mediator) {
        // call super(durationAlive, color) then in super also make the rect to hold bitmap
        super(durationAlive, color, centerLocation, shapeImg, shapeClickImg, paint, bitmapHolder, mediator);
        setLives(1);
        setProgressBarAddition(-100);


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


    // TODO set gameOver for all of these if touch recieved
    // listens for specific touch events
    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";


        @Override
        public boolean onDown(MotionEvent event) {
            //Log.d(DEBUG_TAG,"onDown: " + event.toString());
            //SceneManager.setGameOver(true);
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
            //Log.d(DEBUG_TAG, "onShowPress: " + event.toString());
        }

        @Override
        public boolean onSingleTapUp(MotionEvent event) {
            //Log.d(DEBUG_TAG, "onSingleTapUp: " + event.toString());
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent event) {
            //Log.d(DEBUG_TAG, "onDoubleTap: " + event.toString());
            return true;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent event) {
            //Log.d(DEBUG_TAG, "onDoubleTapEvent: " + event.toString());
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent event) {
            //Log.d(DEBUG_TAG, "onSingleTapConfirmed: " + event.toString());
            return true;
        }

        // we want to put shape back to original state
        @Override
        public void onLongPress(MotionEvent event) {
            //Log.d(DEBUG_TAG, "onLongPress: " + event.toString());
        }
    }
}
