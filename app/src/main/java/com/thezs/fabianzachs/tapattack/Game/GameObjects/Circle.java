package com.thezs.fabianzachs.tapattack.Game.GameObjects;

import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by fabianzachs on 07/02/18.
 */

public class Circle extends ShapeObject {

    private float durationAlive;
    //private Animation animation;  make the animation
    private Rect bitmapHolder;
    private long initTime;
    private String color;
    private int points;
    private int lives;


    public Circle(float durationAlive, String color) {
       // call super(durationAlive, color) then in super also make the rect to hold bitmap
        super(durationAlive, color);
        lives = 1;
        // make the animation

    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void update() {

    }
}
