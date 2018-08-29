package com.thezs.fabianzachs.tapattack.GameFragment.ShapeMovers;

import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes.ShapeObject;

import java.util.ArrayList;

public class ContinuousShapeMover implements ShapeMover {

    private long startTime;
    private long timeOfLastUpdate;
    private Speed speed;

    public ContinuousShapeMover(Speed speed) {
        resetStartTime();
        this.speed = speed;
    }

    @Override
    public void update(ArrayList<ShapeObject> shapes) {

    }

    public void resetStartTime() {
        this.startTime = System.currentTimeMillis();
    }

    public interface Speed {
        double getCurrentSpeed(int elapsedTime);
    }

    public class ConstantSlowSpeed implements Speed {

        @Override
        public double getCurrentSpeed(int elapsedTime) {
            return 0;
        }
    }

    public class ConstantMediumSpeed implements Speed {

        @Override
        public double getCurrentSpeed(int elapsedTime) {
            return 0;
        }
    }
    public class ConstantFastSpeed implements Speed {

        @Override
        public double getCurrentSpeed(int elapsedTime) {
            return 0;
        }
    }
    public class GrowingSlowSpeed implements Speed {

        @Override
        public double getCurrentSpeed(int elapsedTime) {
            return 0;
        }
    }
    public class GrowingMediumSpeed implements Speed {

        @Override
        public double getCurrentSpeed(int elapsedTime) {
            return 0;
        }
    }
    public class GrowingFastSpeed implements Speed {

        @Override
        public double getCurrentSpeed(int elapsedTime) {
            return 0;
        }
    }
}
