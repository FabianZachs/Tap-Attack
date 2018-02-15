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
import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.R;

/**
 * Created by fabianzachs on 07/02/18.
 */

public class Circle extends ShapeObject {


    public Circle(float durationAlive, String color, Point centerLocation, Bitmap shapeClickImg, Bitmap shapeImg) {
       // call super(durationAlive, color) then in super also make the rect to hold bitmap
        super(durationAlive, color, centerLocation, shapeImg, shapeClickImg);
        setLives(1);
        setProgressBarAddition(10);
    }


    @Override
    public void draw(Canvas canvas) {

        Paint alphaPaint = new Paint();
        alphaPaint.setAlpha(255);

        // ERROR if time is below 150 - shape flashes
        if (150 < timeLeft() && timeLeft() < 1000 ) {
            alphaPaint.setAlpha( (int) ((255 * timeLeft())/1000));
        }
        else if (timeLeft() < 150)
            alphaPaint.setAlpha(0);

        canvas.drawBitmap(getCurrentShapeImg(), null, getBitmapHolder(),alphaPaint);

    }
    public long timeLeft() {
        return  (int) getDurationAlive() * 1000 - (System.currentTimeMillis() - getInitTime());
    }


    @Override
    public void update() {
    }

}
