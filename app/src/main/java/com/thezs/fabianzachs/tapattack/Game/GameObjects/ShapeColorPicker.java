package com.thezs.fabianzachs.tapattack.Game.GameObjects;

import android.util.Log;

import com.thezs.fabianzachs.tapattack.Animation.Themes.ThemesManager;
import com.thezs.fabianzachs.tapattack.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by fabianzachs on 31/05/18.
 */

public class ShapeColorPicker {

    private int NUMBER_OF_COLORS;
    private double MAX_PROBABILITY_OF_WARNING_COLOR = 0.1666;
    private boolean maxProbabilityReached = false;
    private String warningColor;
    //private String[] colors;
    private ArrayList<String> colors; // todo change to integer colors
    private Random randGenerator;
    private long initTime;


    public ShapeColorPicker(long initTime) {
        this.initTime = initTime;
        this.colors = new ArrayList<>(Arrays.asList(ThemesManager.getStrColors(Constants.CURRENT_THEME)));
        this.NUMBER_OF_COLORS = colors.size();
        this.randGenerator = new Random();
    }

    // TODO setup observer so that this is updated everytime warning color changes
    public void setWarningColor(String newWarningColor) {
        //Log.d("newwarningcolor", "setWarningColor: " + newWarningColor);
        this.warningColor = newWarningColor;
    }

    public String getColorForShape() {

        //Log.d("java.lang.null", "\n\n");
        //Log.d("java.long.null", "WARNINGCOLOR" + warningColor);
        //float leftIf = randGenerator.nextFloat();
        //double rightIf = getProbabilityOfWarningColor();
        //Log.d("checkprob", "getColorForShape: " + leftIf + "/n" + rightIf);
        double probabilityOfWarningColor;

        if (maxProbabilityReached)
            probabilityOfWarningColor = MAX_PROBABILITY_OF_WARNING_COLOR;
        else {
            probabilityOfWarningColor = getProbabilityOfWarningColor();
            maxProbabilityReached = probabilityOfWarningColor >= MAX_PROBABILITY_OF_WARNING_COLOR;
        }

        //double probabilityOfWarningColor = getProbabilityOfWarningColor();
        //Log.d("probabilityofwarning", "before: " + probabilityOfWarningColor);
        //if (probabilityOfWarningColor >= MAX_PROBABILITY_OF_WARNING_COLOR)
            //probabilityOfWarningColor = MAX_PROBABILITY_OF_WARNING_COLOR;


        //Log.d("probabilityofwarning", "getColorForShape: " + probabilityOfWarningColor);
        //Log.d("randomfloat", "getColorForShape: " + randGenerator.nextFloat());
        if (randGenerator.nextFloat() <= probabilityOfWarningColor) {
        //if (leftIf<=rightIf) {
            //Log.d("checkprob", "getColorForShape: warning color chosen");
            //Log.d("returningcolor", "getColorForShape: warning color:");
            //Log.d("java.lang.null", "getColorForShape: warningcolor return: " + warningColor);
            return warningColor;

        }


        ArrayList<String> selectableColors = new ArrayList<>(colors);

        //Log.d("java.lang.indexoutofbounds", "colors size" + colors.size());
        //Log.d("java.lang.indexoutofbounds", "size: " + selectableColors.size());
        selectableColors.remove(warningColor);
        //Log.d("java.lang.indexoutofbounds", "size: " + selectableColors.size());
        //Log.d("java.lang.indexoutofbounds", "\n\n");
        String colorToReturn =selectableColors.get(randGenerator.nextInt(NUMBER_OF_COLORS-1));
        //Log.d("java.lang.null", "getColorForShape: normal color return: " + colorToReturn );
        return colorToReturn;



    }

    // currently 1/6 chance max at 60 seconds in
    public double getProbabilityOfWarningColor() {
        float secondsSinceStart = getGameTime()/1000.0f; // seconds
        //Log.d("warningcolorprob", "getProbabilityOfWarningColor: secondsinscestart" + secondsSinceStart);

        //Math.log((0.003* secondsSinceStart) +1);
        double returnthis = Math.log(0.003* secondsSinceStart +1);

        //Log.d("warningcolorprob", "getProbabilityOfWarningColor: " +returnthis);
        return returnthis;
    }

    public long getGameTime() {
        //Log.d("warningcolorprob", "getGameTime: " + (System.currentTimeMillis() - initTime)/1000);
        return System.currentTimeMillis() - initTime;
    }


}
