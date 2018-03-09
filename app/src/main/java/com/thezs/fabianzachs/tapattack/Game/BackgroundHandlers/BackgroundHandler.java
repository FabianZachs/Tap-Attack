package com.thezs.fabianzachs.tapattack.Game.BackgroundHandlers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.GradientDrawable;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.ProgressBar;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.UIHolders.ProgressBarHolder;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.UIHolders.WarningColorHolder;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.WarningColor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fabianzachs on 16/02/18.
 */

public class BackgroundHandler {

    // an arraylist: color -> Bitmap background
    private Map<String, Bitmap> backgrounds;
    private Bitmap previousBitmap;
    private String theme;
    private WarningColorHolder warningColorHolderObserver;
    private ProgressBarHolder progressBarHolderObserver;
    private WarningColor warningColorObserver;
    //private GradientDrawable progressBarHolderDrawable;
    //private GradientDrawable warningHolderDrawable; // TODO
    private String previousColor;
    //private WarningColor warningColorComponent;


    public BackgroundHandler(String theme) {
        //this.progressBarHolderDrawable = (GradientDrawable) Constants.progressBarHolder.getDrawable();
        //this.warningHolderDrawable = (GradientDrawable) Constants.warningComponent.getDrawable(0);
        this.theme = theme;
        this.backgrounds = new HashMap<>();
        addBackgroundsToMap(theme);
        this.previousColor = "null";
        this.previousBitmap = null;
        //this.warningColorComponent = new WarningColor(); // TODO use observer instead
    }

    // TODO when we get a new background we update the warning colorholder and warning color
    public Bitmap getBackgroundBitmap(String color) {

        if (!previousColor.equals(color)) {
            progressBarHolderObserver.changeColor(color);
            warningColorHolderObserver.changeColor(color);
            warningColorObserver.setNextColor();
            previousColor = color;
            previousBitmap = backgrounds.get(theme+color+"background");
        }
        // TODO store background here to eliminate constant char[] calls
        return previousBitmap;
    }

    /*
    private void changeWarningHolderColor(String color) {
        warningHolderDrawable.setColors(Constants.progressBarHolderAndWarningHolderColors.get(color));
    }*/

    /*
    private void changeProgressBarBackground(String color) {
        //GradientDrawable replacer = (GradientDrawable) Constants.CURRENT_CONTEXT.getResources().getDrawable(R.drawable.progressbarholder);
        //replacer.setColors(colors);
        progressBarHolderDrawable.setColors(Constants.progressBarHolderAndWarningHolderColors.get(color));
    }*/

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

    /*public WarningColor getWarningColorComponent() {
        return this.warningColorComponent;
    }*/

    public void attachWarningColorHolderObserver(WarningColorHolder warningColorHolder) {
        this.warningColorHolderObserver = warningColorHolder;
    }

    public void attachProgressBarHolderObserver(ProgressBarHolder progressBarHolder) {
        this.progressBarHolderObserver = progressBarHolder;
    }

    public void attachWarningColorObserver(WarningColor warningColor) {
        this.warningColorObserver = warningColor;
    }
}
