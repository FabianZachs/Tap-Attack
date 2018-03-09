package com.thezs.fabianzachs.tapattack.Game.GameUIComponents.UIHolders;

import android.graphics.drawable.GradientDrawable;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.BackgroundHandlers.BackgroundHandler;

/**
 * Created by fabianzachs on 09/03/18.
 */

public class WarningColorHolder {

    private GradientDrawable warningHolderDrawable;

    public WarningColorHolder(BackgroundHandler backgroundHandler) {
        backgroundHandler.attachWarningColorHolderObserver(this);
        this.warningHolderDrawable = (GradientDrawable) Constants.warningComponent.getDrawable(0);
    }

    public void changeColor(String color) {
        warningHolderDrawable.setColors(Constants.progressBarHolderAndWarningHolderColors.get(color));
    }
}
