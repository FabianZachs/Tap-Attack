package com.thezs.fabianzachs.tapattack.Game.GameObjects;

import android.graphics.drawable.shapes.Shape;
import android.util.Log;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.ShapeObject;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by fabianzachs on 25/05/18.
 */

public class ShapeMover {

    private long startTime;
    private long initTime;
    private float RATE_OF_SPEED_INCREASE; // TODO use log(x+1) function

    public ShapeMover() {
        //Log.d("speed", "previous speed: " + Constants.SCREEN_HEIGHT/5000.0f);
        this.startTime = System.currentTimeMillis();
        this.initTime = System.currentTimeMillis();
    }

    public void updateShapeLocation(Shape shape) {}

    public void update(CopyOnWriteArrayList<ShapeObject> shapes) {
        int elapsedTime = (int) (System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();
        //float speed = Constants.SCREEN_HEIGHT/5000.0f;

        Log.d("speed", "now speed: " + getSpeed(elapsedTime));
        for (ShapeObject shape : shapes) {
            shape.incrementY((float) getSpeed(elapsedTime) * elapsedTime);

        }


    }

    public double getSpeed(int elapsedTime) {
        long currentGameTime = System.currentTimeMillis() - initTime;
        //Log.d("speed", "getSpeed: currenttime" + currentGameTime);
        //double denominator = (10000* (1/(Math.log(50000 * currentGameTime+ 100)))) + 5000;

        //double denominator = (3000000/((.021*currentGameTime)+300));
        //double denominator = (1000000/((.003*currentGameTime)+300));
        //double denominator = (900000/((.003*currentGameTime)+300)); // good start speed, but steeper slope
        double denominator = (900000/((.006*currentGameTime)+300));  // TODO check if good with function for producing warning colors
        return Constants.SCREEN_HEIGHT/ denominator;
    }
}
