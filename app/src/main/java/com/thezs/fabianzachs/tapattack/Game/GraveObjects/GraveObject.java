package com.thezs.fabianzachs.tapattack.Game.GraveObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.ShapeObject;

/**
 * Created by fabianzachs on 15/02/18.
 */

public abstract class GraveObject {

    private float DURATION = 0.05f;
    private long initTime;
    private Point centerLocation;
    private Rect bitmapHolder;
    private Bitmap graveImg;
    private Paint paint;

    private float shapeScaling;


    public GraveObject(ShapeObject shapeToCreateGraveFrom) {
        this.initTime = System.currentTimeMillis();
        this.centerLocation = shapeToCreateGraveFrom.getCenterLocation();
        this.bitmapHolder = shapeToCreateGraveFrom.getBitmapHolder();
        this.graveImg = shapeToCreateGraveFrom.getShapeClickImg();

        this.paint = new Paint();
    }

    public boolean graveDestroyed() {
        // grave destroyed if time elapsed is greater then how long grave should be alive
        return (System.currentTimeMillis() - initTime > DURATION * 1000);
    }


    public void draw(Canvas canvas) {

        // TODO maybe use factory design pattern for creating the right kind of grave: below is blinking for cross
        /*
        if(System.currentTimeMillis() - blinkStart >= 150 && blink) {
    blink = false;
    lastUpdateTime = System.currentTimeMillis();
        }
         */

        // TODO use opasityPaint to fade out
        // if lifespan of shape - time alive = time left < 1
        //alphaPaint.setAlpha(255);

        /*if (timeLeft() < 1000 ) {
            //alphaPaint.setAlpha( (int) ((255/1000) * getDurationAlive()*1000 - (System.currentTimeMillis() - getInitTime())));
            alphaPaint.setAlpha( (int) ((255/1000) * timeLeft()));

            StyleableToast.makeText(Constants.CURRENT_CONTEXT, "NOw", R.style.successtoast).show();

        }*/

        canvas.drawBitmap(graveImg, null, this.bitmapHolder, this.paint);

    }


    public Paint getAlphaPaint() {
        Paint alphaPaint = new Paint();
        alphaPaint.setAlpha(255);

        // ERROR if time is below 150 - shape flashes
        if (150 < getTimeLeft() && getTimeLeft() < 1000) {
            alphaPaint.setAlpha((int) ((255 * getTimeLeft()) / 1000));
        } else if (getTimeLeft() < 150)
            alphaPaint.setAlpha(0);

        return alphaPaint;
    }

    public long getTimeLeft() {
        return  (int) DURATION * 1000 - (System.currentTimeMillis() - initTime);
    }

    public Bitmap getGraveImg() {
        return this.graveImg;
    }

    public Rect getBitmapHolder() {
        return this.bitmapHolder;
    }

    public Paint getPaint() {
        return this.paint;
    }

    public void setDURATION(float duration) {
        this.DURATION = duration;
    }
}
