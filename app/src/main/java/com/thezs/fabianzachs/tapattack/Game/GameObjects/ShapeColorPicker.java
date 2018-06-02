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
    private ArrayList<String> colors;
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

        //float leftIf = randGenerator.nextFloat();
        //double rightIf = getProbabilityOfWarningColor();
        //Log.d("checkprob", "getColorForShape: " + leftIf + "/n" + rightIf);


        double probabilityOfWarningColor = getProbabilityOfWarningColor();
        //Log.d("probabilityofwarning", "before: " + probabilityOfWarningColor);
        if (probabilityOfWarningColor >= MAX_PROBABILITY_OF_WARNING_COLOR)
            probabilityOfWarningColor = MAX_PROBABILITY_OF_WARNING_COLOR;

        //Log.d("probabilityofwarning", "getColorForShape: " + probabilityOfWarningColor);
        // tODO if last getProbabilityofWarningcolor > 1/6 then keep at 1/6 chance
        //Log.d("randomfloat", "getColorForShape: " + randGenerator.nextFloat());
        if (randGenerator.nextFloat() <= probabilityOfWarningColor) {
        //if (leftIf<=rightIf) {
            //Log.d("checkprob", "getColorForShape: warning color chosen");
            //Log.d("returningcolor", "getColorForShape: warning color:");
            return warningColor;

        }

        ArrayList<String> selectableColors = colors;
        selectableColors.remove(warningColor);
        return selectableColors.get(randGenerator.nextInt(NUMBER_OF_COLORS-1));



    }

    // currently 1/6 chance max at 60 seconds in
    public double getProbabilityOfWarningColor() {
        float secondsSinceStart = getGameTime()/1000.0f; // seconds
        //Log.d("warningcolorprob", "getProbabilityOfWarningColor: secondsinscestart" + secondsSinceStart);

        //Math.log((0.003* secondsSinceStart) +1);
        double returnthis =Math.log(0.003* secondsSinceStart +1);

        //Log.d("warningcolorprob", "getProbabilityOfWarningColor: " +returnthis);
        return returnthis;
    }

    public long getGameTime() {
        //Log.d("warningcolorprob", "getGameTime: " + (System.currentTimeMillis() - initTime)/1000);
        return System.currentTimeMillis() - initTime;
    }


}
