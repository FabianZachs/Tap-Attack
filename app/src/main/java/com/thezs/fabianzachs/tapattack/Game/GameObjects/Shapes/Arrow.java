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
import android.widget.Toast;

import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.GameOverReasons;
import com.thezs.fabianzachs.tapattack.Game.Mediator.CentralGameCommunication;
import com.thezs.fabianzachs.tapattack.R;

import java.util.ArrayList;

/**
 * Created by fabianzachs on 02/03/18.
 */

public class Arrow extends ShapeObject {

    private double intendedFlickDirectionRadians;
    private String intendedFlickDirectionString;
    private final Point originalPoint;
    private final int ARROW_TRAVEL_DISTANCE = 30;
    private long timeOfLastPenalty;
    private double FLICK_DIRECTION_LENIENCY = Math.PI/4;



    public Arrow(float durationAlive, String color, Integer colorInt, Point centerLocation, Bitmap shapeImg, Bitmap shapeClickImg, String intendendedFlickDirection, Paint paint, Rect bitmapHolder, CentralGameCommunication mediator) {
        super(durationAlive, color, colorInt, centerLocation, shapeImg, shapeImg, paint, bitmapHolder, mediator);
        setLives(1);
        setProgressBarAddition(15);
        setGraveAble(false); // todo fix grave for arrow

        this.originalPoint = centerLocation;
        this.timeOfLastPenalty = 0;
        this.intendedFlickDirectionString = intendendedFlickDirection;
        setIntendedFlickDirectionRadians(intendedFlickDirectionString);

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
            case "LEFT":
                intendedFlickDirectionRadians = Math.PI;
                break;
            case "RIGHT":
                intendedFlickDirectionRadians = 0;
                break;
            case "UP":
                intendedFlickDirectionRadians = Math.PI/2;
                break;
            case "DOWN":
                intendedFlickDirectionRadians = -Math.PI/2;
                break;
        }
    }

    private boolean isCorrectFlick(float x1, float y1, float x2, float y2) {
        Double angle = Math.atan2(y1 - y2, x2 - x1); // more similar to unit circle
        //Double angle = Math.atan2(y1-y2,x1-x2);

        Log.d("anglefinder", "isCorrectFlick: " + angle + " " + intendedFlickDirectionString);

        switch (intendedFlickDirectionString) {
            case "RIGHT":
                //Log.d("anglefinder", "isCorrectFlick: " + angle + " " + intendedFlickDirectionString);
                return (angle >= intendedFlickDirectionRadians - FLICK_DIRECTION_LENIENCY &&
                        angle <= intendedFlickDirectionRadians + FLICK_DIRECTION_LENIENCY);

                // todo up and down havent been checked since they wont be in the game likely
            case "UP":
                //Log.d("anglefinder", "isCorrectFlick: " + angle + " " + intendedFlickDirectionString);
                return (angle >= intendedFlickDirectionRadians - FLICK_DIRECTION_LENIENCY &&
                        angle <= intendedFlickDirectionRadians + FLICK_DIRECTION_LENIENCY);
            case "DOWN":
                //Log.d("anglefinder", "isCorrectFlick: " + angle + " " + intendedFlickDirectionString);

                return (angle >= intendedFlickDirectionRadians - FLICK_DIRECTION_LENIENCY &&
                        angle <= intendedFlickDirectionRadians + FLICK_DIRECTION_LENIENCY);
            case "LEFT":
                angle = getUnitCircleVersion(angle);
                //Log.d("anglefinder", "isCorrectFlick: " + angle + " " + intendedFlickDirectionString);

                return (angle >= intendedFlickDirectionRadians - FLICK_DIRECTION_LENIENCY &&
                        angle <= intendedFlickDirectionRadians + FLICK_DIRECTION_LENIENCY);


        }
        return false;

        /*
        angle = getUnitCircleVersion(angle);

        StyleableToast.makeText(Constants.CURRENT_CONTEXT, angle + "", R.style.successtoast).show();
        angle = angle % (2* Math.PI);

        return (angle >= intendedFlickDirectionRadians - Math.PI/4 &&
                angle <= intendedFlickDirectionRadians + Math.PI/4);
                */

        /*
        if (angle < -(Math.PI/2)  - Math.PI/4)
            angle = (2 * Math.PI) + angle;
        StyleableToast.makeText(Constants.CURRENT_CONTEXT, angle + "", R.style.successtoast).show();

        return (angle >= intendedFlickDirectionRadians - Math.PI/4 &&
                angle <= intendedFlickDirectionRadians + Math.PI/4);
        */
    }

    private Double getUnitCircleVersion(Double angle) {
        if (angle < 0) {
            angle = Math.PI + (angle + Math.PI);
        }
        return angle;
    }

    public String getIntendedFlickDirectionString() {
        return this.intendedFlickDirectionString;
    }


    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent event) {
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent event1, MotionEvent event2, float distanceX,
                                float distanceY) {
            try {
                if (isCorrectFlick(event1.getX(), event1.getY(), event2.getX(), event2.getY())) {
                    if (getLives() > 0)
                        mediator.arrowSoundEffect();
                    reduceLives();
                }
                else if (System.currentTimeMillis() - timeOfLastPenalty > 1000) {
                    //mediator.changeProgressBarBy(PROGRESSBAR_REDUCTION_WITH_INCORRECT_TOUCH);
                    //mediator.resetStreak();
                    setIncorrectTouchAndReason("fling");
                    //mediator.setGameOver(GameOverReasons.wrongShapeAction(Arrow.this, "fling", "in that direction " + intendedFlickDirectionString+" " + Math.atan2(event1.getY() - event2.getY(), event2.getX() - event1.getX())));
                    //mediator.setGameOver(GameOverReasons.wrongShapeAction(Arrow.this));
                    //StyleableToast.makeText(Constants.CURRENT_CONTEXT, "wrong flick direction", R.style.successtoast).show();
                    timeOfLastPenalty = System.currentTimeMillis();

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent event) {
            //mediator.changeProgressBarBy(PROGRESSBAR_REDUCTION_WITH_INCORRECT_TOUCH);
            //mediator.resetStreak();
            setIncorrectTouchAndReason("tap");
            //mediator.setGameOver(GameOverReasons.wrongShapeAction(Arrow.this, "tap", ""));
            //mediator.setGameOver(GameOverReasons.wrongShapeAction(Arrow.this));
            StyleableToast.makeText(Constants.CURRENT_CONTEXT, "single tap up", R.style.successtoast).show();
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
    }

}
