package com.thezs.fabianzachs.tapattack.Game.GraveObjects;

import android.graphics.Canvas;
import android.graphics.Point;
import android.util.Log;

import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.Arrow;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.ShapeObject;

import java.util.Vector;

import javax.xml.datatype.Duration;

/**
 * Created by fabianzachs on 03/03/18.
 */

public class ArrowGrave extends GraveObject {

    private String movementDirection;
    private Point startPoint;
    private Point endPoint;
    private Point currentLocation;
    private final int TRAVEL_DISTANCE = 200;
    private int[] TRAVEL_VECTOR;
    private float slopeOfTravel;
    private float constantOfTravel;

    public ArrowGrave(Arrow shapeToCreateGraveFrom) {
        super(shapeToCreateGraveFrom);
        this.TRAVEL_VECTOR = new int[2];
        this.movementDirection = shapeToCreateGraveFrom.getIntendedFlickDirectionString();
        this.startPoint = shapeToCreateGraveFrom.getCenterLocation();
        setupEndPointAndTRAVELVECTOR();
        this.currentLocation = startPoint;
        //this.slopeOfTravel = findSlopeOfTravel();
        //this.constantOfTravel = findConstantOfTravel();
        setDURATION(0.6f);

        Log.d("arrowgrave", "startlocation" + startPoint );
    }

    private float findConstantOfTravel() {
        return  -startPoint.y - slopeOfTravel * startPoint.x;
    }

    private float findSlopeOfTravel() {
        return (-endPoint.y + startPoint.y) / (endPoint.x - startPoint.x);
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }


    @Override
    public void update() {
        this.currentLocation = getCurrentLocation();
        setBitmapHolderLocation(currentLocation);
        setPaint(getAlphaPaint());


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
                break;
            case "DOWN":
                x = startPoint.x;
                y = startPoint.y + TRAVEL_DISTANCE;
                TRAVEL_VECTOR[0] = 0;
                TRAVEL_VECTOR[1] = TRAVEL_DISTANCE;
                break;
            case "LEFT":
                x = startPoint.x - TRAVEL_DISTANCE;
                y = startPoint.y;
                TRAVEL_VECTOR[0] = -TRAVEL_DISTANCE;
                TRAVEL_VECTOR[1] = 0;
                break;
            case "RIGHT":
                x = startPoint.x + TRAVEL_DISTANCE;
                y = startPoint.y;
                TRAVEL_VECTOR[0] = TRAVEL_DISTANCE;
                TRAVEL_VECTOR[1] = 0;
                break;
        }
        this.endPoint = new Point(x,y);
    }

    public Point getCurrentLocation() {
        // TODO based on time left
        Log.d("times", "duration: " + getDURATION() + " timeleft: " + getTimeLeft());
        //float one =getTimeLeft()/(getDURATION()*1000);
        int x = (int) (endPoint.x - (getTimeLeft()/ (getDURATION()*1000)) * TRAVEL_VECTOR[0]);
        int y = (int) (endPoint.y - (getTimeLeft()/ (getDURATION()*1000)) * TRAVEL_VECTOR[1]);
        Log.d("locationo", "getCurrentLocation: x:" +x +" y: " + y);
        return new Point(x,y);
    }
}
