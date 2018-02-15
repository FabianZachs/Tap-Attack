package com.thezs.fabianzachs.tapattack.Game.GraveObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by fabianzachs on 15/02/18.
 */

public class Grave {

    private float duration = 0.1f;
    private long initTime;
    private Point centerLocation;
    private Rect bitmapHolder;
    private Bitmap graveImg;
    private Paint paint;

    private float shapeScaling;

    public Grave(Point centerLocation, Rect bitmapHolder, Bitmap graveImg) {
        this.initTime = System.currentTimeMillis();
        this.centerLocation = centerLocation;
        this.bitmapHolder = bitmapHolder;
        this.graveImg = graveImg;

        paint = new Paint();
    }

    public boolean graveDestroyed() {
        // grave destroyed if time elapsed is greater then how long grave should be alive
        return (System.currentTimeMillis() - initTime > duration * 1000);
    }


    public void draw(Canvas canvas) {

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

}
