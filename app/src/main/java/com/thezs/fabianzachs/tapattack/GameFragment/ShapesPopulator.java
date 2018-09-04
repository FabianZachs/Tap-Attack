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

    private NormalShapeBuilder shapeBuilder;
    private RecycledPaint recycledPaint;
    private RecycledRect recycledRect;
    private ColorPicker colorPicker;
    private ShapePicker shapePicker;
    private int shapeRadius;
    private int shapeSpacing;
    private int yStepSize;
    private int yLevelOfShapeCreation;
    private int yLevelToTriggerShapeCreation;
    private Random random;

    public ShapesPopulator(CopyOnWriteArrayList<ShapeObject> shapes, RecycledRect recycledRect,
                           RecycledPaint recycledPaint, ColorPicker colorPicker, ShapePicker shapePicker,
                           int shapeRadius, int shapeSpacing) {
        this.shapeBuilder = new NormalShapeBuilder();
        this.recycledRect = recycledRect;
        this.recycledPaint = recycledPaint;
        this.colorPicker = colorPicker;
        this.shapePicker = shapePicker;
        this.shapeRadius = shapeRadius;
        this.shapeSpacing = shapeSpacing;
        this.random = new Random();

        this.yStepSize = 2*shapeRadius + shapeSpacing;

        populateInitialShapes(shapes);


    }

    public void update(CopyOnWriteArrayList<ShapeObject> shapes) {

        if (!readyToAddAnotherShape(shapes))
            return;


        ShapeObject newShape = shapeBuilder.buildShape(shapePicker.getShape(), colorPicker.getColor(),
                getValidNewShapeLocation(), recycledPaint.getUnusedPaint(), recycledRect.getUnusedRect(), shapeRadius, getDirection());
        shapes.add(0,newShape);
    }

    private void populateInitialShapes(CopyOnWriteArrayList<ShapeObject> shapes) {
        ArrayList<Integer> yLocations = getInitialYAxisShapeLocations();

        for (Integer y : yLocations) {
            ShapeObject newShape = shapeBuilder.buildShape(shapePicker.getShape(), colorPicker.getColor(),
                    new Point(getValidNewShapeLocation().x, y), recycledPaint.getUnusedPaint(), recycledRect.getUnusedRect(), shapeRadius, getDirection());
            shapes.add(0,newShape);
        }
    }

    private Point getValidNewShapeLocation() {
        int x = random.nextInt(Constants.SHAPE_CREATION_AREA.right - Constants.SHAPE_CREATION_AREA.left) + Constants.SHAPE_CREATION_AREA.left;

        return new Point(x,yLevelOfShapeCreation);
    }

    private boolean readyToAddAnotherShape(CopyOnWriteArrayList<ShapeObject> shapes) {
        return (shapes.size() == 0 ) || (shapes.get(0).getCenterLocation().y>=yLevelToTriggerShapeCreation);
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

    private ArrayList<Integer> getInitialYAxisShapeLocations() {
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
