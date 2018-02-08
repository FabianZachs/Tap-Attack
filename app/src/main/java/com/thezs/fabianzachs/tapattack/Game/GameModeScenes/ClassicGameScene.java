package com.thezs.fabianzachs.tapattack.Game.GameModeScenes;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.thezs.fabianzachs.tapattack.Game.GameObjects.ShapesManager;
import com.thezs.fabianzachs.tapattack.Game.Scene;

/**
 * Created by fabianzachs on 07/02/18.
 */

public class ClassicGameScene implements Scene {

    public boolean gameOver;
    private ShapesManager shapesManager;

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
    public void recieveTouch(MotionEvent event) {
        // pass action to shapesmanager so it can assign the touch to the specific shape
        // then the shape will update depending on that touch
        if (!gameOver) {
            shapesManager.recieveTouch(event);
        }
    }

    // what happens when game is over (possibly game over screen before reset)
    public void reset() {

    }
}
