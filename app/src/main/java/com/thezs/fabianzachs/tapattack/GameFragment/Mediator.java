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
    private SoundEffectsManager soundEffectsManager;

    public Mediator() {
        resetStartTime();
    }


    public void levelComplete() {
        // todo implement later
        isGameOver = true;
        Log.d("islevel", "levelComplete: ");
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
            return null;
        return warningColorComponent.getCurrentWarningColor();
    }

    public void addObject(WarningColorComponent warningColorComponent) {
        this.warningColorComponent = warningColorComponent;
    }

    public void addObject(ShapeMover shapeMover) {
        this.shapeMover = shapeMover;
    }

    public void addObject(SoundEffectsManager soundEffectsManager) {
        this.soundEffectsManager = soundEffectsManager;
    }

    public void playCircleSoundEffect() {
        soundEffectsManager.soundEffects.playCircleTap();
    }

    public void playArrowSoundEffect() {
        soundEffectsManager.soundEffects.playArrowSwipe();
    }

    public void playSquare1SoundEffect() {
        soundEffectsManager.soundEffects.playSquareTapOne();
    }

    public void playSquare2SoundEffect() {
        soundEffectsManager.soundEffects.playSquareTapTwo();
    }

    public void playStarSoundEffect() {
        soundEffectsManager.soundEffects.playStarTap();
    }

}
