package com.thezs.fabianzachs.tapattack.GameFragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Database.MyDBHandler;
import com.thezs.fabianzachs.tapattack.helper;

public class Background {

    private Drawable backgroundDrawable;

    public Background(Activity activity) {
        SharedPreferences preferences = activity.getSharedPreferences("playerInfo", Context.MODE_PRIVATE);
        preferences.edit().putString(Constants.BACKGROUND_TAG, Constants.BACKGROUNDS[0]).apply();
        MyDBHandler dbHandler = new MyDBHandler(activity, null, null, 1);

        String backgroundFile = dbHandler.getCurrentBackgroundFile();
        backgroundDrawable = helper.getDrawableFromFileName(activity, backgroundFile);
        backgroundDrawable.setBounds(0,0,Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
    }

    public void draw(Canvas canvas) {
        backgroundDrawable.draw(canvas);
    }
}
