package com.thezs.fabianzachs.tapattack.GameFragment;

import java.util.ArrayList;
import java.util.Random;

public class ColorPicker {

    private ColorGetter colorGetter;

    private int NUMBER_OF_COLORS = 5;
    private ArrayList<Integer> colors;
    private Integer warningColor;
    private Random random = new Random();
    private boolean maxProbabilityReached;

    public ColorPicker(float probabilityOfWarningColor) {
        this.colorGetter = new SimpleColorGetter(probabilityOfWarningColor);

    }

    public Integer getWarningColor() {
        return 0x00000000;
        //return warningColor;
    }

    public Integer getNonWarningColor() {
        return 0xffffffff;
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

        @Override
        public Integer getColor() {
            return null;
        }
    }
}
