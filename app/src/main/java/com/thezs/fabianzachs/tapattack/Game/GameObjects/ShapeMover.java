package com.thezs.fabianzachs.tapattack.Game.GameObjects;

import android.graphics.drawable.shapes.Shape;
import android.util.Log;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.ShapeObject;
import com.thezs.fabianzachs.tapattack.Game.Mediator.CentralGameCommunication;

import java.util.concurrent.CopyOnWriteArrayList;

import javax.xml.datatype.Duration;

/**
 * Created by fabianzachs on 25/05/18.
 */

public class ShapeMover {

    private float MAX_SPEED = 1.2f;
    private boolean maxSpeedReached = false;
    private long startTime;
    //private long initTime;
    private CentralGameCommunication mediator;
    private float RATE_OF_SPEED_INCREASE; // TODO use log(x+1) function
    private int DURATION_OF_REVERSE = 800;
    private int directionVector;
    private int numeratorOfDenominator = 800000;
    private int timeShift = 0;

    public ShapeMover(CentralGameCommunication mediator) {
        //Log.d("speed", "previous speed: " + Constants.SCREEN_HEIGHT/5000.0f);
        directionVector = 1;
        this.mediator = mediator;
        this.startTime = System.currentTimeMillis();
        //this.initTime = System.currentTimeMillis();

        // todo universal time
    }

    public void resetStartTime() {
        this.startTime = System.currentTimeMillis();
    }

    public void updateShapeLocation(Shape shape) {}

    public void update(CopyOnWriteArrayList<ShapeObject> shapes) {
        /*
        if (!mediator.gameMoving()) {
            startTime = System.currentTimeMillis();
            Log.d("shapemover", "update: not moving");
        }*/

        //Log.d("shapemover", "update: moving");
        int elapsedTime = (int) (System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();
        //float speed = Constants.SCREEN_HEIGHT/5000.0f;

        //Log.d("speed", "now speed: " + getSpeed(elapsedTime));
        for (ShapeObject shape : shapes) {
            //shape.incrementY(directionVector * (float) getSpeed(elapsedTime) * elapsedTime);
            shape.incrementY(directionVector * (float) getSpeed2() * elapsedTime);
        }
    }

    private double getSpeed2() {
        return Constants.SCREEN_HEIGHT/970;
    }

    public void oppositeDirection() {
        this.directionVector = directionVector == 1 ? -1 : 1;
        final long timeOfReverse = System.currentTimeMillis();
        final long timeOfEndReverse = timeOfReverse + DURATION_OF_REVERSE;



        Thread timer = new Thread() {
            public void run() {
                boolean running = true;
                while (running) {
                    try {
                        if (timeOfEndReverse < System.currentTimeMillis() ) {
                            running = false;
                            directionVector = directionVector == 1 ? -1 : 1;
                            decreaseSpeed();
                        }
                        Thread.sleep(100);

                    } catch (InterruptedException e) {
                        Log.d("running", "run: error");
                        e.printStackTrace();
                    }
                }
            }
        };
        timer.start();
    }

    public void decreaseSpeed() {
        //this.numeratorOfDenominator += 300000;
        this.timeShift += 15000; // this amount of seconds back in time
        //Log.d("speeddebug", "decreaseSpeed  efwfgwigwgwrgwgwrgwgwgwgwrg:                               " + timeShift);
    }

    public void updateStartTime() {
        this.startTime = System.currentTimeMillis();
    }

    public double getSpeed(int elapsedTime) {

        double speed;

        if (!maxSpeedReached) {
            long currentGameTime = System.currentTimeMillis() - mediator.getGameStartTime();
            //Log.d("speeddebug", "getSpeed: time " + currentGameTime);
            double denominator = (800000/((.006*(currentGameTime-timeShift))+300));  // TODO check if good with function for producing warning colors
            //Log.d("speeddebug", "getSpeed: den " + denominator);
            //Log.d("speeddebug", "\n");
            speed = Constants.SCREEN_HEIGHT/ denominator;

            maxSpeedReached = speed >= MAX_SPEED;
            //return speed;
        } else
            speed = MAX_SPEED;
        return speed;


        //long currentGameTime = System.currentTimeMillis() - mediator.getGameStartTime();

        //Log.d("speed", "getSpeed: currenttime" + currentGameTime);
        //double denominator = (10000* (1/(Math.log(50000 * currentGameTime+ 100)))) + 5000;

        //double denominator = (3000000/((.021*currentGameTime)+300));
        //double denominator = (1000000/((.003*currentGameTime)+300));
        //double denominator = (900000/((.003*currentGameTime)+300)); // good start speed, but steeper slope
        //double denominator = (990000/((.126*currentGameTime)+300));  // TODO check if good with function for producing warning colors
        //double denominator = (900000/((.006*currentGameTime)+300));  // TODO check if good with function for producing warning colors
        //return Constants.SCREEN_HEIGHT/ denominator;
    }
}
