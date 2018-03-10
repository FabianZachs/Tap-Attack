package com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.Mediator.CentralGameCommunication;

/**
 * Created by fabianzachs on 02/03/18.
 */

public class Arrow extends ShapeObject {

    private double intendedFlickDirectionRadians;
    private String intendedFlickDirectionString;
    private final double FLICK_DIRECTION_ERROR_ALLOWANCE = Math.PI/4;
    private final Point originalPoint;
    private final int ARROW_TRAVEL_DISTANCE = 30;
    private long timeOfLastPenalty;



    public Arrow(float durationAlive, String color, Point centerLocation, Bitmap shapeImg, Bitmap shapeClickImg, String intendendedFlickDirection, Paint paint, Rect bitmapHolder, CentralGameCommunication mediator) {
        super(durationAlive, color, centerLocation, shapeImg, shapeClickImg, paint, bitmapHolder, mediator);
        setLives(1);
        setProgressBarAddition(15);

        this.intendedFlickDirectionString = intendendedFlickDirection;
        setIntendedFlickDirectionRadians(intendedFlickDirectionString);
        this.originalPoint = centerLocation;
        this.timeOfLastPenalty = 0;


        setmDetector(new GestureDetectorCompat(Constants.CURRENT_CONTEXT, new MyGestureListener()));

    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }


    @Override
    public void update() {}

    private void setIntendedFlickDirectionRadians(String direction) {

        switch (direction) {
            case "UP":
                intendedFlickDirectionRadians = Math.PI/2;
                break;
            case "LEFT":
                intendedFlickDirectionRadians = Math.PI;
                break;
            case "RIGHT":
                intendedFlickDirectionRadians = 0;
                break;
            case "DOWN":
                intendedFlickDirectionRadians = -Math.PI/2;
                break;
        }
    }



    // TODO reset arrow to original point if user scrolls out of error region
    // listens for specific touch events
    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";


        @Override
        public boolean onDown(MotionEvent event) {
            return true;
        }

        private boolean isCorrectFlick(float x1, float y1, float x2, float y2) {
            Double angle = Math.atan2(y1 - y2, x2 - x1);

            if (angle < -(Math.PI/2)  - FLICK_DIRECTION_ERROR_ALLOWANCE)
                angle = (2 * Math.PI) + angle;

            return (angle >= intendedFlickDirectionRadians - FLICK_DIRECTION_ERROR_ALLOWANCE &&
                    angle <= intendedFlickDirectionRadians + FLICK_DIRECTION_ERROR_ALLOWANCE);
        }


        // TODO maybe use this if someone slowly drags in direction
        @Override
        public boolean onScroll(MotionEvent event1, MotionEvent event2, float distanceX,
                                float distanceY) {
            try {
                if (isCorrectFlick(event1.getX(), event1.getY(), event2.getX(), event2.getY())) {
                    reduceLives();
                }
                else if (System.currentTimeMillis() - timeOfLastPenalty > 1000) {
                    mediator.changeProgressBarBy(PROGRESSBAR_REDUCTION_WITH_INCORRECT_TOUCH);
                    mediator.resetStreak();
                    timeOfLastPenalty = System.currentTimeMillis();

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }


        @Override
        public void onShowPress(MotionEvent event) {
        }

        @Override
        public boolean onSingleTapUp(MotionEvent event) {
            mediator.changeProgressBarBy(PROGRESSBAR_REDUCTION_WITH_INCORRECT_TOUCH);
            mediator.resetStreak();
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent event) {
            return true;
        }

        // TODO this is called if arrow double tapped then scrolled (called instead of scrolled)
        @Override
        public boolean onDoubleTapEvent(MotionEvent event) {
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent event) {
            mediator.changeProgressBarBy(-1);
            return true;
        }

        @Override
        public void onLongPress(MotionEvent event) {
        }

    }

    public String getIntendedFlickDirectionString() {
        return this.intendedFlickDirectionString;
    }



}
