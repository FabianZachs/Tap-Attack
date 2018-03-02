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

    private int intendedFlickDirectionInDegrees; //[-180, 180]
    private final int FLICK_DIRECTION_ERROR_ALLOWANCE = 20;
    private final Point originalPoint;
    private final Point destinationPoint;
    private final int ARROW_TRAVEL_DISTANCE = 90;




    public Arrow(float durationAlive, String color, Point centerLocation, Bitmap shapeImg, Bitmap shapeClickImg, int intendedFlickDirectionInDegrees) {
        // call super(durationAlive, color) then in super also make the rect to hold bitmap
        super(durationAlive, color, centerLocation, shapeImg, shapeClickImg);


        setIntendedFlickDirection(intendedFlickDirectionInDegrees);
        this.originalPoint = centerLocation;
        this.destinationPoint = getDestinationPoint();  // TODO use ARROW_TRAVEL_DISTANCE to see where arrow needs to get


        Log.d("arrow", "Arrow: start: " + originalPoint);
        Log.d("arrow", "Arrow: end: " + destinationPoint);

        setLives(1);
        setProgressBarAddition(15);


        // handling touch events
        setmDetector(new GestureDetectorCompat(Constants.CURRENT_CONTEXT, new MyGestureListener()));

    }

    // TODO use this to see if we can create a arrow at a specific location?? or just make arrow spawn further from edge
    public Point getDestinationPoint() {

        int x = (int) (originalPoint.x + (Math.cos(Math.toRadians(intendedFlickDirectionInDegrees)) *
                                            ARROW_TRAVEL_DISTANCE));

        int y = (int) (originalPoint.y - (Math.sin(Math.toRadians(intendedFlickDirectionInDegrees)) *
                ARROW_TRAVEL_DISTANCE));


        return new Point(x,y);
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }


    @Override
    public void update() {
        // TODO check if it is at destinationPoint
        if (getCenterLocation() == getDestinationPoint())
            //reduceLives();
            ;
    }

    private void setIntendedFlickDirection(int direction) {
        this.intendedFlickDirectionInDegrees = direction;
    }



    // TODO reset arrow to original point if user scrolls out of error region
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
                // TODO set flicked to true so that draw can show it move and dissapear
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

        // TODO maybe use this if someone slowly drags in direction
        @Override
        public boolean onScroll(MotionEvent event1, MotionEvent event2, float distanceX,
                                float distanceY) {
            //Log.d(DEBUG_TAG, "onScroll: " + event1.toString() + event2.toString());
            if (isCorrectFlick(event1.getX(), event1.getY(), event2.getX(), event2.getY())) {
                int y = (int) (event2.getX() * Math.tan(Math.toRadians(-intendedFlickDirectionInDegrees)));

                Log.d("arrow", "onScroll: x,y: " + event1.getX() +"  "+event2.getX()+"  "+y);

                setCenterLocation((int) event2.getX(),
                        y);
            }
            // TODO use angle instead of actual x y to go along line

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

        // TODO this is called if arrow double tapped then scrolled (called instead of scrolled)
        @Override
        public boolean onDoubleTap(MotionEvent event) {
            Log.d(DEBUG_TAG, "onDoubleTap: " + event.toString());
            //onScroll(event, event, 0,0);
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
