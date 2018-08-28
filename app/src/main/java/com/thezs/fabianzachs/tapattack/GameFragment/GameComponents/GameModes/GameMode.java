package com.thezs.fabianzachs.tapattack.GameFragment.GameComponents.GameModes;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.thezs.fabianzachs.tapattack.GameFragment.ShapeBitmapManager;
import com.thezs.fabianzachs.tapattack.GameFragment.ThemeManager;
import com.thezs.fabianzachs.tapattack.GameFragment.WarningColorComponent;

public abstract class GameMode {

    protected boolean warningColorEnabled;

    public GameMode(View view, boolean warningColorEnabled) {
        this.warningColorEnabled = warningColorEnabled;

        ThemeManager themeManager = new ThemeManager();
        if (warningColorEnabled)
            new WarningColorComponent(view,themeManager.getColors());

        ShapeBitmapManager shapeBitmapManager = new ShapeBitmapManager();
    }



    public void update() {

    }

    public void draw(Canvas canvas) {

    }

    public void recieveTouch(MotionEvent event) {
        Log.d("touchreca", "recieveTouch: ");

    }
}
