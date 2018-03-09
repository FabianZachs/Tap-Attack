package com.thezs.fabianzachs.tapattack.Game.GameUIComponents.UIHolders;

import android.graphics.drawable.GradientDrawable;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.BackgroundHandlers.BackgroundHandler;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.ProgressBar;

/**
 * Created by fabianzachs on 09/03/18.
 */

public class ProgressBarHolder {

    private GradientDrawable progressBarHolderDrawable;

    public ProgressBarHolder(BackgroundHandler backgroundHandler) {
        backgroundHandler.attachProgressBarHolderObserver(this);
        this.progressBarHolderDrawable = (GradientDrawable) Constants.progressBarHolder.getDrawable();
    }

    public void changeColor(String color) {
        progressBarHolderDrawable.setColors(Constants.progressBarHolderAndWarningHolderColors.get(color));
    }
}
