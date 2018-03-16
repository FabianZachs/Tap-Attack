package com.thezs.fabianzachs.tapattack.Game.GameModeScenes;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.BackgroundHandlers.BackgroundHandler;
import com.thezs.fabianzachs.tapattack.Game.BackgroundHandlers.BackgroundManager;
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

    private static boolean gameOver; // TODO private???

    private CentralGameCommunication mediator;

    //private WarningColorHolder warningColorHolder; // takeout
    //private ProgressBarHolder progressBarHolder; // takeout
    private WarningColor warningColor;
    private ProgressBar progressBar;
    private Streak streak;
    private Score score;
    private long initTime;

    //private BackgroundHandler backgroundHandler; // takeout
    private BackgroundManager backgroundManager;
    private ShapesManager shapesManager;


    public ClassicGameScene() {
        this.initTime = System.currentTimeMillis();
        this.gameOver = false; // TODO or/and on reset?
        this.mediator = new CentralGameCommunication();

        Constants.NEONCOLORS = RandomizeArray(Constants.NEONCOLORS); // TODO make this responsive to any theme

        this.shapesManager = new ShapesManager(mediator, initTime);
        this.backgroundManager = new BackgroundManager("backgroundtriangleblue");
        //this.backgroundManager = new BackgroundManager(Constants.CURRENT_BACKGROUND);
        //this.backgroundHandler = new BackgroundHandler(Constants.CURRENT_THEME);

        //this.warningColorHolder = new WarningColorHolder(backgroundHandler);
        //this.progressBarHolder = new ProgressBarHolder(backgroundHandler);
        //this.warningColor = new WarningColor(backgroundHandler);
        this.progressBar = new ProgressBar();
        this.streak = new Streak();
        this.score = new Score();

        mediator.addObject(score);
        mediator.addObject(streak);
        mediator.addObject(progressBar);
        mediator.addObject(shapesManager);
        mediator.addObject(warningColor);

    }

    @Override
    public void update() {
        shapesManager.update();

    }

    @Override
    public void draw(Canvas canvas) {
        //backgroundHandler.draw(canvas, "blue");
        backgroundManager.draw(canvas);
        score.draw(canvas);
        streak.draw(canvas);
        shapesManager.draw(canvas);

    }

    @Override
    public void terminate() {

    }

    @Override
    public void recieveTouch(MotionEvent event) {
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


    private static String[] RandomizeArray(String[] array){
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
