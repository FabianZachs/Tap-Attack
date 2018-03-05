package com.thezs.fabianzachs.tapattack.Game.BackgroundHandlers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fabianzachs on 16/02/18.
 */

public class BackgroundHandler {

    // an arraylist: color -> Bitmap background
    private Map<String, Bitmap> backgrounds;
    private String theme;
    private GradientDrawable progressBarHolderDrawable;
    private GradientDrawable warningHolderDrawable; // TODO


    public BackgroundHandler(String theme) {
        this.progressBarHolderDrawable = (GradientDrawable) Constants.progressBarHolder.getDrawable();
        this.warningHolderDrawable = (GradientDrawable) Constants.warningComponent.getDrawable(0);
        this.theme = theme;
        this.backgrounds = new HashMap<>();
        addBackgroundsToMap(theme);
    }

    private void changeProgressBarBackground(String color) {
        //GradientDrawable replacer = (GradientDrawable) Constants.CURRENT_CONTEXT.getResources().getDrawable(R.drawable.progressbarholder);
        //replacer.setColors(colors);
        progressBarHolderDrawable.setColors(Constants.progressBarHolderAndWarningHolderColors.get(color));
    }


    private void addBackgroundsToMap(String theme) {
        String[] colors = Constants.COLORS.get(theme);
        BitmapFactory bf = new BitmapFactory();
        for (String color : colors) {
            String backgroundName = theme + color + "background";
            int resID = Constants.CURRENT_CONTEXT.getResources().getIdentifier(backgroundName, "drawable", Constants.CURRENT_CONTEXT.getPackageName());
            Bitmap img = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), resID);
            backgrounds.put(backgroundName, img);
        }
    }

    // TODO when we get a new background we update the warning colorholder and warning color
    public Bitmap getBackgroundBitmap(String color) {
        changeProgressBarBackground(color);
        changeWarningHolderColor(color);
        return backgrounds.get(theme+color+"background");
    }

    private void changeWarningHolderColor(String color) {
        warningHolderDrawable.setColors(Constants.progressBarHolderAndWarningHolderColors.get(color));
    }
}
