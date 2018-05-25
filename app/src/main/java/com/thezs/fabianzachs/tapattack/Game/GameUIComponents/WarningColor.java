package com.thezs.fabianzachs.tapattack.Game.GameUIComponents;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
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

    private Handler mHandler = new Handler();
    private ImageView warningComponent;
    private GradientDrawable warningDrawable;
    private int colorIndex;

    private String[] strColors;
    private Integer[] intColors;

    public static boolean running = true;

    private boolean shake;


    public WarningColor(String[] strColors, Integer[] intColors) {
        this.colorIndex = 0;
        this.warningDrawable = (GradientDrawable) Constants.warningComponent.getDrawable(1);
        setAndRandomizeArrays(strColors,intColors);
        setNextColor();




        running = true;
        this.warningComponent= Constants.warningComponentImg;
        YoYo.with(Techniques.FadeIn)
                .duration(1500)
                .repeat(0)
                .playOn(warningComponent);

        // TODO make sure thread is disposed of properly at end
        Thread timer = new Thread() {
            public void run() {
                while (running) {
                    try {
                        Constants.GAME_ACTIVITY.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //if (shake == true)

                            }
                        });
                        Thread.sleep(100);

                    } catch (InterruptedException e) {
                        Log.d("running", "run: error");
                        e.printStackTrace();
                    }
                }
            }
        };
        timer.start();


    }


    public void recieveTouch(MotionEvent event) {
        // TODO handle scrolling
        if (event.getAction() == MotionEvent.ACTION_DOWN)
            setNextColor();

        // TODO or scrolling, set lives == rand(10-40) then reduce lives per scroll -- if lives <= 0, setNextColor() & set lives == rand(10-40)
    }


    public void setNextColor() {
        colorIndex++;
        colorIndex = colorIndex >= intColors.length ? 0 : colorIndex;
        warningDrawable.setColor(intColors[colorIndex]);
    }

    public String getCurrentStrColor() {
        return strColors[colorIndex];
    }

    public Integer getCurrentIntColor() {
        return intColors[colorIndex];
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

    public void shake() {
        /*
        YoYo.with(Techniques.Shake)
                .duration(750)
                .repeat(0)
                .playOn(Constants.warningComponentImg);
        shake = true; */
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                YoYo.with(Techniques.Shake)
                        .duration(750)
                        .repeat(0)
                        .playOn(warningComponent);
            }
        });
    }
}
