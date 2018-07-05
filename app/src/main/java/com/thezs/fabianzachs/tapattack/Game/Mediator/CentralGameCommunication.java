package com.thezs.fabianzachs.tapattack.Game.Mediator;

import android.util.Log;

import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.ShapeColorPicker;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.ShapeMover;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.ShapeObject;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.ShapesManager;
import com.thezs.fabianzachs.tapattack.Game.GamePanel;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.Score;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.Streak;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.WarningColor;
import com.thezs.fabianzachs.tapattack.Game.MainGameActivity;
import com.thezs.fabianzachs.tapattack.R;

/**
 * Created by fabianzachs on 08/03/18.
 */

public class CentralGameCommunication {

    private long gameStartTime;
    private int WARNINGCOLOR_SHAPE_DESTROY_POINTS = 5; //todo what class should this go into

    private ShapesManager shapesManager; // TODO  do we need this??
    private MainGameActivity mainGameActivity;
    private GamePanel gamePanel;
    private ShapeMover shapeMover;
    private Score score;
    private Streak streak;
    //private ProgressBar progressBar;
    private WarningColor warningColor;
    private ShapeColorPicker shapeColorPicker;
    private boolean shapesMoving = false;

    /* Tasks:
    * recieve messages regarding destruction of shape, incorrect taps of shape, (passing required info)
    *
     */
    public CentralGameCommunication(long gameStartTime) {
        this.gameStartTime = gameStartTime;

    }

    public void setGameOver(String gameOverReason) {
        gamePanel.endRunningThread();
        mainGameActivity.showGameOverScreen(gameOverReason, score.getScore(), streak.getCurrentGameHighestStreak());
        // todo get score and streak into prefs if highscore (do elsewhere, like when we create dialog, so pass in game score and game highest streak acheived)
    }

    public Integer getIntWarningColor() {
        return this.warningColor.getCurrentIntColor();
    }

    public String getStrWarningColor() {
        return this.warningColor.getCurrentStrColor();
    }

    public long getGameStartTime() {
        return gameStartTime;
    }

    public void incrementStartTimeBy(long timeAmount) {
        this.gameStartTime += timeAmount;
        shapeMover.updateStartTime();
    }


    /*
    //  ===== PROGRESSBAR CALLS =====
    public void changeProgressBarBy(int amount, String color) {
        Log.d("warningcolor", "changeProgressBarBy: color clicked: " + color);
        changeProgressBarBy(warningColor.getCurrentStrColor().equals(color) ? -amount * 2 : amount);
    }*/

    /*public void changeProgressBarBy(int amount) {
        progressBar.changeProgressBy(amount);
    }*/

    //  ==============================

    //  ===== STREAK CALLS =====
    public void resetStreak() {
        streak.resetStreak();
    }

    public void incStreak(int amount, String color) {
        if (warningColor.getCurrentStrColor().equals(color)) {

            streak.resetStreak();
            warningColor.shake();
        }
        else
            streak.incStreak(amount);
    }

    //  ==============================

    //  ===== SCORE CALLS =====
    public void incScore(int amount, String color) { // TODO make ternary operation like above
        if (!warningColor.getCurrentStrColor().equals(color))
            score.incScore(amount);
    }

    public void incScore(int amount) {
        score.incScore(amount);
    }

    //  ==============================



    public void addObject(ShapesManager shapesManager) {
        this.shapesManager = shapesManager;
    }

    public void addObject(ShapeColorPicker shapeColorPicker) {
        this.shapeColorPicker = shapeColorPicker;
    }

    public void addObject(Score score) {
        this.score = score;
    }

    public void addObject(Streak streak) {
        this.streak = streak;
    }
/*
    public void addObject(ProgressBar progressBar) {
        //this.progressBar = progressBar;
    }
*/
    public void addObject(WarningColor warningColor) {
        this.warningColor = warningColor;
    }

    public void addObject(ShapeMover shapeMover) {
        this.shapeMover = shapeMover;
    }

    public void addObject(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void addObject(MainGameActivity mainGameActivity) {
        this.mainGameActivity = mainGameActivity;
    }

    public void warningComponentShake() {
        this.warningColor.shake();
    }

    public void warningColorChanged(String strColor) {
        shapeColorPicker.setWarningColor(strColor);
        //shapeColorPicker.setWarningColor(warningColor.getCurrentStrColor());
    }

    public void executeReward(int streak) {
        switch (streak) {
            case 0:
                return;
            case 1:
                rewardOne();
                return;
            case 2:
                rewardTwo();
                return;
            case 3:
                rewardThree();
                return;
        }
    }

    private void rewardThree() {
        shapesManager.turnShapesIntoStars();
    }

    private void rewardTwo() {
        shapeMover.oppositeDirection();
        // todo still need to impliment speed decrease
    }

    private void rewardOne() {
        score.incScore(WARNINGCOLOR_SHAPE_DESTROY_POINTS);
    }

    public void editWaningColorStreak(ShapeObject shape) {
        int streak = warningColor.findCurrentStreak(shape);
        executeReward(streak);


        return;

        //warningColor.checkForStreakReset(shape);

        /*
        if (shape.getColorInt().equals(warningColor.getPreviousIntWarningColor())) {
            warningColor.incStreak();
        }
        else{
            warningColor.resetColorHistory();
            warningColor.resetWarningStreak();
            //Log.d("warningstreak", "restr" );
        }
*/
        //Log.d("warningstreak", "editWaningColorStreak: streak" + warningColor.getStreak());
        //StyleableToast.makeText(Constants.CURRENT_CONTEXT, warningColor.getStreak() + "", R.style.successtoast).show();
        //Log.d("warningstreak", "editWaningColorStreak: shape" + shape.getColorInt());
        //Log.d("warningstreak", "editWaningColorStreak: oldwarning" + warningColor.getPreviousIntWarningColor());
    }

    public int getWarningColorStreak() {
        return warningColor.getStreak();
    }

    public void startGameMotion() {
        this.shapesMoving = true;
    }

    public boolean gameMoving() {
        return shapesMoving;
    }

    //public void addObject(ShapeObject shape) {
        // TODO no need to add the shapes as an attribute, nobody tells shapes anything.. we just want to send messages
    //}


}
