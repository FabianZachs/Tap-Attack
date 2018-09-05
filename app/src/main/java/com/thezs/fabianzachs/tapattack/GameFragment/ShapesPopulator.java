package com.thezs.fabianzachs.tapattack.GameFragment;

import android.graphics.Point;
import android.util.Log;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.GameFragment.RecycledResources.RecycledPaint;
import com.thezs.fabianzachs.tapattack.GameFragment.RecycledResources.RecycledRect;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes.NormalShapeBuilder;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes.ShapeObject;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class ShapesPopulator {

    private final int TOTAL_NUMBER_OF_SHAPES;
    private int currentNumberOfCreatedShapes=0;
    private NormalShapeBuilder shapeBuilder;
    private RecycledPaint recycledPaint;
    private RecycledRect recycledRect;
    private ColorPicker colorPicker;
    private ShapePicker shapePicker;
    private int shapeRadius;
    private int yLevelOfShapeCreation;
    private int yLevelToTriggerShapeCreation;
    private Random random;

    public ShapesPopulator(Mediator mediator, CopyOnWriteArrayList<ShapeObject> shapes, RecycledRect recycledRect,
                           RecycledPaint recycledPaint, ColorPicker colorPicker, ShapePicker shapePicker,
                           int shapeRadius, int shapeSpacing, int TOTAL_NUMBER_OF_SHAPES) {
        this.TOTAL_NUMBER_OF_SHAPES = TOTAL_NUMBER_OF_SHAPES;
        this.shapeBuilder = new NormalShapeBuilder(mediator);
        this.recycledRect = recycledRect;
        this.recycledPaint = recycledPaint;
        this.colorPicker = colorPicker;
        this.shapePicker = shapePicker;
        this.shapeRadius = shapeRadius;
        this.random = new Random();

        int yStepSize = 2*shapeRadius + shapeSpacing;

        populateInitialShapes(shapes, yStepSize);
    }

    public int getCurrentNumberOfCreatedShapes() {
        return currentNumberOfCreatedShapes;
    }

    public void update(CopyOnWriteArrayList<ShapeObject> shapes) {

        if (!readyToAddAnotherShape(shapes))
            return;


        ShapeObject newShape = shapeBuilder.buildShape(shapePicker.getShape(), colorPicker.getColor(),
                getValidNewShapeLocation(), recycledPaint.getUnusedPaint(), recycledRect.getUnusedRect(), shapeRadius, getDirection());
        shapes.add(0,newShape);
        currentNumberOfCreatedShapes++;
    }

    private void populateInitialShapes(CopyOnWriteArrayList<ShapeObject> shapes, int yStepSize) {
        ArrayList<Integer> yLocations = getInitialYAxisShapeLocations(yStepSize);

        for (Integer y : yLocations) {
            ShapeObject newShape = shapeBuilder.buildShape(shapePicker.getShape(), colorPicker.getColor(),
                    new Point(getValidNewShapeLocation().x, y), recycledPaint.getUnusedPaint(), recycledRect.getUnusedRect(), shapeRadius, getDirection());
            shapes.add(0,newShape);
            currentNumberOfCreatedShapes++;
        }
    }

    private Point getValidNewShapeLocation() {
        int x = random.nextInt(Constants.SHAPE_CREATION_AREA.right - Constants.SHAPE_CREATION_AREA.left) + Constants.SHAPE_CREATION_AREA.left;

        return new Point(x,yLevelOfShapeCreation);
    }

    private boolean readyToAddAnotherShape(CopyOnWriteArrayList<ShapeObject> shapes) {
        return (currentNumberOfCreatedShapes < TOTAL_NUMBER_OF_SHAPES) &&
                ((shapes.size() == 0 ) || (shapes.get(0).getCenterLocation().y>=yLevelToTriggerShapeCreation));
    }

    private String getDirection() {
        int i = random.nextInt(2);

        switch (i) {
            case 0:
                return "left";
            case 1:
                return "right";
        }

        throw new RuntimeException("getDirection failed");
    }

    private ArrayList<Integer> getInitialYAxisShapeLocations(int yStepSize) {
        ArrayList<Integer> yLocations = new ArrayList<>();
        Integer y = Constants.GAME_VIEW_HEIGHT;
        y = y - yStepSize;

        while (y>-(yStepSize)) {
            yLocations.add(y);
            y = y - yStepSize;
        }
        yLevelToTriggerShapeCreation = y+yStepSize;
        yLevelOfShapeCreation = y;
        return yLocations;
    }

}
