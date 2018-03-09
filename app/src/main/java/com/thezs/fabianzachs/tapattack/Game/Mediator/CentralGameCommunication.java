package com.thezs.fabianzachs.tapattack.Game.Mediator;

import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.ShapeObject;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.ShapesManager;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.ProgressBar;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.Score;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.Streak;

/**
 * Created by fabianzachs on 08/03/18.
 */

public class CentralGameCommunication {

    private ShapesManager shapesManager;
    private Score score;
    private Streak streak;
    private ProgressBar progressBar;

    /* Tasks:
    * recieve messages regarding destruction of shape, incorrect taps of shape, (passing required info)
    *
     */
    public CentralGameCommunication() {

    }

    //  ===== PROGRESSBAR CALLS =====
    public void changeProgressBarBy(int amount) {
        progressBar.changeProgressBy(amount);
    }

    //  ==============================

    //  ===== STREAK CALLS =====
    public void resetStreak() {
        streak.resetStreak();
    }

    public void incStreak(int amount) {
        streak.incStreak(amount);
    }

    //  ==============================

    //  ===== PROGRESSBAR CALLS =====
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

    //public void addObject(ShapeObject shape) {
        // TODO no need to add the shapes as an attribute, nobody tells shapes anything.. we just want to send messages
    //}


}
