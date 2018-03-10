package com.thezs.fabianzachs.tapattack.Game.Mediator;

import android.util.Log;

import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.ShapeObject;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.ShapesManager;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.ProgressBar;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.Score;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.Streak;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.WarningColor;

/**
 * Created by fabianzachs on 08/03/18.
 */

public class CentralGameCommunication {

    private ShapesManager shapesManager; // TODO  do we need this??
    private Score score;
    private Streak streak;
    private ProgressBar progressBar;
    private WarningColor warningColor;

    /* Tasks:
    * recieve messages regarding destruction of shape, incorrect taps of shape, (passing required info)
    *
     */
    public CentralGameCommunication() {

    }

    //  ===== PROGRESSBAR CALLS =====
    public void changeProgressBarBy(int amount, String color) {
        changeProgressBarBy(warningColor.getCurrentColor().equals(color) ? -amount : amount); // TODO doesnt trigger shake since called from update in thread.. maybe add shake to ui thread like for progress bar but not a while loop
    }

    public void changeProgressBarBy(int amount) {
        progressBar.changeProgressBy(amount);
    }

    //  ==============================

    //  ===== STREAK CALLS =====
    public void resetStreak() {
        streak.resetStreak();
    }

    public void incStreak(int amount, String color) {
        if (warningColor.getCurrentColor().equals(color))
            streak.resetStreak();
        else
            streak.incStreak(amount);
    }

    //  ==============================

    //  ===== SCORE CALLS =====
    public void incScore(int amount, String color) { // TODO make ternary operation like above
        if (!warningColor.getCurrentColor().equals(color))
            score.incScore(amount);
    }

    public void incScore(int amount) {
        score.incScore(amount);
    }

    //  ==============================



    public void addObject(ShapesManager shapesManager) {
        this.shapesManager = shapesManager;
    }

    public void addObject(Score score) {
        this.score = score;
    }

    public void addObject(Streak streak) {
        this.streak = streak;
    }

    public void addObject(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public void addObject(WarningColor warningColor) {
        this.warningColor = warningColor;
    }

    //public void addObject(ShapeObject shape) {
        // TODO no need to add the shapes as an attribute, nobody tells shapes anything.. we just want to send messages
    //}


}
