package com.thezs.fabianzachs.tapattack.GameFragment.ShapeMovers;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.GameFragment.Mediator;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes.ShapeObject;

import java.util.ArrayList;

public class ContinuousShapeMover implements ShapeMover {

    private long startTime;
    private long timeAtLastUpdate;
    private Speed speed;
    private Mediator mediator;

    public ContinuousShapeMover(Mediator mediator) {
        this.mediator = mediator;
        resetStartTime();
        timeAtLastUpdate = System.currentTimeMillis();
    }

    public void setSpeed(Speed speed) {
        this.speed = speed;
    }

    @Override
    public void update(ArrayList<ShapeObject> shapes) {
        int timeSinceLastFrame = (int) (System.currentTimeMillis() - timeAtLastUpdate);
        timeAtLastUpdate = System.currentTimeMillis();

        for (ShapeObject shape : shapes) {
            shape.incrementY(speed.getCurrentSpeed()*timeSinceLastFrame);
        }


    }

    public void resetStartTime() {
        this.startTime = System.currentTimeMillis();
    }



    public interface Speed {
        float getCurrentSpeed();
    }

    public class ConstantSlowSpeed implements Speed {

        float speed = Constants.GAME_VIEW_HEIGHT/3500.0f;
        public ConstantSlowSpeed(){

        }

        @Override
        public float getCurrentSpeed() {
            return speed;
        }
    }

    public class ConstantMediumSpeed implements Speed {

        float speed = Constants.GAME_VIEW_HEIGHT/2800.0f;

        @Override
        public float getCurrentSpeed() {
            return speed;
        }
    }
    public class ConstantFastSpeed implements Speed {
        float speed = Constants.GAME_VIEW_HEIGHT/1500.0f;

        @Override
        public float getCurrentSpeed() {
            return speed;
        }
    }
    public class GrowingSlowSpeed implements Speed {

        private int TIME_UNTIL_MAX_SPEED = 2000;
        private int MIN_DENOMINATOR= 500;
        private int MAX_DENOMINATOR = 3500;
        private boolean maxSpeedReached = false;

        @Override
        public float getCurrentSpeed() {
            float denominator = (float) (((MAX_DENOMINATOR - MIN_DENOMINATOR)/(-TIME_UNTIL_MAX_SPEED))
                    *mediator.getElapsedGameTime()+MAX_DENOMINATOR);
            return Constants.GAME_VIEW_HEIGHT/denominator;
        }
    }
    public class GrowingMediumSpeed implements Speed {

        @Override
        public float getCurrentSpeed() {
            return 0;
        }
    }
    public class GrowingFastSpeed implements Speed {

        @Override
        public float getCurrentSpeed() {
            return 0;
        }
    }
}
