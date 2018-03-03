package com.thezs.fabianzachs.tapattack.Game.GameObjects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

import com.thezs.fabianzachs.tapattack.Animation.AnimationManager;
import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.ShapeObject;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.Square;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by fabianzachs on 25/02/18.
 */

public class ShapesPopulator {

    // settings
    private final int UNIT_TIME_PER_SHAPE_ADDITION = 5; // every x seconds one more max shape
    private final int SHAPE_SPACING = 5; // space between shapes
    private final int MAX_SHAPES = 1;
    private final int MAX_NUMBER_LOOPS = 20;

    private long timeOfLastShapeAddition;

    private long initTime;
    private Rect newShapeArea; // instead of creating a new rect for every shape we want to create
    private Random rand;
    private ShapeBuilder shapeBuilder;

    public ShapesPopulator(long initTime) {
        this.timeOfLastShapeAddition = 0;
        this.initTime = initTime;
        this.newShapeArea = new Rect(300,300,300,300);
        rand = new Random(); // TODO make attribute?
        this.shapeBuilder = new ShapeBuilder(initTime);
    }

    public CopyOnWriteArrayList update(CopyOnWriteArrayList shapes) {

        CopyOnWriteArrayList<ShapeObject> mShapes = shapes;

        // TODO integrate the lastTimeShapeAdded with the progress bar to make sure ppl are able to get enough points
        // TODO possibly reduce time between added shapes when progress bar gets lower and lower. ex. if <10% have no timeout for adding shapes
        if (maxNumberOfShapes() == shapes.size() || (lastTimeShapeAdded() < 200 && shapes.size() != 0))
            return shapes;

        Point newShapeLocation = getValidNewShapeLocation(mShapes);

        // no new location findable
        if(newShapeLocation == null)
            return mShapes;


        // TODO use factory design pattern? so instead of .buildCross, pass "cross" in parameter
        //mShapes.add(shapeBuilder.buildArrow("blue", newShapeLocation));
        mShapes.add(shapeBuilder.buildShape("arrow","blue", newShapeLocation));
        timeOfLastShapeAddition = System.currentTimeMillis();

        return mShapes;
    }

    private long lastTimeShapeAdded() {
        return System.currentTimeMillis() - timeOfLastShapeAddition;
    }


    // TODO find the right bounds for location for shape
    private Point getValidNewShapeLocation(CopyOnWriteArrayList shapes) {

        // TODO incorporate with GAMEBOUNDARY
        int i = rand.nextInt(Constants.SCREEN_WIDTH);
        int j = rand.nextInt(Constants.SCREEN_HEIGHT - 300) + 300;
        int iterationNumber = 0;

        while(locationUsedByAnotherShape(shapes,i,j)) {


            i = rand.nextInt(Constants.SCREEN_WIDTH);
            j = rand.nextInt(Constants.SCREEN_HEIGHT -300) + 300;
            iterationNumber++;

            // break out of it if we cant find a location
            if (iterationNumber > MAX_NUMBER_LOOPS) {
                return null;
            }

        }

        return new Point(i,j);

    }

    private boolean locationUsedByAnotherShape(CopyOnWriteArrayList shapes, int i, int j) {
        //Rect newShapeArea = new Rect(i - Constants.SCREEN_WIDTH - this.SHAPE_SPACING,j - Constants.SHAPE_HEIGHT - this.SHAPE_SPACING,i + Constants.SCREEN_WIDTH + this.SHAPE_SPACING, j + Constants.SHAPE_HEIGHT + this.SHAPE_SPACING);
        this.newShapeArea.left = i - Constants.SCREEN_WIDTH - this.SHAPE_SPACING;
        this.newShapeArea.top = j - Constants.SHAPE_HEIGHT - this.SHAPE_SPACING;
        this.newShapeArea.right = i + Constants.SCREEN_WIDTH + this.SHAPE_SPACING;
        this.newShapeArea.bottom = j + Constants.SHAPE_HEIGHT + this.SHAPE_SPACING;
        for (Object shapeObj : shapes) {
            ShapeObject shape = (ShapeObject) shapeObj;
            if (Rect.intersects(shape.getBitmapHolder(), this.newShapeArea))
                return true;
        }
        return false;
    }


    private int maxNumberOfShapes() {
        int number = (int) (getGameTime()/1000)/UNIT_TIME_PER_SHAPE_ADDITION + 1;
        return number <= MAX_SHAPES ? number : MAX_SHAPES;
    }


    public long getGameTime() {
        return System.currentTimeMillis() - initTime;
    }
}
