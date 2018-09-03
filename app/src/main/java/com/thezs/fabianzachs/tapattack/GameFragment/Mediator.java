package com.thezs.fabianzachs.tapattack.GameFragment;

public class Mediator {

    private long gameStartTime;
    private boolean isGameOver = false;
    private boolean hasGameStarted = false;
    private WarningColorComponent warningColorComponent;

    public Mediator() {
        resetStartTime();
    }

    public void setGameOver(final String gameOverReason) {
        isGameOver = true;
    }

    public void startGame() {
        hasGameStarted = true;
        resetStartTime();
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

}
