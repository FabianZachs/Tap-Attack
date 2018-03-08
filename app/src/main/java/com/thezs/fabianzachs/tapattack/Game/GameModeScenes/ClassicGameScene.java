package com.thezs.fabianzachs.tapattack.Game.GameModeScenes;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.BackgroundHandlers.BackgroundHandler;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.ShapesManager;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.ProgressBar;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.Score;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.Streak;
import com.thezs.fabianzachs.tapattack.Game.LayoutHeadingHandlers.LayoutHeadings;
import com.thezs.fabianzachs.tapattack.Game.Scene;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by fabianzachs on 07/02/18.
 */

public class ClassicGameScene implements Scene {

    private static boolean gameOver;
    private ShapesManager shapesManager;
    private Score score;
    private Streak streak;
    private ProgressBar progressBar;
    private BackgroundHandler backgroundHandler;
    private Rect entireScreenRect;

    public ClassicGameScene() {
        // shuffle colors
        Constants.NEONCOLORS = RandomizeArray(Constants.NEONCOLORS);
        this.entireScreenRect = new Rect(0,0,Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        this.gameOver = false; // TODO or/and on reset?
        shapesManager = new ShapesManager();
        initializeBackgroundHandler();
        score = new Score(shapesManager);
        streak = new Streak(shapesManager);
        progressBar = new ProgressBar(shapesManager);

    }

    private void initializeBackgroundHandler() {
        SharedPreferences prefs = Constants.CURRENT_CONTEXT.getSharedPreferences("playerPrefs", MODE_PRIVATE);

        // TODO change error: no theme to basic colorscheme
        String theme = prefs.getString("theme", "error: no theme");

        this.backgroundHandler = new BackgroundHandler(theme);
    }

    @Override
    public void update() {
        // TODO update Score via score.setScore(shapesManager.getScore())
        // TODO same for streak
        //score.setScore(shapesManager.getScore());
        //streak.setStreak(shapesManager.getStreak());

        // TODO implement THIS NEXT
        //progressBar.update(shapesManager.getProgress);
        shapesManager.update();

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(backgroundHandler.getBackgroundBitmap("blue"), null,
                entireScreenRect, null);


        // TODO draw score drawtext via score.draw(canvas)
        // TODO same for streak
        score.draw(canvas);
        streak.draw(canvas);
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


    public static String[] RandomizeArray(String[] array){
        Random rgen = new Random();  // Random number generator

        for (int i=0; i<array.length; i++) {
            int randomPosition = rgen.nextInt(array.length);
            String temp = array[i];
            array[i] = array[randomPosition];
            array[randomPosition] = temp;
        }

        return array;
    }
}
