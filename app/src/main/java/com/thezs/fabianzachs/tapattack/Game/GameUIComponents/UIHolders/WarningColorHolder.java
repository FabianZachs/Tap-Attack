package com.thezs.fabianzachs.tapattack.Game.GameUIComponents.UIHolders;

import android.graphics.drawable.GradientDrawable;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.BackgroundHandlers.BackgroundHandler;

/**
 * Created by fabianzachs on 09/03/18.
 */

public class WarningColorHolder {

    private GradientDrawable warningHolderDrawable;

    // TODO remove once old background handler removed
    public WarningColorHolder(BackgroundHandler backgroundHandler) {
        backgroundHandler.attachWarningColorHolderObserver(this);
        this.warningHolderDrawable = (GradientDrawable) Constants.warningComponent.getDrawable(0);
    }

    public WarningColorHolder() {
        this.warningHolderDrawable = (GradientDrawable) Constants.warningComponent.getDrawable(0);
    }

    // TODO remove once old background handler removed
    public void changeColor(String color) {
        warningHolderDrawable.setColors(Constants.progressBarHolderAndWarningHolderColors.get(color));
    }

    public void setColor(int[] colors) {
        warningHolderDrawable.setColors(colors);
    }
}
