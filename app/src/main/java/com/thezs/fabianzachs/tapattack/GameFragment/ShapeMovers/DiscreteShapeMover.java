package com.thezs.fabianzachs.tapattack.GameFragment.ShapeMovers;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.GameFragment.Mediator;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes.ShapeObject;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class DiscreteShapeMover implements ShapeMover {

    private int yStepSize;
    private int furtherDownCenterLocation;

    public DiscreteShapeMover(Mediator mediator, int yStepSize) {
        mediator.addObject(this);
        this.yStepSize = yStepSize;
        this.furtherDownCenterLocation = Constants.GAME_VIEW_HEIGHT - yStepSize;
    }

    @Override
    public void update(CopyOnWriteArrayList<ShapeObject> shapes) {

        if (shapes.size()!=0 && missingShapeInFurthestDownSlot(shapes)) {
            moveAllShapesDownAStep(shapes);
        }
    }

    @Override
    public void resetTimeAtLastUpdate() {
    }


    private boolean missingShapeInFurthestDownSlot(CopyOnWriteArrayList<ShapeObject> shapes) {
        return  getFurthestDownShape(shapes).getBitmapHolder().bottom < furtherDownCenterLocation;
    }

    private ShapeObject getFurthestDownShape(CopyOnWriteArrayList<ShapeObject> shapes) {
        return shapes.get(shapes.size()-1);
    }

    private void moveAllShapesDownAStep(CopyOnWriteArrayList<ShapeObject> shapes) {
        for (ShapeObject shape : shapes) {
            shape.incrementY(yStepSize);
        }
    }
}
