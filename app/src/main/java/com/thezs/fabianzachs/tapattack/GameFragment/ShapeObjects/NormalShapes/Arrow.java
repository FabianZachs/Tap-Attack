package com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.thezs.fabianzachs.tapattack.Constants;


public class Arrow extends ShapeObject {

    private String intendedFlickDirectionString;
    private double intendedFlickDirectionRadians;

    public Arrow(Point centerLocation, Bitmap[] shapeImages, Integer color, Paint paint, Rect bitmapHolder, int shapeRadius, String intendedFlickDirectionString) {
        super(centerLocation, shapeImages, true, 1, color, paint, bitmapHolder, shapeRadius);
        this.intendedFlickDirectionString = intendedFlickDirectionString;
        intendedFlickDirectionRadians = getIntendedFlickDirectionRadians(intendedFlickDirectionString);

        setmDetector(new GestureDetectorCompat(Constants.CURRENT_CONTEXT, new MyGestureListener()));
    }

    @Override
    protected void playDeathSoundEffect() {

    }

    public String getIntendedFlickDirectionString() {
        return this.intendedFlickDirectionString;
    }

    private double getIntendedFlickDirectionRadians(String direction) {

        switch (direction) {
            case "left":
                return Math.PI;
            case "right":
                return 0;
            case "up":
                return Math.PI/2;
            case "down":
                return -Math.PI/2;
        }
        throw new IllegalArgumentException("Unknown arrow flick direction");
    }

    private boolean isCorrectFlick(float x1, float y1, float x2, float y2) {
        Double angle = Math.atan2(y1 - y2, x2 - x1); // more similar to unit circle
        double FLICK_DIRECTION_LENIENCY = Math.PI/2;

        switch (intendedFlickDirectionString) {
            case "right":
                return (angle >= intendedFlickDirectionRadians - FLICK_DIRECTION_LENIENCY &&
                        angle <= intendedFlickDirectionRadians + FLICK_DIRECTION_LENIENCY);

            // todo up and down havent been checked since they wont be in the game likely
            case "up":
                return (angle >= intendedFlickDirectionRadians - FLICK_DIRECTION_LENIENCY &&
                        angle <= intendedFlickDirectionRadians + FLICK_DIRECTION_LENIENCY);
            case "down":

                return (angle >= intendedFlickDirectionRadians - FLICK_DIRECTION_LENIENCY &&
                        angle <= intendedFlickDirectionRadians + FLICK_DIRECTION_LENIENCY);
            case "left":
                angle = getUnitCircleVersion(angle);

                return (angle >= intendedFlickDirectionRadians - FLICK_DIRECTION_LENIENCY &&
                        angle <= intendedFlickDirectionRadians + FLICK_DIRECTION_LENIENCY);
        }
        return false;
    }

    private Double getUnitCircleVersion(Double angle) {
        if (angle < 0) {
            angle = Math.PI + (angle + Math.PI);
        }
        return angle;
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onScroll(MotionEvent event1, MotionEvent event2, float distanceX, float distanceY) {
            try {
                if (isCorrectFlick(event1.getX(), event1.getY(), event2.getX(), event2.getY())) {
                    reduceLives();
                }
                else {
                    setIncorrectTouchAndReason("fling" + Math.atan2(event1.getY()-event2.getY(),event2.getX()-event1.getX()) );
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            setIncorrectTouchAndReason("tap");
            return true;
        }
    }
}
