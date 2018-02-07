package com.thezs.fabianzachs.tapattack.Game.GameObjects;

import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by fabianzachs on 07/02/18.
 */

public abstract class ShapeObject {

    private float durationAlive;
    private Rect bitmapHolder;  // rectangle to hold bitmap ?? needed??
    private int points;
    private long initTime;
    private String color;  // will be used to find correct bitmap img


    public abstract void draw(Canvas canvas);
    public abstract void update();

    private boolean isAlive() {
        // if time alive > durationAlive, object should be removed
        return !(System.currentTimeMillis() - initTime > durationAlive);
    }









}
