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
    private long initTime;
    private String color;  // will be used to find correct bitmap img
    private int points;
    private int lives;

    // TODO
    // for arrow shape make at least a little direction movement neccessary (small flick)  , so that
    // a simple press (with fractional vertical/ horizontal movement doesnt trigger fail/sucess


    public abstract void draw(Canvas canvas);
    public abstract void update();

    public ShapeObject(float durationAlive, String color) {
        this.durationAlive = durationAlive;
        this.color = color;
        this.points = 1; // all shapes are worth one point?
        this.initTime = System.currentTimeMillis();
        // code to make rectangle for holding the shape, then have subclasses call super for this?
        //this.bitmapHolder =
    }


    private boolean isAlive() {
        // if time alive > durationAlive, object should be removed
        // AND if it still has lives
        return !(System.currentTimeMillis() - initTime > durationAlive) && lives > 0;
    }









}
