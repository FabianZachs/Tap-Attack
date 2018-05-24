package com.thezs.fabianzachs.tapattack.Game.BackgroundHandlers.Backgrounds;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.ProgressBar;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.UIHolders.ProgressBarHolder;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.UIHolders.WarningColorHolder;

/**
 * Created by fabianzachs on 15/03/18.
 */

public abstract class GameBackground {

    private Bitmap background;
    private WarningColorHolder warningColorHolder;
    //private ProgressBarHolder progressBarHolder;

    public GameBackground(String bitmapName, int[] UIHolderColors) {
        this.warningColorHolder = new WarningColorHolder();
        //this.progressBarHolder = new ProgressBarHolder();
        setupBitmap(bitmapName);
        setupHolders(UIHolderColors);

    }

    private void setupBitmap(String bitmapName) {
        BitmapFactory bf = new BitmapFactory();
        int resID = Constants.CURRENT_CONTEXT.getResources().getIdentifier(bitmapName, "drawable", Constants.CURRENT_CONTEXT.getPackageName());
        this.background = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), resID);
    }

    public Bitmap getBackground() {
        return this.background;
    }

    protected void setupHolders(int[] colors) {
        warningColorHolder.setColor(colors);
        //progressBarHolder.setColor(colors);
    }
}
