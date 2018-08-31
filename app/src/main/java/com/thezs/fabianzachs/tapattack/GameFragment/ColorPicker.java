package com.thezs.fabianzachs.tapattack.GameFragment;

import java.util.ArrayList;
import java.util.Random;

public class ColorPicker {

    private Mediator mediator;
    private ColorGetter colorGetter;

    private ArrayList<Integer> colors;
    private Random random = new Random();

    public ColorPicker(Mediator mediator, ArrayList<Integer> colors, float probabilityOfWarningColor) {
        this.mediator = mediator;
        this.colors = colors;
        this.colorGetter = new SimpleColorGetter(probabilityOfWarningColor);
    }

    public ColorPicker(Mediator mediator, ArrayList<Integer> colors, float maxProbability, int timeUntilMaxWarningColorProb) {
        this.mediator = mediator;
        this.colors = colors;
        this.colorGetter = new CustomColorGetter(maxProbability, timeUntilMaxWarningColorProb);
    }

    public Integer getWarningColor() {
        return mediator.getWarningColor();
    }

    public Integer getNonWarningColor() {
        return 0xffffffff;
        /*
        ArrayList<String> selectableColors = new ArrayList<>(colors);
        selectableColors.remove(warningColor);
        return  selectableColors.get(randGenerator.nextInt(4));
         */
    }

    public Integer getColor() {
        return colorGetter.getColor();
    }


    private interface ColorGetter {
        Integer getColor();
    }

    private class SimpleColorGetter implements ColorGetter {

        private float probabilityOfWarningColor;

        private SimpleColorGetter(float probabilityOfWarningColor) {
            this.probabilityOfWarningColor = probabilityOfWarningColor;
        }

        @Override
        public Integer getColor() {
            if (random.nextFloat() <= probabilityOfWarningColor)
                return getWarningColor();
            else
                return getNonWarningColor();
        }
    }


    private class CustomColorGetter implements ColorGetter {

        private float maxProbability;
        private int timeUntilMaxWarningColorProb;
        private boolean maxProbabilityReached;

        private CustomColorGetter(float maxProbability, int timeUntilMaxWarningColorProb) {
            this.maxProbability = maxProbability;
            this.timeUntilMaxWarningColorProb = timeUntilMaxWarningColorProb;
        }

        private float getWarningColorProbability() {
            if (maxProbabilityReached)
                return maxProbability;
            float currentProbabilityOfWarningColor = ((maxProbability/timeUntilMaxWarningColorProb) *
                mediator.getElapsedGameTime());

            maxProbabilityReached = currentProbabilityOfWarningColor >= maxProbability;

            return currentProbabilityOfWarningColor;
        }

        @Override
        public Integer getColor() {
            if (random.nextFloat() <= getWarningColorProbability())
                return getWarningColor();
            else
                return getNonWarningColor();
        }
    }
}
