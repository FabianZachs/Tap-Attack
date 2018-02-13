package com.thezs.fabianzachs.tapattack.Game.GameObjects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.thezs.fabianzachs.tapattack.Animation.Animation;
import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.R;

/**
 * Created by fabianzachs on 07/02/18.
 */

public class Circle extends ShapeObject {

    //private Animation animation;  make the animation

    public Circle(float durationAlive, String color, Point centerLocation, Bitmap shapeClickImg, Bitmap shapeImg) {
       // call super(durationAlive, color) then in super also make the rect to hold bitmap
        super(durationAlive, color, centerLocation, shapeImg, shapeClickImg);
        setLives(1);
        setProgressBarAddition(10);
    }


    @Override
    public void draw(Canvas canvas) {

        Paint alphaPaint = new Paint();
        // if lifespan of shape - time alive = time left < 1
        alphaPaint.setAlpha(255);

        if (timeLeft() < 1000 ) {
            //alphaPaint.setAlpha( (int) ((255/1000) * getDurationAlive()*1000 - (System.currentTimeMillis() - getInitTime())));
            alphaPaint.setAlpha( (int) ((255/1000) * timeLeft()));

            StyleableToast.makeText(Constants.CURRENT_CONTEXT, "NOw", R.style.successtoast).show();

        }

        canvas.drawBitmap(getCurrentShapeImg(), null, getBitmapHolder(),alphaPaint);
        //canvas.drawBitmap(getCurrentShapeImg(), null, getBitmapHolder(),new Paint());
    }
    public long timeLeft() {
        return  (int) getDurationAlive() * 1000 - (System.currentTimeMillis() - getInitTime());
    }


    @Override
    public void update() {

    }

}
