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
 * Created by fabianzachs on 24/02/18.
 */

public class Square extends ShapeObject {

    private long timeSetState;
    private int TIME_FOR_SECOND_CLICK = 500; // used to be 350 for doubletap

    public Square(float durationAlive, String color, Point centerLocation, Bitmap shapeImg, Bitmap shapeClickImg, Paint paint, Rect bitmapHolder, CentralGameCommunication mediator) {
        super(durationAlive, color, centerLocation, shapeImg, shapeClickImg, paint, bitmapHolder, mediator);
        setLives(2);
        setProgressBarAddition(15);
        setGraveAble(false);

        //timeSetState = 0;

        setmDetector(new GestureDetectorCompat(Constants.CURRENT_CONTEXT, new MyGestureListener()));

    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }


    @Override
    public void update() {
        if (System.currentTimeMillis() - timeSetState > TIME_FOR_SECOND_CLICK/*ViewConfiguration.getTapTimeout()*/ && getState() != 0) {
            increaseLives();
            setState(0);
        }
    }

    private void increaseLives() {
        setLives(getLives()+1);
    }


    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent event) {
            setState(1);
            timeSetState = System.currentTimeMillis();
            reduceLives();
            setDurationAlive(getDurationAlive() + 0.25f); // todo wont matter for waterfall type mode
            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            //mediator.changeProgressBarBy(PROGRESSBAR_REDUCTION_WITH_INCORRECT_TOUCH);
            mediator.resetStreak();
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent event) {
            return true;
        }
    }

}
