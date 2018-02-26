package com.thezs.fabianzachs.tapattack.Game.GameObjects;

import android.graphics.Point;
import android.util.Log;

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

        int  i = rand.nextInt(500) + 50;
        int  j = rand.nextInt(500) + 50;

        // TODO PROBLEM LIES WITH THE setmDetector in each of the shapes
        // "Can't create handler inside thread that has not called Looper.prepare()"
        Square square = shapeBuilder.buildSquare("blue", new Point(i,j));
        //shapes.add(shapeBuilder.buildSquare("blue", new Point(i,j)));
        return shapes;
    }

    private int maxNumberOfShapes() {
        int number = (int) (getGameTime()/1000)/UNIT_TIME_PER_SHAPE_ADDITION;
        return number;
    }


    public long getGameTime() {
        return System.currentTimeMillis() - initTime;
    }
}
