package com.thezs.fabianzachs.tapattack.Game.GameObjects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by fabianzachs on 07/02/18.
 */

public class Circle extends ShapeObject {

    //private Animation animation;  make the animation

    public Circle(float durationAlive, String color) {
       // call super(durationAlive, color) then in super also make the rect to hold bitmap
        super(durationAlive, color);
        setLives(1);
        setProgressBarAddition(10);
        setBitmapHolder(new Rect(100,100,400,400));
        // make the animation

    }



    @Override
    public void recieveTouch(MotionEvent event) {
        super.recieveTouch(event);
    }

    @Override
    public void draw(Canvas canvas) {
        // test drawing shape to canvas
        Paint paint = new Paint();
        paint.setColor(Color.RED);

        canvas.drawRect(getBitmapHolder(), paint);
    }

    @Override
    public void update() {

    }
}
