package com.thezs.fabianzachs.tapattack.Game.GraveObjects;

import android.graphics.Canvas;
import android.graphics.Point;

import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.Arrow;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.ShapeObject;

import java.util.Vector;

/**
 * Created by fabianzachs on 03/03/18.
 */

public class ArrowGrave extends GraveObject {

    private String movementDirection;
    private Point startPoint;
    private Point endPoint;
    private Point currentLocation;
    private final int TRAVEL_DISTANCE = 900;
    private int[] TRAVEL_VECTOR;

    public ArrowGrave(Arrow shapeToCreateGraveFrom) {
        super(shapeToCreateGraveFrom);
        this.TRAVEL_VECTOR = new int[2];
        this.movementDirection = shapeToCreateGraveFrom.getIntendedFlickDirectionString();
        this.startPoint = shapeToCreateGraveFrom.getCenterLocation();
        setupEndPointAndTRAVELVECTOR();
        this.currentLocation = startPoint;
        setDURATION(5);

    }


    public void draw(Canvas canvas) {
        this.currentLocation = getCurrentLocation();
        setBitmapHolderLocation(currentLocation);



        canvas.drawBitmap(getGraveImg(),null,getBitmapHolder(), getPaint());
    }

    private void setupEndPointAndTRAVELVECTOR() {
        int x = 0;
        int y = 0;

        switch (movementDirection) {
            case "UP":
                x = startPoint.x;
                y = startPoint.y - TRAVEL_DISTANCE;
                TRAVEL_VECTOR[0] = 0;
                TRAVEL_VECTOR[1] = -TRAVEL_DISTANCE;
            case "DOWN":
                x = startPoint.x;
                y = startPoint.y + TRAVEL_DISTANCE;
                TRAVEL_VECTOR[0] = 0;
                TRAVEL_VECTOR[1] = TRAVEL_DISTANCE;
            case "LEFT":
                x = startPoint.x - TRAVEL_DISTANCE;
                y = startPoint.y;
                TRAVEL_VECTOR[0] = -TRAVEL_DISTANCE;
                TRAVEL_VECTOR[1] = 0;
            case "RIGHT":
                x = startPoint.x + TRAVEL_DISTANCE;
                y = startPoint.y;
                TRAVEL_VECTOR[0] = TRAVEL_DISTANCE;
                TRAVEL_VECTOR[1] = 0;
        }
        this.endPoint = new Point(x,y);
    }

    public Point getCurrentLocation() {
        // TODO based on time left
        int x = (int) (endPoint.x - getTimeLeft() * TRAVEL_VECTOR[0]);
        int y = (int) (endPoint.y - getTimeLeft() * TRAVEL_VECTOR[1]);
        return new Point(x,y);
    }
}
