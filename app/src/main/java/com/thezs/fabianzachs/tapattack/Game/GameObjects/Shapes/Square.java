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
 * Created by fabianzachs on 24/02/18.
 */

public class Square extends ShapeObject {

    private long timeSetState;

    public Square(float durationAlive, String color, Point centerLocation, Bitmap shapeImg, Bitmap shapeClickImg, Paint paint, Rect bitmapHolder, CentralGameCommunication mediator) {
        super(durationAlive, color, centerLocation, shapeImg, shapeClickImg, paint, bitmapHolder, mediator);
        setLives(1);
        setProgressBarAddition(15);
        setGraveAble(false);

        timeSetState = 0;

        setmDetector(new GestureDetectorCompat(Constants.CURRENT_CONTEXT, new MyGestureListener()));

    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }


    @Override
    public void update() {
        if (System.currentTimeMillis() - timeSetState > 350/*ViewConfiguration.getTapTimeout()*/)
            setState(0);
    }


    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent event) {
            setState(1);
            timeSetState = System.currentTimeMillis();
            setDurationAlive(getDurationAlive() + 0.25f);
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
            reduceLives();
            return true;
        }
    }

}
