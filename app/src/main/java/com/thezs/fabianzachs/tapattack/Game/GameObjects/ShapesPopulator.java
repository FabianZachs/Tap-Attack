package com.thezs.fabianzachs.tapattack.Game.GameObjects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

import com.thezs.fabianzachs.tapattack.Animation.AnimationManager;
import com.thezs.fabianzachs.tapattack.Animation.Themes.ThemesManager;
import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.ShapeObject;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.Square;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.ProgressBar;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.Score;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.Streak;
import com.thezs.fabianzachs.tapattack.Game.Mediator.CentralGameCommunication;
import com.thezs.fabianzachs.tapattack.Game.SharedResources.SharedPaint;
import com.thezs.fabianzachs.tapattack.Game.SharedResources.SharedRect;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by fabianzachs on 25/02/18.
 */

public class ShapesPopulator {

    private CentralGameCommunication mediator;

    private SharedPaint sharedPaint;
    private SharedRect sharedRect;

    // settings
    private final int UNIT_TIME_PER_SHAPE_ADDITION = 1; // every x seconds one more max shape
    private final int MAX_NUMBER_LOOPS = 5;
    private final int SHAPE_SPACING = Constants.SCREEN_HEIGHT/15; // space between shapes
    //private final int SHAPE_SPACING = 4 * Constants.SCREEN_HEIGHT; // space between shapes
    private final int MAX_SHAPES = 5;


    private long timeOfLastShapeAddition;

    private long initTime;
    private Rect newShapeArea; // instead of creating a new rect for every shape we want to create
    private Random rand;
    private ShapeBuilder shapeBuilder;

    private String[] shapeColors;
    private Random colorFinder;
    private String direction;

    private ShapeColorPicker shapeColorPicker;

    public ShapesPopulator(CentralGameCommunication mediator, long initTime, SharedPaint sharedPaint, SharedRect sharedRect) {
        this.shapeColors = ThemesManager.getStrColors(Constants.CURRENT_THEME);
        this.mediator = mediator;
        this.sharedPaint = sharedPaint;
        this.sharedRect = sharedRect;
        this.timeOfLastShapeAddition = 0;
        this.initTime = initTime;
        // todo universal time
        this.newShapeArea = new Rect(300,300,300,300);
        rand = new Random(); // TODO make attribute?
        this.shapeBuilder = new ShapeBuilder(initTime);
        this.colorFinder = new Random();



        this.shapeColorPicker = new ShapeColorPicker(initTime);
        mediator.addObject(shapeColorPicker);
    }

    public CopyOnWriteArrayList update(CopyOnWriteArrayList<ShapeObject> shapes) {


        // TODO integrate the lastTimeShapeAdded with the progress bar to make sure ppl are able to get enough points
        // TODO possibly reduce time between added shapes when progress bar gets lower and lower. ex. if <10% have no timeout for adding shapes
        //if (maxNumberOfShapes() == shapes.size() || (lastTimeShapeAdded() < 100 && shapes.size() != 0))
        //return shapes;

        //this.shapeColorPicker.getProbabilityOfWarningColor();
        //Log.d("returningcolor", "update: # shapese" + shapes.size());

        if (shapes.size() >= 1 && shapes.get(0).getBitmapHolder().top - SHAPE_SPACING < 0) {
            Log.d("shapepopulatorissue", "A");
            return shapes;
        }

        Paint paint = sharedPaint.getUnUsedPaint();
        //Log.d("debug3", "update: paint:" + paint);
        //Log.d("debug3", "update: unusedpaintsize:" + sharedPaint.unUsedPaints.size());

        if (paint == null) {
            //Log.d("resourcesmissing", "update: paint none");
            Log.d("shapepopulatorissue", "B");
            //Log.d("debug3", "update: paint:null");
            return shapes;
        }

        Rect bitmapHolder = sharedRect.getUnUsedRect();
        //Log.d("debug3", "update: rect:" + bitmapHolder);
        if (bitmapHolder == null) {
            //Log.d("debug3", "update: rect:null");
            Log.d("shapepopulatorissue", "C");
            sharedPaint.freePaint(paint);
            //Log.d("resourcesmissing", "update: rect none");
            return shapes;
        }

        Point newShapeLocation = getValidNewShapeLocation(shapes);

        // no new location findable
        if (newShapeLocation == null) {
            Log.d("shapepopulatorissue", "D");
            return shapes;
        }


        // TODO use factory design pattern? so instead of .buildCross, pass "cross" in parameter
        //mShapes.add(shapeBuilder.buildArrow("blue", newShapeLocation));
        //ShapeObject newShape = shapeBuilder.buildShape("arrow", getColor(), newShapeLocation,paint,bitmapHolder,mediator, getDirection()) ;
        //ShapeObject newShape = shapeBuilder.buildShape("circle", getColor() , newShapeLocation,paint,bitmapHolder,mediator,"LEFT") ;
        //ShapeObject newShape = shapeBuilder.buildShape("cross", getColor() , newShapeLocation,paint,bitmapHolder,mediator,"LEFT") ;
        //ShapeObject newShape = shapeBuilder.buildShape("square", getColor(), newShapeLocation,paint,bitmapHolder,mediator,"LEFT") ;
        //ShapeObject newShape = shapeBuilder.buildShape("star", getColor(), newShapeLocation,paint,bitmapHolder,mediator,"LEFT") ;
        //newShape.attachAllObservers(scoreObserver,streakObserver,progressBarObserver);
        ShapeObject newShape = shapeBuilder.buildShape(getShape(), getColor(), newShapeLocation, paint, bitmapHolder, mediator, getDirection());
        shapes.add(0, newShape);
        timeOfLastShapeAddition = System.currentTimeMillis();

        Log.d("shapepopulatorissue", "E");
        return shapes; // TODO remember we need the whole list of shapes to know what next shapes we should add
    }

