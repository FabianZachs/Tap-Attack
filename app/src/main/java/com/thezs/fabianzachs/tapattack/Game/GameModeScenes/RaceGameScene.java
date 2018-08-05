package com.thezs.fabianzachs.tapattack.Game.GameModeScenes;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.thezs.fabianzachs.tapattack.Game.Mediator.CentralGameCommunication;
import com.thezs.fabianzachs.tapattack.Game.Scene;

/**
 * Created by fabianzachs on 02/08/18.
 */

public class RaceGameScene implements Scene {

    public RaceGameScene(CentralGameCommunication mediator) {
        // todo shapepopulator has x circles, y squares, z arrows, p stars
        // todo shapemover has discrete stages
        // todo background click increases timer
        // todo draw timer not score
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

    }

    @Override
    public void setGameOver(Boolean gameOver) {

    }

    @Override
    public Boolean getGameOver() {
        return null;
    }
}
