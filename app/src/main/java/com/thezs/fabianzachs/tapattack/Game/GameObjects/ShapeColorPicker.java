package com.thezs.fabianzachs.tapattack.Game.GameObjects;

import android.util.Log;

import com.thezs.fabianzachs.tapattack.Animation.Themes.ThemesManager;
import com.thezs.fabianzachs.tapattack.Constants;

import java.util.Random;

/**
 * Created by fabianzachs on 31/05/18.
 */

public class ShapeColorPicker {

    private int NUMBER_OF_COLORS = 6;
    private String warningColor;
    private String[] colors;
    private Random randGenerator;
    private long initTime;


    public ShapeColorPicker(long initTime) {
        this.initTime = initTime;
        this.colors = ThemesManager.getStrColors(Constants.CURRENT_THEME);
    }

    // TODO setup observer so that this is updated everytime warning color changes
    public void setWarningColor(String newWarningColor) {}

    public String getColorForShape() {

        // todo first find whether warning color or no warning color

        // todo if warning color, return that

        // todo else choose one of remaining NUMBER_OF_COLORS-1 colors (all equal probability)

        return null;
    }

    // currently 1/6 chance max at 60 seconds in
    public double getProbabilityOfWarningColor() {
        float secondsSinceStart = getGameTime()/1000.0f; // seconds
        Log.d("warningcolorprob", "getProbabilityOfWarningColor: secondsinscestart" + secondsSinceStart);

        //Math.log((0.003* secondsSinceStart) +1);
        double returnthis =Math.log(0.003* secondsSinceStart +1);

        Log.d("warningcolorprob", "getProbabilityOfWarningColor: " +returnthis);
        return returnthis;
    }

    public long getGameTime() {
        //Log.d("warningcolorprob", "getGameTime: " + (System.currentTimeMillis() - initTime)/1000);
        return System.currentTimeMillis() - initTime;
    }


}
