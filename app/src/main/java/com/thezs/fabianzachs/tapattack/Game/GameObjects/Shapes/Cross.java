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
    public Cross(float durationAlive, String color, Integer colorInt, Point centerLocation, Bitmap shapeImg, Bitmap shapeClickImg, Paint paint, Rect bitmapHolder, CentralGameCommunication mediator) {
        super(durationAlive, color, colorInt, centerLocation, shapeImg, shapeClickImg, paint, bitmapHolder, mediator);
        setLives(1);
        //setProgressBarAddition(-100);
        // todo set back to gravable?? currently there is an error in grave
        setGraveAble(false);

        setmDetector(new GestureDetectorCompat(Constants.CURRENT_CONTEXT, new MyGestureListener()));
    }

    @Override
    public void playDeathSoundEffect() {
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }


    @Override
    public void update() {
    }


    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent event) {
            reduceLives();
            return true;
        }

    }
}
