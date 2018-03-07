package com.thezs.fabianzachs.tapattack.Game.GameUIComponents;

import android.graphics.drawable.GradientDrawable;

import com.thezs.fabianzachs.tapattack.Constants;

/**
 * Created by fabianzachs on 05/03/18.
 */

// TODO fade in at a specific time using yoyo
    /*
        YoYo.with(Techniques.FadeIn)
                .duration(3500)
                .repeat(0)
                .playOn(warningComponent);
     */
public class WarningColor {

    private GradientDrawable warningDrawable;

    public WarningColor() {
        this.warningDrawable = (GradientDrawable) Constants.warningComponent.getDrawable(1);
    }
}
