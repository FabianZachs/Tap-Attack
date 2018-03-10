package com.thezs.fabianzachs.tapattack.Game.BackgroundHandlers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
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

    private Map<String, Bitmap> backgrounds; // color -> bitmap
    private Bitmap previousBitmap;
    private String previousColor;
    private String theme;

    private Rect entireScreenRect;

    private WarningColorHolder warningColorHolderObserver;
    private ProgressBarHolder progressBarHolderObserver;
    private WarningColor warningColorObserver;


    public BackgroundHandler(String theme) {
        this.backgrounds = new HashMap<>();
        this.theme = theme;
        this.entireScreenRect = new Rect(0,0,Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        addBackgroundsToMap(theme);
        this.previousColor = "null";
        this.previousBitmap = null;
    }

    public void draw(Canvas canvas, String blue) {
        canvas.drawBitmap(getBackgroundBitmap("blue"), null,
                entireScreenRect, null);
    }

    public Bitmap getBackgroundBitmap(String color) {

        if (!previousColor.equals(color)) {
            progressBarHolderObserver.changeColor(color);
            warningColorHolderObserver.changeColor(color);
            warningColorObserver.setNextColor(); //TODO next color should be 1 step ahead of bacground. so if background was blue and turns to green, the warning color was green. Or vice versa
            previousColor = color;
            previousBitmap = backgrounds.get(theme+color+"background");
        }
        return previousBitmap;
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
