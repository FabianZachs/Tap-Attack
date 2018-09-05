package com.thezs.fabianzachs.tapattack.GameFragment.ShapeMovers;

import android.util.Log;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.GameFragment.Mediator;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes.ShapeObject;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class ContinuousShapeMover implements ShapeMover {

    private long timeAtLastUpdate;
    private Speed speed;
    private Mediator mediator;

    public ContinuousShapeMover(Mediator mediator, float startSecondsToMoveEntireScreen,
                                float finalSecondsToMoveEntireScreen, int timeUntilFinal) {
        this.mediator = mediator;
        timeAtLastUpdate = System.currentTimeMillis();
        mediator.addObject(this);
        speed = new GrowingSpeed(startSecondsToMoveEntireScreen, finalSecondsToMoveEntireScreen, timeUntilFinal);
    }

    public ContinuousShapeMover(Mediator mediator, float secondsToMoveEntireScreen) {
        this.mediator = mediator;
        timeAtLastUpdate = System.currentTimeMillis();
        mediator.addObject(this);
        speed = new ConstantSpeed(secondsToMoveEntireScreen);
    }

    public void setConstantSpeed(float secondsToMoveEntireScreen) {
        speed.setSpeed(secondsToMoveEntireScreen);
    }

    @Override
    public void update(CopyOnWriteArrayList<ShapeObject> shapes) {
        int timeSinceLastFrame = (int) (System.currentTimeMillis() - timeAtLastUpdate);
        timeAtLastUpdate = System.currentTimeMillis();
        double incAmount = (double) speed.getCurrentSpeed()*timeSinceLastFrame;

        for (ShapeObject shape : shapes) {
            //shape.incrementY( (double) speed.getCurrentSpeed()*timeSinceLastFrame);
            shape.incrementY(incAmount);
        }

    }

    @Override
    public void resetTimeAtLastUpdate() {
        this.timeAtLastUpdate = System.currentTimeMillis();
    }

    public interface Speed {
        float getCurrentSpeed();
        void setSpeed(float secondsToMoveEntireScreen);
    }

    public class ConstantSpeed implements Speed {
        float speed;

        public ConstantSpeed(float secondsToMoveEntireScreen) {
            this.speed = Constants.GAME_VIEW_HEIGHT/secondsToMoveEntireScreen;
        }

        public void setSpeed(float secondsToMoveEntireScreen) {
            this.speed = Constants.GAME_VIEW_HEIGHT/secondsToMoveEntireScreen;
        }

        @Override
        public float getCurrentSpeed() {
            return speed;
        }
    }

    public class GrowingSpeed implements Speed {

        private int TIME_UNTIL_MAX_SPEED;
        private float startSecondsToMoveEntireScreen;
        private float finalSecondsToMoveEntireScreen;

        public GrowingSpeed(float startSecondsToMoveEntireScreen, float finalSecondsToMoveEntireScreen,
                            int timeUntilFinal) {

            this.TIME_UNTIL_MAX_SPEED = timeUntilFinal;
            this.startSecondsToMoveEntireScreen = startSecondsToMoveEntireScreen;
            this.finalSecondsToMoveEntireScreen = finalSecondsToMoveEntireScreen;
        }

        @Override
        public float getCurrentSpeed() {


            if (mediator.getElapsedGameTime()>TIME_UNTIL_MAX_SPEED)
                return Constants.GAME_VIEW_HEIGHT/finalSecondsToMoveEntireScreen;

            float denominator = ((finalSecondsToMoveEntireScreen - startSecondsToMoveEntireScreen)/TIME_UNTIL_MAX_SPEED)
                    * mediator.getElapsedGameTime() + startSecondsToMoveEntireScreen;

            return Constants.GAME_VIEW_HEIGHT/denominator;
        }

        @Override
        public void setSpeed(float secondsToMoveEntireScreen) {
        }
    }
}
