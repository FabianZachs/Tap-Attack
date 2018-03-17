package com.thezs.fabianzachs.tapattack.Game.GameUIComponents;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;

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
    private String[] colorSelection;
    private String currentColor;
    private Random randColorFinder;
    private int colorIndex;

    // TODO old constructor --remove--
    public WarningColor(BackgroundHandler backgroundHandler) {
        backgroundHandler.attachWarningColorObserver(this);
        this.colorIndex = 0;
        this.warningDrawable = (GradientDrawable) Constants.warningComponent.getDrawable(1);
        this.colorSelection = Constants.NEONCOLORS;
        this.currentColor = "null";
    }

    public WarningColor(String[] colors) {
        this.colorIndex = 0;
        this.warningDrawable = (GradientDrawable) Constants.warningComponent.getDrawable(1);
        // TODO get the color selection from themes
    }


    public void setCurrentColor(String color) {
        this.currentColor = color;
        warningDrawable.setColor(Constants.progressBarHolderAndWarningHolderColors.get(color)[0]);
        //warningDrawable.setColor(Constants.holderBlue[1]);
    }

    public void setNextColor() {
        setCurrentColor(colorSelection[colorIndex]);
        colorIndex++;
        colorIndex = colorIndex >= colorSelection.length ? 0 : colorIndex;
    }

    public String getCurrentColor() {
        return this.currentColor;
    }

}
