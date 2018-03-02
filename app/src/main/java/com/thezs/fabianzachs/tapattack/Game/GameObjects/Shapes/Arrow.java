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

    private double intendedFlickDirectionRadians;
    private String intendedFlickDirectionString;
    private final double FLICK_DIRECTION_ERROR_ALLOWANCE = Math.PI/4;
    private final Point originalPoint;
    private final Point destinationPoint;
    private final int ARROW_TRAVEL_DISTANCE = 30;
    private int lastUpdateXLocation;
    private int lastUpdateYLocation;


    // TODO BUGS: 1) is arrow too small when flicking? user scroll wont be registered to arrow if its outside it
    // TODO       2) fling needs to go to destination in grave object
    // TODO       3) angle 0 and pi dont work since its (+) to (-)
    // TODO       4) should shape return to origonalPoint if untouched?
    // TODO       5) make grave just simply take another x distance fading out in that diration instead of until final distination?


    public Arrow(float durationAlive, String color, Point centerLocation, Bitmap shapeImg, Bitmap shapeClickImg, String intendendedFlickDirection) {
        // call super(durationAlive, color) then in super also make the rect to hold bitmap
        super(durationAlive, color, centerLocation, shapeImg, shapeClickImg);
        setLives(1);
        setProgressBarAddition(15);

        this.intendedFlickDirectionString = intendendedFlickDirection;
        setIntendedFlickDirectionRadians(intendedFlickDirectionString);
        this.originalPoint = centerLocation;
        this.destinationPoint = getDestinationPoint();


        //lastUpdateXLocation = getCenterLocation().x;
        //lastUpdateYLocation = getCenterLocation().y;
        //Log.d("arrow", "Arrow: start: " + originalPoint);
        //Log.d("arrow", "Arrow: end: " + destinationPoint);



        // handling touch events
        setmDetector(new GestureDetectorCompat(Constants.CURRENT_CONTEXT, new MyGestureListener()));

    }

    // TODO use this to see if we can create a arrow at a specific location?? or just make arrow spawn further from edge
    public Point getDestinationPoint() {

        int x = 0;
        int y = 0;

        switch (intendedFlickDirectionString) {
            case "UP":
                x = originalPoint.x;
                y = originalPoint.y - ARROW_TRAVEL_DISTANCE;
                break;
            case "LEFT":
                x = originalPoint.x - ARROW_TRAVEL_DISTANCE;
                y = originalPoint.y;
                break;
            case "RIGHT":
                x = originalPoint.x + ARROW_TRAVEL_DISTANCE;
                y = originalPoint.y;
                break;
            case "DOWN":
                x = originalPoint.x;
                y = originalPoint.y + ARROW_TRAVEL_DISTANCE;
                break;
        }

        return new Point(x,y);
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }


    @Override
    public void update() {
        // TODO check if it is at destinationPoint
        Log.d("location", "current: " + getCenterLocation());
        Log.d("location", "target: " + destinationPoint);

        // TODO IF UP && CURRENTLOCATION Y < DESTINATION --- simpler CHANGE BELOW IMPLEMENTATION
        if (intendedFlickDirectionString.equals("UP") && getCenterLocation().y <= destinationPoint.y)
            reduceLives();
        else if (intendedFlickDirectionString.equals("DOWN") && getCenterLocation().y >= destinationPoint.y)
            reduceLives();
        else if (intendedFlickDirectionString.equals("LEFT") && getCenterLocation().x <= destinationPoint.x)
            reduceLives();
        else if (intendedFlickDirectionString.equals("RIGHT") && getCenterLocation().x >= destinationPoint.x)
            reduceLives();
        /*
        // UP
        if (lastUpdateYLocation > destinationPoint.y && destinationPoint.y >= getCenterLocation().y)
            reduceLives();
        // DOWN
        else if (lastUpdateYLocation < destinationPoint.y && destinationPoint.y <= getCenterLocation().y)
            reduceLives();
        // RIGHT
        else if (lastUpdateXLocation < destinationPoint.x && destinationPoint.x <= getCenterLocation().x)
            reduceLives();
        // LEFT
        else if (lastUpdateXLocation > destinationPoint.x && destinationPoint.x >= getCenterLocation().x)
            reduceLives();
        */
        //lastUpdateXLocation = getCenterLocation().x;
        //lastUpdateYLocation = getCenterLocation().y;

    }

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
            //Log.d(DEBUG_TAG,"onDown: " + event.toString());
            return true;
        }

        /*
        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            Log.d(DEBUG_TAG, "onFling: " + event1.toString() + event2.toString());

            if (isCorrectFlick(event1.getX(), event1.getY(), event2.getX(), event2.getY()))
                reduceLives(); // TODO arrow grave should move in flick direction
                // TODO set flicked to true so that draw can show it move and dissapear to final destination
            else
                ;
                // TODO implement decriment to progress bar (add wrong flick attribute to decriment progress bar)

            return true;

        }*/

        private boolean isCorrectFlick(float x1, float y1, float x2, float y2) {
            Double angle = Math.atan2(y1 - y2, x2 - x1);

            return (angle >= intendedFlickDirectionRadians - FLICK_DIRECTION_ERROR_ALLOWANCE &&
                    angle <= intendedFlickDirectionRadians + FLICK_DIRECTION_ERROR_ALLOWANCE);
        }


        // TODO maybe use this if someone slowly drags in direction
        @Override
        public boolean onScroll(MotionEvent event1, MotionEvent event2, float distanceX,
                                float distanceY) {
            //Log.d(DEBUG_TAG, "onScroll: " + event1.toString() + event2.toString());
            try {
                if (isCorrectFlick(event1.getX(), event1.getY(), event2.getX(), event2.getY())) {
                    int x = getCenterLocation().x;
                    int y = getCenterLocation().y;

                    switch (intendedFlickDirectionString) {
                        case "UP":
                        case "DOWN":
                            y = (int) event2.getY();
                            break;
                        case "LEFT":
                        case "RIGHT":
                            x = (int) event2.getX();
                            break;
                    }
                    setCenterLocation(x, y);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

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
