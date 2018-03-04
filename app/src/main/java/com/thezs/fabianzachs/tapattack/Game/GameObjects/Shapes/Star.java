package com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.Score;

/**
 * Created by fabianzachs on 04/03/18.
 */

public class Star extends ShapeObject {

    private Score scoreObserver;
    private final int ROTATION_ANGLE = 90;
    private final float TIME_PER_ROATION = 0.5f;
    private Matrix rotationMatrix;
    private long timeOfLastRotation;

    public Star(float durationAlive, String color, Point centerLocation, Bitmap shapeImg, Bitmap shapeClickImg) {
        super(durationAlive, color, centerLocation, shapeImg, shapeClickImg);
        setLives(1);
        setProgressBarAddition(0);
        setGraveAble(false);

        rotationMatrix = new Matrix();
        rotationMatrix.postRotate(ROTATION_ANGLE);
        timeOfLastRotation = System.currentTimeMillis();

        // handling touch events
        setmDetector(new GestureDetectorCompat(Constants.CURRENT_CONTEXT, new MyGestureListener()));

    }


    // TODO in the update we want the star to rotate every x milliseconds
    @Override
    public void update() {

        if (System.currentTimeMillis() - timeOfLastRotation> TIME_PER_ROATION * 1000) {
            //rotationMatrix.postRotate(ROTATION_ANGLE);
            timeOfLastRotation = System.currentTimeMillis();
            setShapeImages(0, Bitmap.createBitmap(getShapeImg(), 0, 0, getShapeImg().getWidth(), getShapeImg().getHeight(), rotationMatrix, true));
            // TODO rotate bitmap holder as well
            //setBitmapHolder(getBitmapHolder().);
        }
/*
        Matrix m = new Matrix();
// point is the point about which to rotate.
        m.setRotate(degrees, point.x, point.y);
        m.mapRect(r);*/

/*
        Matrix matrix = new Matrix();
        matrix.setRotate(ROTATION_ANGLE, getBitmapHolder().centerX(), getBitmapHolder().centerY());
        mcanvas.drawBitmap(yourBitmap, matrix, null);
*/
        //canvas.drawBitmap(getCurrentShapeImg(), null, getBitmapHolder(),getAlphaPaint());
/*
        mcanvas.save(Canvas.MATRIX_SAVE_FLAG); //Saving the canvas and later restoring it so only this image will be rotated.
        2
        mcanvas.rotate(-angle);
        3
        mcanvas.drawBitmap(yourBitmap, left, top, null);
        4
        mcanvas.restore();
        */


    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        /*
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.rotate(-ROTATION_ANGLE);
        canvas.drawBitmap(getCurrentShapeImg(),null, getBitmapHolder(),getAlphaPaint());
        canvas.restore();
        */
        /*
        Matrix matrix = new Matrix();
        matrix.setRotate(ROTATION_ANGLE, getBitmapHolder().centerX(), getBitmapHolder().centerY());
        canvas.drawBitmap(getCurrentShapeImg(), , null);
        */

    }




    // listens for specific touch events
    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";


        @Override
        public boolean onDown(MotionEvent event) {
            //Log.d(DEBUG_TAG,"onDown: " + event.toString());
            //setState(1); // TODO only briefly
            getScoreObserver().incScore(getPoints());
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
