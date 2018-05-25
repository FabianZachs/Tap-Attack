package com.thezs.fabianzachs.tapattack.Game.GameObjects;

import android.graphics.drawable.shapes.Shape;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.ShapeObject;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by fabianzachs on 25/05/18.
 */

public class ShapeMover {

    private long startTime;
    private float RATE_OF_SPEED_INCREASE;

    public ShapeMover() {
        startTime = System.currentTimeMillis();
    }

    public void updateShapeLocation(Shape shape) {}

    public void update(CopyOnWriteArrayList<ShapeObject> shapes) {
        int elapsedTime = (int) (System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();
        float speed = Constants.SCREEN_HEIGHT/10000.0f;

        for (ShapeObject shape : shapes) {
            shape.incrementY(speed * elapsedTime);

        }


    }
}
