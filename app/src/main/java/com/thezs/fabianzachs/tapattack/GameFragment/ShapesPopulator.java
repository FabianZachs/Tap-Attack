package com.thezs.fabianzachs.tapattack.GameFragment;

import android.graphics.Paint;
import android.graphics.Point;

import com.thezs.fabianzachs.tapattack.GameFragment.RecycledResources.RecycledPaint;
import com.thezs.fabianzachs.tapattack.GameFragment.RecycledResources.RecycledRect;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes.NormalShapeBuilder;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes.ShapeObject;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class ShapesPopulator {

    private NormalShapeBuilder shapeBuilder;
    private RecycledPaint recycledPaint;
    private RecycledRect recycledRect;
    private ColorPicker colorPicker;
    private ShapePicker shapePicker;
    private int shapeRadius;
    private Random random;

    public ShapesPopulator(RecycledRect recycledRect, RecycledPaint recycledPaint, ColorPicker colorPicker, ShapePicker shapePicker, int shapeRadius) {
        this.shapeBuilder = new NormalShapeBuilder();
        this.recycledRect = recycledRect;
        this.recycledPaint = recycledPaint;
        this.colorPicker = colorPicker;
        this.shapePicker = shapePicker;
        this.shapeRadius = shapeRadius;
        this.random = new Random();

        // todo populateInitialShapes


    }

    public CopyOnWriteArrayList<ShapeObject> update(CopyOnWriteArrayList<ShapeObject> shapes) {

        if (!readyToAddAnotherShape())
            return shapes;


        ShapeObject newShape = shapeBuilder.buildShape(shapePicker.getShape(), colorPicker.getColor(),
                getValidNewShapeLocation(), recycledPaint.getUnusedPaint(), recycledRect.getUnusedRect(), shapeRadius, getDirection());
        shapes.add(0,newShape);
        return shapes;

    }

    /* TODO: PUT PUBLIC SETTERS FOR SHAPERADIUS SHAPEPICKER COLORPICKER to change during game
     TODO: actually better if we do this in the gamemode class, since this is a reference it will do the same

     */



    private void populateInitialShapes() {
        //todo use similar to discete shape mover idea
    }

    private Point getValidNewShapeLocation() {

    }

    public boolean readyToAddAnotherShape() {
        // todo use to manually ask if we should add another shape
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

}
