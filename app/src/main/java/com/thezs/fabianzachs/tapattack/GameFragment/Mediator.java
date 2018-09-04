package com.thezs.fabianzachs.tapattack.GameFragment;

import android.util.Log;

import com.thezs.fabianzachs.tapattack.GameFragment.ShapeMovers.ContinuousShapeMover;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeMovers.ShapeMover;

public class Mediator {

    private long gameStartTime;
    private boolean isGameOver = false;
    private boolean hasGameStarted = false;
    private WarningColorComponent warningColorComponent;
    private ShapeMover shapeMover;

    public Mediator() {
        resetStartTime();
    }

    public void setGameOver(final String gameOverReason) {
        isGameOver = true;
    }

    public void startGame() {
        hasGameStarted = true;
        resetStartTime();
        shapeMover.resetTimeAtLastUpdate();
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
        if (warningColorComponent == null)
            throw new IllegalArgumentException("this game mode has no warning color");
        return warningColorComponent.getCurrentWarningColor();
    }

    public void addObject(WarningColorComponent warningColorComponent) {
        this.warningColorComponent = warningColorComponent;
    }

    public void addObject(ShapeMover shapeMover) {
        this.shapeMover = shapeMover;
    }

}
