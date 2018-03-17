package com.thezs.fabianzachs.tapattack.Game.GameUIComponents;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.MotionEvent;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.BackgroundHandlers.BackgroundHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by fabianzachs on 05/03/18.
 */

// TODO fade in at a specific time using yoyo
    /*
        YoYo.with(Techniques.FadeIn)
                .duration(3500)
                .repeat(0)
                .playOn(warningComponent);
     */
public class WarningColor {

    private GradientDrawable warningDrawable;
    private String currentColor;
    private int colorIndex;

    private String[] strColors;
    private Integer[] intColors;

    /*
    // TODO old constructor --remove--
    public WarningColor(BackgroundHandler backgroundHandler) {
        backgroundHandler.attachWarningColorObserver(this);
        this.colorIndex = 0;
        this.warningDrawable = (GradientDrawable) Constants.warningComponent.getDrawable(1);
        this.colorSelection = Constants.NEONCOLORS;
        this.currentColor = "null";
    }*/

    public WarningColor(String[] strColors, Integer[] intColors) {
        this.colorIndex = 0;
        this.warningDrawable = (GradientDrawable) Constants.warningComponent.getDrawable(1);
        setAndRandomizeArrays(strColors,intColors);
        setNextColor();
    }

    public void recieveTouch(MotionEvent event) {
        // TODO handle scrolling
        if (event.getAction() == MotionEvent.ACTION_DOWN)
            setNextColor();
    }

/*
    public void setCurrentColor(String color) {
        this.currentColor = color;
        warningDrawable.setColor(Constants.progressBarHolderAndWarningHolderColors.get(color)[0]);
        //warningDrawable.setColor(Constants.holderBlue[1]);
    }*/

    public void setNextColor() {
        colorIndex = colorIndex >= intColors.length ? 0 : colorIndex;
        warningDrawable.setColor(intColors[colorIndex]);
        //setCurrentColor(colorSelection[colorIndex]);
        colorIndex++;
    }

    public String getCurrentColor() {
        return this.currentColor;
    }


    private void setAndRandomizeArrays(String[] strColors, Integer[] intColors){
        Random rgen = new Random();  // Random number generator

        for (int i=0; i<strColors.length; i++) {
            int randomPosition = rgen.nextInt(strColors.length);
            String strTemp = strColors[i];
            Integer intTemp = intColors[i];

            strColors[i] = strColors[randomPosition];
            intColors[i] = intColors[randomPosition];

            strColors[randomPosition] = strTemp;
            intColors[randomPosition] = intTemp;
        }
        this.strColors = strColors;
        this.intColors = intColors;
    }
}
