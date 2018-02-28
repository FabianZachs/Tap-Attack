package com.thezs.fabianzachs.tapattack.Game.GameModeScenes;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import com.thezs.fabianzachs.tapattack.Game.GameObjects.ShapesManager;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.ProgressBar;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.Score;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.Streak;
import com.thezs.fabianzachs.tapattack.Game.LayoutHeadingHandlers.LayoutHeadings;
import com.thezs.fabianzachs.tapattack.Game.Scene;

import static android.content.ContentValues.TAG;

/**
 * Created by fabianzachs on 07/02/18.
 */

public class ClassicGameScene implements Scene {

    private static boolean gameOver;
    private ShapesManager shapesManager;
    private Score score;
    private Streak streak;
    private ProgressBar progressBar;

    public ClassicGameScene() {
        this.gameOver = false; // TODO or/and on reset?
        shapesManager = new ShapesManager();
        score = new Score();

    }

    @Override
    public void update() {
        // TODO update Score via score.setScore(shapesManager.getScore())
        // TODO same for streak
        shapesManager.update();

    }

    @Override
    public void draw(Canvas canvas) {
        // set background color canvas.drawARGB(..);
        // TODO draw score drawtext via score.draw(canvas)
        // TODO same for streak
        score.draw(canvas);
        shapesManager.draw(canvas);

    }

    @Override
    public void terminate() {

    }

    @Override
    public void recieveTouch(MotionEvent event) {
        // pass action to shapesmanager so it can assign the touch to the specific shape
        // then the shape will update depending on that touch
        //if (!gameOver) {
            //shapesManager.recieveTouch(event);
        //}
        shapesManager.recieveTouch(event);
    }

    @Override
    public void setGameOver(Boolean gameOver) {
        this.gameOver = gameOver;
    }

    @Override
    public Boolean getGameOver() {
        return this.gameOver;
    }


    // what happens when game is over (possibly game over screen before reset)
    public void reset() {

    }
}
