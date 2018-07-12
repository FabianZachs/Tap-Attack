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

public class GameBackground {

    private Bitmap background;
    private WarningColorHolder warningColorHolder;
    //private ProgressBarHolder progressBarHolder;

    public GameBackground(Integer bitmapId, int[] warningColorHolderColors) {
        this.background = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), bitmapId);
        this.warningColorHolder = new WarningColorHolder();
        warningColorHolder.setColor(warningColorHolderColors);

    }

    public GameBackground(String bitmapName, int[] UIHolderColors) {
        this.warningColorHolder = new WarningColorHolder();
        //this.progressBarHolder = new ProgressBarHolder();
        this.background = getBackgroundBitmap(bitmapName);
        setupHolders(UIHolderColors);

    }

    public static Bitmap getBackgroundBitmap(String bitmapName) {
        BitmapFactory bf = new BitmapFactory();
        int resID = Constants.CURRENT_CONTEXT.getResources().getIdentifier(bitmapName, "drawable", Constants.CURRENT_CONTEXT.getPackageName());
        return bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), resID);
    }

    public Bitmap getBackground() {
        return this.background;
    }

    protected void setupHolders(int[] colors) {
        warningColorHolder.setColor(colors);
        //progressBarHolder.setColor(colors);
    }
}
