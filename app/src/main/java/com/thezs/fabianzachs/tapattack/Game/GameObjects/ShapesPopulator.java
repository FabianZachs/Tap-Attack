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

/**
 * Created by fabianzachs on 25/02/18.
 */

public class ShapesPopulator {

    // settings
    private final int UNIT_TIME_PER_SHAPE_ADDITION = 2; // every x seconds one more max shape
    private final int SHAPE_SPACING = 5; // space between shapes

    private long initTime;
    private Rect newShapeArea; // instead of creating a new rect for every shape we want to create
    private Random rand;
    private ShapeBuilder shapeBuilder;

    public ShapesPopulator(long initTime) {
        this.initTime = initTime;
        this.newShapeArea = new Rect(300,300,300,300);
        rand = new Random(); // TODO make attribute?
        this.shapeBuilder = new ShapeBuilder(initTime);
    }

    public ArrayList<ShapeObject> update(ArrayList<ShapeObject> shapes) {

        if (maxNumberOfShapes() == shapes.size())
            return shapes;

        // TODO might return a null location (if all locations taken.. shouldnt happen???
        Point newShapeLocation = getValidNewShapeLocation(shapes);


        shapes.add(shapeBuilder.buildSquare("blue", newShapeLocation));

        return shapes;
    }


    // TODO find the right bounds for location for shape
    private Point getValidNewShapeLocation(ArrayList<ShapeObject> shapes) {

        int i = rand.nextInt(1000) + 100;
        int j = rand.nextInt(1000) + 200;

        /*while(locationUsedByAnotherShape(shapes,i,j)) {

            i = rand.nextInt(1000) + 100;
            j = rand.nextInt(1000) + 200;

        }*/
        return new Point(i,j);

    }

    // TODO add SHAPE_SPACING???
    private boolean locationUsedByAnotherShape(ArrayList<ShapeObject> shapes, int i, int j) {
        //Rect newShapeArea = new Rect(i - Constants.SCREEN_WIDTH - this.SHAPE_SPACING,j - Constants.SHAPE_HEIGHT - this.SHAPE_SPACING,i + Constants.SCREEN_WIDTH + this.SHAPE_SPACING, j + Constants.SHAPE_HEIGHT + this.SHAPE_SPACING);
        this.newShapeArea.left = i - Constants.SCREEN_WIDTH - this.SHAPE_SPACING;
        this.newShapeArea.top = j - Constants.SHAPE_HEIGHT - this.SHAPE_SPACING;
        this.newShapeArea.right = i + Constants.SCREEN_WIDTH + this.SHAPE_SPACING;
        this.newShapeArea.bottom = j + Constants.SHAPE_HEIGHT + this.SHAPE_SPACING;
        for (ShapeObject shape : shapes) {
            if (Rect.intersects(shape.getBitmapHolder(), this.newShapeArea))
                return true;
        }
        return false;
    }


    private int maxNumberOfShapes() {
        int number = (int) (getGameTime()/1000)/UNIT_TIME_PER_SHAPE_ADDITION + 1;
        return number;
    }


    public long getGameTime() {
        return System.currentTimeMillis() - initTime;
    }
}
