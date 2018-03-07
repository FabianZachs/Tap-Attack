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
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.ProgressBar;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.Score;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.Streak;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by fabianzachs on 25/02/18.
 */

public class ShapesPopulator {

    private Score scoreObserver;
    private Streak streakObserver;
    private ProgressBar progressBarObserver;

    // settings
    private final int UNIT_TIME_PER_SHAPE_ADDITION = 3; // every x seconds one more max shape
    private final int SHAPE_SPACING = 5; // space between shapes
    private final int MAX_SHAPES = 4;
    private final int MAX_NUMBER_LOOPS = 5;

    private long timeOfLastShapeAddition;

    private long initTime;
    private Rect newShapeArea; // instead of creating a new rect for every shape we want to create
    private Random rand;
    private ShapeBuilder shapeBuilder;

    private String[] shapeColors = {"blue","yellow","red","purple","green"};
    private Random colorFinder;

    public ShapesPopulator(long initTime) {
        this.timeOfLastShapeAddition = 0;
        this.initTime = initTime;
        this.newShapeArea = new Rect(300,300,300,300);
        rand = new Random(); // TODO make attribute?
        this.shapeBuilder = new ShapeBuilder(initTime);
        this.colorFinder = new Random();
    }

    public CopyOnWriteArrayList update(CopyOnWriteArrayList shapes) {


        // TODO integrate the lastTimeShapeAdded with the progress bar to make sure ppl are able to get enough points
        // TODO possibly reduce time between added shapes when progress bar gets lower and lower. ex. if <10% have no timeout for adding shapes
        if (maxNumberOfShapes() == shapes.size() || (lastTimeShapeAdded() < 200 && shapes.size() != 0))
            return shapes;

        Point newShapeLocation = getValidNewShapeLocation(shapes);

        // no new location findable
        if(newShapeLocation == null)
            return shapes;


        // TODO use factory design pattern? so instead of .buildCross, pass "cross" in parameter
        //mShapes.add(shapeBuilder.buildArrow("blue", newShapeLocation));
        ShapeObject newShape = shapeBuilder.buildShape("circle",getColor(), newShapeLocation,"LEFT") ;
        newShape.attachAllObservers(scoreObserver,streakObserver,progressBarObserver);
        shapes.add(newShape);
        timeOfLastShapeAddition = System.currentTimeMillis();

        return shapes; // TODO remember we need the whole list of shapes to know what next shapes we should add
    }

    private long lastTimeShapeAdded() {
        return System.currentTimeMillis() - timeOfLastShapeAddition;
    }


    // TODO find the right bounds for location for shape
    private Point getValidNewShapeLocation(CopyOnWriteArrayList shapes) {

        int i = rand.nextInt(Constants.SHAPE_CREATION_AREA.right - Constants.SHAPE_CREATION_AREA.left) + Constants.SHAPE_CREATION_AREA.left;
        int j = rand.nextInt(Constants.SHAPE_CREATION_AREA.bottom - Constants.SHAPE_CREATION_AREA.top) + Constants.SHAPE_CREATION_AREA.top;
        int iterationNumber = 0;

        while(locationUsedByAnotherShape(shapes,i,j)) {


            i = rand.nextInt(Constants.SHAPE_CREATION_AREA.right - Constants.SHAPE_CREATION_AREA.left) + Constants.SHAPE_CREATION_AREA.left;
            j = rand.nextInt(Constants.SHAPE_CREATION_AREA.bottom - Constants.SHAPE_CREATION_AREA.top) + Constants.SHAPE_CREATION_AREA.top;
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
        this.newShapeArea.left = i - (Constants.SHAPE_WIDTH/2) - this.SHAPE_SPACING;
        this.newShapeArea.top = j - (Constants.SHAPE_HEIGHT/2) - this.SHAPE_SPACING;
        this.newShapeArea.right = i + (Constants.SHAPE_WIDTH/2) + this.SHAPE_SPACING;
        this.newShapeArea.bottom = j + (Constants.SHAPE_HEIGHT/2) + this.SHAPE_SPACING;
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

    public void attachScoreObserver(Score scoreObserver) {
        this.scoreObserver= scoreObserver;
    }

    public void attachStreakObserver(Streak streakObserver) {
        this.streakObserver = streakObserver;
    }

    public void attachProgressBarObserver(ProgressBar progressBarObserver) {
        this.progressBarObserver = progressBarObserver;
    }

    public String getColor() {
        return shapeColors[colorFinder.nextInt(5)];
    }
}
