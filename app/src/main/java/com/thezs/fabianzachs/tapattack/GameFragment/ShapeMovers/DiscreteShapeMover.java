package com.thezs.fabianzachs.tapattack.GameFragment.ShapeMovers;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes.ShapeObject;

import java.util.ArrayList;

public class DiscreteShapeMover implements ShapeMover {

    //private  ArrayList<Integer> yAxisShapeLocations;
    private int yStepSize;
    private int furtherDownCenterLocation;

    public DiscreteShapeMover(int yStepSize, int furtherDownCenterLocation/*, int shapeRadius, int shapeSpacing*/) {
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
    public void update(ArrayList<ShapeObject> shapes) {

        if (shapes.size()!=0 && missingShapeInFurthestDownSlot(shapes)) {
            moveAllShapesDownAStep(shapes);
        }
    }

    /*
    private void setyStepSize(int shapeRadius, int shapeSpacing) {
        yStepSize = 2*shapeRadius + shapeSpacing;
    }
    */


    private boolean missingShapeInFurthestDownSlot(ArrayList<ShapeObject> shapes) {
       //return  getFurthestDownShape(shapes).getBitmapHolder().bottom < yAxisShapeLocations.get(0);
        return  getFurthestDownShape(shapes).getBitmapHolder().bottom < furtherDownCenterLocation;
    }

    private ShapeObject getFurthestDownShape(ArrayList<ShapeObject> shapes) {
        return shapes.get(shapes.size()-1);
    }

    private void moveAllShapesDownAStep(ArrayList<ShapeObject> shapes) {
        for (ShapeObject shape : shapes) {
            shape.incrementY(yStepSize);
        }
    }
}
