package com.thezs.fabianzachs.tapattack.Game.GameModeScenes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;

import com.thezs.fabianzachs.tapattack.Animation.Themes.ThemesManager;
import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.BackgroundHandlers.BackgroundManager;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.ShapesManager;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.ProgressBar;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.Score;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.Streak;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.WarningColor;
import com.thezs.fabianzachs.tapattack.Game.Mediator.CentralGameCommunication;
import com.thezs.fabianzachs.tapattack.Game.Scene;

import java.util.Random;

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
    //private long initTime;

    //private BackgroundHandler backgroundHandler; // takeout
    private BackgroundManager backgroundManager;
    private ShapesManager shapesManager;


    public ClassicGameScene(CentralGameCommunication mediator) {
        //this.initTime = System.currentTimeMillis();
        // todo universal time
        this.gameOver = false; // TODO or/and on reset?
        this.mediator = mediator;
        mediator.resetInitTime();

        Constants.NEONCOLORS = RandomizeArray(Constants.NEONCOLORS); // TODO make this responsive to any theme

        this.shapesManager = new ShapesManager(mediator);
        //this.backgroundManager = new Background("backgroundtriangleblue");
        //this.backgroundManager = new Background(Constants.CURRENT_BACKGROUND);
        this.backgroundManager = new BackgroundManager();
        this.warningColor = new WarningColor(mediator,ThemesManager.getStrColors(Constants.CURRENT_THEME), ThemesManager.getIntColors(Constants.CURRENT_THEME));
        //this.backgroundManager = new Background(Constants.CURRENT_BACKGROUND);
        //this.backgroundHandler = new BackgroundHandler(Constants.CURRENT_THEME);

        //this.warningColorHolder = new WarningColorHolder(backgroundHandler);
        //this.progressBarHolder = new ProgressBarHolder(backgroundHandler);
        //this.warningColor = new WarningColor(backgroundHandler);
        //this.progressBar = new ProgressBar();
        this.streak = new Streak();
        this.score = new Score();

        mediator.addObject(score);
        mediator.addObject(streak);
        //mediator.addObject(progressBar);
        mediator.addObject(shapesManager);
        mediator.addObject(warningColor);

    }

    @Override
    public void update() {
        if (!mediator.isGameOver)
            shapesManager.update();

    }

    @Override
    public void draw(Canvas canvas) {
        //backgroundHandler.draw(canvas, "blue");
        backgroundManager.draw(canvas);
        score.draw(canvas);
        //streak.draw(canvas);
        if (!mediator.isGameOver)
            shapesManager.draw(canvas);
        else {
            shapesManager.drawGameOverREDO(canvas);
            //drawGameOverScreenDim(canvas);

        }
            //shapesManager.drawGameOver(canvas);
        warningColor.draw(canvas);

        // todo draw "TOUCH TO START" text in middle of screen if !mediator.gameMoving()
        if (!mediator.gameMoving()) {
            drawStartGameText(canvas);
        }

    }

    /*
    private void drawGameOverScreenDim(Canvas canvas) {
        Rect screen = new Rect();
        screen.set(0,0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setAlpha(240);
        canvas.drawRect(screen, paint);
    }
    */

    // todo refactor -- repeatedly calling new paint in thread cant be efficient. So do setupStartGameTExtPaint and then else statement above release the paint by setting null maybe??
    private void drawStartGameText(Canvas canvas) {
        Paint startGameTextPaint = new Paint();
        Typeface plain = Typeface.createFromAsset(Constants.CURRENT_CONTEXT.getAssets(), "undinaru.ttf");
        Typeface bold = Typeface.create(plain, Typeface.BOLD);
        startGameTextPaint.setTypeface(bold);
        startGameTextPaint.setColor(Color.WHITE);
        startGameTextPaint.setTextSize(100);
        startGameTextPaint.setTextAlign(Paint.Align.CENTER);
        int xPos = (canvas.getWidth() / 2);
        //int yPos = (int) ((canvas.getHeight() / 2) - ((startGameTextPaint.descent() + startGameTextPaint.ascent()) / 2)) ; center
        int yPos = (int) (7*canvas.getHeight()) /8;
        //((textPaint.descent() + textPaint.ascent()) / 2) is the distance from the baseline to the center.
        canvas.drawText("TOUCH TO START", xPos, yPos, startGameTextPaint);

    }

    @Override
    public void terminate() {

    }

    private void initializeGameMovement() {
        if (!mediator.gameMoving()) {
            mediator.startGameMotion();
            mediator.resetInitTime();
        }
    }

    @Override
    public void recieveTouch(MotionEvent event) {
        /*
        if (!mediator.gameMoving()) {
            mediator.startGameMotion();
            mediator.resetInitTime();
        }
        */
        //if (!gameOver) {
            //shapesManager.receiveTouch(event);
        //}
        if (mediator.isGameOver)
            return;
        // TODO touch can be for warning color if in warning color region
        // TODO if touch in shape creation region
        if (Constants.SHAPE_CLICK_AREA.contains((int) event.getX(), (int) event.getY())) {
            initializeGameMovement();
            shapesManager.recieveTouch(event);
        }
        else if (Constants.WARNING_COLOR_CLICK_AREA_LEFT.contains((int) event.getX(), (int) event.getY()))
            //Log.d("warningtoucharea", "receiveTouch: warning");
            warningColor.recieveTouch(event, "left");
        else if(Constants.WARNING_COLOR_CLICK_AREA_RIGHT.contains((int) event.getX(), (int) event.getY()))
            warningColor.recieveTouch(event, "right");

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
