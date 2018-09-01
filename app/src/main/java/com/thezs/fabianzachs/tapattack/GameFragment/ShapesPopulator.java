package com.thezs.fabianzachs.tapattack.GameFragment;

import android.graphics.Paint;
import android.graphics.Point;

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
    private Random random;

    public ShapesPopulator(CopyOnWriteArrayList<ShapeObject> shapes, RecycledRect recycledRect, RecycledPaint recycledPaint, ColorPicker colorPicker, ShapePicker shapePicker, int shapeRadius, int shapeSpacing) {
        this.shapeBuilder = new NormalShapeBuilder();
        this.recycledRect = recycledRect;
        this.recycledPaint = recycledPaint;
        this.colorPicker = colorPicker;
        this.shapePicker = shapePicker;
        this.shapeRadius = shapeRadius;
        this.shapeSpacing = shapeSpacing;
        this.random = new Random();

        this.yStepSize = 2*shapeRadius + shapeSpacing;

        // todo populateInitialShapes
        populateInitialShapes(shapes);


    }

    public CopyOnWriteArrayList<ShapeObject> update(CopyOnWriteArrayList<ShapeObject> shapes) {

        if (!readyToAddAnotherShape())
            return shapes;


        ShapeObject newShape = shapeBuilder.buildShape(shapePicker.getShape(), colorPicker.getColor(),
                getValidNewShapeLocation(), recycledPaint.getUnusedPaint(), recycledRect.getUnusedRect(), shapeRadius, getDirection());
        shapes.add(0,newShape);
        return shapes;


        // todo use listener to tell gamemode when another shape is created

    }

    /* TODO: PUT PUBLIC SETTERS FOR SHAPERADIUS SHAPEPICKER COLORPICKER to change during game
     TODO: actually better if we do this in the gamemode class, since this is a reference it will do the same

     */



    private void populateInitialShapes(CopyOnWriteArrayList<ShapeObject> shapes) {
        ArrayList<Integer> yLocations = getInitialYAxisShapeLocations();

        for (Integer y : yLocations) {

        }
    }

    private Point getValidNewShapeLocation() {

        return null;
    }

    private boolean readyToAddAnotherShape() {
        // todo use to manually ask if we should add another shape
        return true;
    }

    public void setListOfShapesToAdd() {
        // todo lists of shapes, colors, shaperadius, direction etc. if element i is null, use function
        // todo like shapePicker.getShape()
        // todo or add the lists to the shapePicker directly <-- better
    }

    private String getDirection() {
        // todo use random to get a
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
        y = y - yStepSize; // todo use this for furthestdownyCenterLocation for discrete mover

        while (y>0) {
            yLocations.add(y);
            y = y - yStepSize;
        }
        return yLocations;
    }

    public int getyStepSize() {
        return yStepSize;
    }

}
