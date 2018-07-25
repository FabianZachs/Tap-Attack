package com.thezs.fabianzachs.tapattack.Game.GameModeScenes;

import android.app.Activity;
import android.graphics.Canvas;
import android.view.MotionEvent;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.Scene;

/**
 * Created by fabianzachs on 25/07/18.
 */

public class InstructionsGameScene implements Scene{

    private int instructionIndex = 0;
    //private int NUMBER_OF_INSTRUCTIONS = 10; // todo update when number found
    private boolean instructionsDone = false;


    public InstructionsGameScene() {

    }


    private void instructionToDraw(Canvas canvas) {
        switch (instructionIndex) {
            case 0:
                //circleInstruction(canvas);
                return;
            case 1:
                instructionsDone = true;
                return;
            case 2:
                return;
            case 3:
                return;
            case 4:
                instructionsDone = true;
        }
    }


    private void nextInstruction() {
        this.instructionIndex++;
    }

    @Override
    public void update() {
        if (instructionsDone)
            ((Activity) Constants.CURRENT_CONTEXT).finish();
            // todo end instructions sequence to main menu

    }

    @Override
    public void draw(Canvas canvas) {
        instructionToDraw(canvas);
    }

    @Override
    public void terminate() {

    }

    @Override
    public void recieveTouch(MotionEvent event) {
        nextInstruction();
    }

    @Override
    public void setGameOver(Boolean gameOver) {

    }

    @Override
    public Boolean getGameOver() {
        return null;
    }
}
