package com.thezs.fabianzachs.tapattack.GameFragment;

public class Mediator {

    private long gameStartTime;
    private boolean isGameOver = false;
    private WarningColorComponent warningColorComponent;

    public Mediator() {
        resetStartTime();
    }

    public void setGameOver() {
        isGameOver = true;
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

}
