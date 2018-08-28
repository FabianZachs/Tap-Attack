package com.thezs.fabianzachs.tapattack.GameFragment.GameComponents.GameModes;

import android.app.Activity;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.GameFragment.GameAnimation;
import com.thezs.fabianzachs.tapattack.GameFragment.GameUI;

public abstract class GameMode {

    protected boolean warningColorEnabled;

    public GameMode(View view, boolean warningColorEnabled) {
        this.warningColorEnabled = warningColorEnabled;
    }


    public void update() {

    }

    public void draw(Canvas canvas) {

    }

    public void recieveTouch(MotionEvent event) {
        Log.d("touchreca", "recieveTouch: ");

    }
}
