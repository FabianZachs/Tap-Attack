package com.thezs.fabianzachs.tapattack.Game.BackgroundHandlers.Backgrounds;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.thezs.fabianzachs.tapattack.Constants;

/**
 * Created by fabianzachs on 15/03/18.
 */

public class GameBackground {

    private Bitmap background;

    public GameBackground(String bitmapName) {
        BitmapFactory bf = new BitmapFactory();
        int resID = Constants.CURRENT_CONTEXT.getResources().getIdentifier(bitmapName, "drawable", Constants.CURRENT_CONTEXT.getPackageName());
        background = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), resID);
    }

    public Bitmap getBackground() {
        return this.background;
    }
}
