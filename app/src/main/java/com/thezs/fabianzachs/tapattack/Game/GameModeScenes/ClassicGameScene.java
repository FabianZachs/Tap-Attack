package com.thezs.fabianzachs.tapattack.Game.GameModeScenes;

import android.graphics.Canvas;

import com.thezs.fabianzachs.tapattack.Game.Scene;

/**
 * Created by fabianzachs on 07/02/18.
 */

public class ClassicGameScene implements Scene {

    public boolean gameOver;

    public ClassicGameScene() {

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void terminate() {

    }

    @Override
    public void recieveTouch() {
        // pass action to shapesmanager so it can assign the touch to the specific shape
        // then the shape will update depending on that touch
    }
}