    private long lastTimeShapeAdded() {
        return System.currentTimeMillis() - timeOfLastShapeAddition;
    }


    // TODO find the right bounds for location for shape
    private Point getValidNewShapeLocation(CopyOnWriteArrayList shapes) {

        //int i = rand.nextInt(Constants.SHAPE_CREATION_AREA.right - Constants.SHAPE_WIDTH/2 - Constants.SHAPE_CREATION_AREA.left) + Constants.SHAPE_CREATION_AREA.left + Constants.SHAPE_WIDTH/2;
        int j = rand.nextInt(Constants.SHAPE_CREATION_AREA.bottom - Constants.SHAPE_CREATION_AREA.top) + Constants.SHAPE_CREATION_AREA.top;
        int i = rand.nextInt(Constants.SHAPE_CREATION_AREA.right - Constants.SHAPE_CREATION_AREA.left) + Constants.SHAPE_CREATION_AREA.left;
        int iterationNumber = 0;

        while(locationUsedByAnotherShape(shapes,i,j)) {

            //Log.d("location", "getValidNewShapeLocation: 0 to left" +(Constants.SHAPE_CREATION_AREA.right - Constants.SHAPE_WIDTH/2 - Constants.SHAPE_CREATION_AREA.left) );
            //Log.d("location", "getValidNewShapeLocation: shift" +(Constants.SHAPE_CREATION_AREA.left + Constants.SHAPE_WIDTH/2) );
            //Log.d("location", "getValidNewShapeLocation: left shape creation: " + Constants.SHAPE_CREATION_AREA.left);
            //Log.d("location", "getValidNewShapeLocation: right shape creation: " + Constants.SHAPE_CREATION_AREA.right);

           // i = rand.nextInt(Constants.SHAPE_CREATION_AREA.right - Constants.SHAPE_CREATION_AREA.left) + Constants.SHAPE_CREATION_AREA.left;

            //i = rand.nextInt(Constants.SHAPE_CREATION_AREA.right - Constants.SHAPE_WIDTH/2 - Constants.SHAPE_CREATION_AREA.left) + Constants.SHAPE_CREATION_AREA.left + Constants.SHAPE_WIDTH/2;
            //j = rand.nextInt(Constants.SHAPE_CREATION_AREA.bottom - Constants.SHAPE_CREATION_AREA.top) + Constants.SHAPE_CREATION_AREA.top;
            j = rand.nextInt(Constants.SHAPE_CREATION_AREA.bottom - Constants.SHAPE_CREATION_AREA.top) + Constants.SHAPE_CREATION_AREA.top;
            i = rand.nextInt(Constants.SHAPE_CREATION_AREA.right - Constants.SHAPE_CREATION_AREA.left) + Constants.SHAPE_CREATION_AREA.left;
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


    // 1/36 chance of getting warning color
    public String getColor() {
        //shapeColorPicker.getColorForShape();
        //String warningColor = mediator.getStrWarningColor();
        /*
        String selectedColor = shapeColors[colorFinder.nextInt(5)];
        if (selectedColor.equals(warningColor))
            selectedColor = shapeColors[colorFinder.nextInt(5)];
        Log.d("colorpicker", "getColor: " + selectedColor);
        return selectedColor;
        */
        String color = shapeColorPicker.getColorForShape();
        //Log.d("java.lang.null", "getColor: in getColor() " + color);
        return color;
    }

    private String getShape() {
        int i = rand.nextInt(850);
        //Log.d("intindex", "getShape: int" + i);


        if (i<25)
            return "cross";
        else if (i<50)
            return "star";
        else if (i<200)
            return "square";
        else if (i<500)
            return "arrow";
        else
            return "circle";


    }

    public String getDirection() {
        int i = rand.nextInt(2);

        switch (i) {
            case 0:
                return "LEFT";
            case 1:
                return "RIGHT";

            //case 2:
              //  return "UP";
            //case 3:
                //return "DOWN";
        }

        throw new RuntimeException("getDirection failed");
    }

    public ShapeObject getShape(String shape, String color, Point centerLocation, Paint paint, Rect bitmapHolder, CentralGameCommunication mediator ,String direction) {
        return shapeBuilder.buildShape(shape, color, centerLocation, paint, bitmapHolder, mediator, direction);

    }
}
