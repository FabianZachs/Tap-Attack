package com.thezs.fabianzachs.tapattack.Game.GameObjects;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.util.Log;

import com.thezs.fabianzachs.tapattack.Animation.AnimationManager;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.ShapeObject;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.Square;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by fabianzachs on 25/02/18.
 */

public class ShapesPopulator {

    // settings
    private final int UNIT_TIME_PER_SHAPE_ADDITION = 5; // every x seconds one more max shape

    private long initTime;
    private ShapeBuilder shapeBuilder;

    public ShapesPopulator(long initTime) {
        this.initTime = initTime;
        this.shapeBuilder = new ShapeBuilder(initTime);
    }

    public ArrayList<ShapeObject> update(ArrayList<ShapeObject> shapes) {

        if (maxNumberOfShapes() == shapes.size())
            return shapes;

        Random rand = new Random();

        int  i = rand.nextInt(1000) + 100;
        int  j = rand.nextInt(1000) + 100;


        shapes.add(shapeBuilder.buildSquare("blue", new Point(i,j)));
        return shapes;
    }

    private int maxNumberOfShapes() {
        int number = (int) (getGameTime()/1000)/UNIT_TIME_PER_SHAPE_ADDITION + 1;
        return number;
    }


    public long getGameTime() {
        return System.currentTimeMillis() - initTime;
    }
}
