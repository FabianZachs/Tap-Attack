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
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.UIHolders.ProgressBarHolder;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.UIHolders.WarningColorHolder;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.WarningColor;
import com.thezs.fabianzachs.tapattack.Game.LayoutHeadingHandlers.LayoutHeadings;
import com.thezs.fabianzachs.tapattack.Game.Mediator.CentralGameCommunication;
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

    private CentralGameCommunication mediator;

    private WarningColorHolder warningColorHolder; // TODO maybe set XML to clear by default in case game does not have this component?
    private ProgressBarHolder progressBarHolder;
    private WarningColor warningColor;
    private BackgroundHandler backgroundHandler;
    private ShapesManager shapesManager;
    private ProgressBar progressBar;
    private Streak streak;
    private Score score;

    private Rect entireScreenRect;

    public ClassicGameScene() {
        this.mediator = new CentralGameCommunication();



        // shuffle colors
        Constants.NEONCOLORS = RandomizeArray(Constants.NEONCOLORS); // TODO make this responsive to any theme
        this.entireScreenRect = new Rect(0,0,Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        this.gameOver = false; // TODO or/and on reset?


        this.shapesManager = new ShapesManager(mediator);
        initializeBackgroundHandler();
        this.warningColorHolder = new WarningColorHolder(backgroundHandler);
        this.progressBarHolder = new ProgressBarHolder(backgroundHandler);
        this.warningColor = new WarningColor(backgroundHandler);
        this.score = new Score();
        this.streak = new Streak();
        this.progressBar = new ProgressBar();

        mediator.addObject(score);
        mediator.addObject(streak);
        mediator.addObject(progressBar);
        mediator.addObject(shapesManager);
        mediator.addObject(warningColor);

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
