package com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.GraveObjects;

import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes.Arrow;

public class ArrowGrave extends GraveObject {

    private int[] TRAVEL_VECTOR;
    private Point endPoint;
    private int shapeRadius;

    public ArrowGrave(Arrow shape) {
        super(shape, 0.3f);
        //Log.d("arrowgrave", "ArrowGrave: ");
        this.TRAVEL_VECTOR = new int[2];
        this.shapeRadius = shape.getShapeRadius();
        Point startPoint = shape.getCenterLocation();
        setupEndPointAndTRAVELVECTOR(startPoint, shape.getIntendedFlickDirectionString());
    }

    public void update() {
       setBitmapHolderLocation(getCurrentLocation());
       setGravePaint(getAlphaPaint(getGravePaint()));
    }

    private Point getCurrentLocation() {
        int x = (int) (endPoint.x - (getTimeLeft()/ (GRAVE_DURATION*1000)) * TRAVEL_VECTOR[0]);
        int y = (int) (endPoint.y - (getTimeLeft()/ (GRAVE_DURATION*1000)) * TRAVEL_VECTOR[1]);
        return new Point(x,y);
    }

    private void setBitmapHolderLocation(Point newCenterLocation) {
        graveHolder.set(newCenterLocation.x - shapeRadius, newCenterLocation.y - shapeRadius,
                newCenterLocation.x + shapeRadius, newCenterLocation.y + shapeRadius);
    }

    private Paint getAlphaPaint(Paint paint) {
        paint.setAlpha(255);
        paint.setAlpha((int) ((255 * getTimeLeft()) / 1000));
        return paint;
    }

    private void setupEndPointAndTRAVELVECTOR(Point startPoint, String movementDirection) {
        int TRAVEL_DISTANCE = 200;
        int x = 0;
        int y = 0;

        switch (movementDirection) {
            case "up":
                x = startPoint.x;
                y = startPoint.y - TRAVEL_DISTANCE;
                TRAVEL_VECTOR[0] = 0;
                TRAVEL_VECTOR[1] = -TRAVEL_DISTANCE;
                break;
            case "down":
                x = startPoint.x;
                y = startPoint.y + TRAVEL_DISTANCE;
                TRAVEL_VECTOR[0] = 0;
                TRAVEL_VECTOR[1] = TRAVEL_DISTANCE;
                break;
            case "left":
                x = startPoint.x - TRAVEL_DISTANCE;
                y = startPoint.y;
                TRAVEL_VECTOR[0] = -TRAVEL_DISTANCE;
                TRAVEL_VECTOR[1] = 0;
                break;
            case "right":
                x = startPoint.x + TRAVEL_DISTANCE;
                y = startPoint.y;
                TRAVEL_VECTOR[0] = TRAVEL_DISTANCE;
                TRAVEL_VECTOR[1] = 0;
                break;
        }
        this.endPoint = new Point(x,y);
    }
}
