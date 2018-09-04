package com.thezs.fabianzachs.tapattack.GameFragment.ShapeMovers;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.GameFragment.Mediator;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes.ShapeObject;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class DiscreteShapeMover implements ShapeMover {

    //private  ArrayList<Integer> yAxisShapeLocations;
    private int yStepSize;
    private int furtherDownCenterLocation;

    public DiscreteShapeMover(Mediator mediator, int yStepSize, int furtherDownCenterLocation/*, int shapeRadius, int shapeSpacing*/) {
        mediator.addObject(this);
        this.yStepSize = yStepSize;
        this.furtherDownCenterLocation = furtherDownCenterLocation;
        // todo use shapepopulator.getYstepSize and furthestDownCenterLocation
        //setyStepSize(shapeRadius, shapeSpacing);
        //setyAxisShapeLocations(/*shapeRadius, shapeSpacing*/);

    }

    /*
    public ArrayList<Integer> getyAxisShapeLocations() {
        return yAxisShapeLocations;
    }
    */

    @Override
    public void update(CopyOnWriteArrayList<ShapeObject> shapes) {

        if (shapes.size()!=0 && missingShapeInFurthestDownSlot(shapes)) {
            moveAllShapesDownAStep(shapes);
        }
    }

    @Override
    public void resetTimeAtLastUpdate() {
    }

    /*
    private void setyStepSize(int shapeRadius, int shapeSpacing) {
        yStepSize = 2*shapeRadius + shapeSpacing;
    }
    */


    private boolean missingShapeInFurthestDownSlot(CopyOnWriteArrayList<ShapeObject> shapes) {
       //return  getFurthestDownShape(shapes).getBitmapHolder().bottom < yAxisShapeLocations.get(0);
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
