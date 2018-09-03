package com.thezs.fabianzachs.tapattack.GameFragment;

import android.util.Log;

import com.thezs.fabianzachs.tapattack.GameFragment.ShapeMovers.ContinuousShapeMover;

public class Mediator {

    private long gameStartTime;
    private boolean isGameOver = false;
    private boolean hasGameStarted = false;
    private WarningColorComponent warningColorComponent;
    private ContinuousShapeMover continuousShapeMover;

    public Mediator() {
        resetStartTime();
    }

    public void setGameOver(final String gameOverReason) {
        isGameOver = true;
    }

    public void startGame() {
        hasGameStarted = true;
        resetStartTime();
        continuousShapeMover.resetTimeAtLastUpdate();
        // todo do whatever needs to be done when we start game, like reset inittime and tell shapemover .. look at old code
    }

    public boolean hasGameStarted() {
        return hasGameStarted;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void resetStartTime() {
        this.gameStartTime = System.currentTimeMillis();
    }

    public long getElapsedGameTime() {
        return System.currentTimeMillis() - gameStartTime;
    }

    public Integer getWarningColor() {
        return warningColorComponent.getCurrentWarningColor();
    }

    public void addObject(WarningColorComponent warningColorComponent) {
        this.warningColorComponent = warningColorComponent;
    }

    public void addObject(ContinuousShapeMover continuousShapeMover) {
        Log.d("moveradded", "addObject: added");
        this.continuousShapeMover= continuousShapeMover;
    }

}
