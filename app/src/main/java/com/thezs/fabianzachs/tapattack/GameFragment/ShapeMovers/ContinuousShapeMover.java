package com.thezs.fabianzachs.tapattack.GameFragment.ShapeMovers;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.GameFragment.Mediator;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes.ShapeObject;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class ContinuousShapeMover implements ShapeMover {

    private long timeAtLastUpdate;
    private Speed speed;
    private Mediator mediator;

    public ContinuousShapeMover(Mediator mediator) {
        this.mediator = mediator;
        mediator.addObject(this);
        timeAtLastUpdate = System.currentTimeMillis();
    }

    public ContinuousShapeMover(Mediator mediator, float secondsToMoveEntireScreen) {
        this.mediator = mediator;
        timeAtLastUpdate = System.currentTimeMillis();
        mediator.addObject(this);
        speed = new ConstantSpeed(secondsToMoveEntireScreen);
    }

    public void setSpeed(Speed speed) {
        this.speed = speed;
    }

    @Override
    public void update(CopyOnWriteArrayList<ShapeObject> shapes) {
        int timeSinceLastFrame = (int) (System.currentTimeMillis() - timeAtLastUpdate);
        timeAtLastUpdate = System.currentTimeMillis();

        for (ShapeObject shape : shapes) {
            shape.incrementY(speed.getCurrentSpeed()*timeSinceLastFrame);
        }

    }

    public void resetTimeAtLastUpdate() {
        this.timeAtLastUpdate = System.currentTimeMillis();
    }

    public interface Speed {
        float getCurrentSpeed();
    }

    public class ConstantSpeed implements Speed {
        float speed;

        public ConstantSpeed(float secondsToMoveEntireScreen) {
            this.speed = Constants.GAME_VIEW_HEIGHT/secondsToMoveEntireScreen;
        }

        @Override
        public float getCurrentSpeed() {
            return speed;
        }
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
