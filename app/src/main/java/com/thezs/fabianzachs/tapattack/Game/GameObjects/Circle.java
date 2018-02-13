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



        // any animation time is fine, itll just switch to the same img
        // TODO create all animations before game starts (for each color and shape)
    }


    @Override
    public void draw(Canvas canvas) {
        // test drawing shape to canvas
        scaleRect();
        canvas.drawBitmap(getCurrentShapeImg(), null, getBitmapHolder(),new Paint());
    }

    private void scaleRect() {
        Rect rect = getBitmapHolder();
        float widthHeightRatio = (float) (getCurrentShapeImg().getWidth())/getCurrentShapeImg().getHeight();
        if (rect.width() > rect.height())
            rect.left = rect.right - (int) (rect.height() * widthHeightRatio);
        else
            rect.top = rect.bottom - (int) (rect.width() * (1/widthHeightRatio));

        setBitmapHolder(rect);
    }

    @Override
    public void update() {

    }

    // ANIMATIONS
    private Animation idle;
    private Animation onTouch; // for onDown
    private Animation onDisappear; // fade out if not clicked




}
