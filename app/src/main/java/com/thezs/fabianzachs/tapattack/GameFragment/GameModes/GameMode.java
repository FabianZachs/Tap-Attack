package com.thezs.fabianzachs.tapattack.GameFragment.GameModes;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.GameFragment.ColorPicker;
import com.thezs.fabianzachs.tapattack.GameFragment.GameOverClasses.GameOver;
import com.thezs.fabianzachs.tapattack.GameFragment.Mediator;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeMovers.ShapeMover;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes.ShapeObject;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapePicker;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapesManager;
import com.thezs.fabianzachs.tapattack.GameFragment.SoundEffectsManager;
import com.thezs.fabianzachs.tapattack.GameFragment.StartGame;
import com.thezs.fabianzachs.tapattack.GameFragment.ThemeManager;
import com.thezs.fabianzachs.tapattack.GameFragment.WarningColorComponent;
import com.thezs.fabianzachs.tapattack.R;

import java.util.ArrayList;

public abstract class GameMode {

    protected boolean warningColorEnabled;
    Mediator mediator;
    ShapesManager shapesManager;
    private StartGame startGame = new StartGame();
    GameOver gameOver;


    public GameMode(View view, Mediator mediator, boolean warningColorEnabled, int shapeRadius) {
        this.mediator = mediator;
        this.warningColorEnabled = warningColorEnabled;





        if (warningColorEnabled) {
            WarningColorComponent warningColorComponent = new WarningColorComponent(view,(new ThemeManager()).getColors());
            mediator.addObject(warningColorComponent);
            Constants.SHAPE_CREATION_AREA = new Rect(
                    Constants.GAME_VIEW_WIDTH/15+ shapeRadius,
                    0,Constants.GAME_VIEW_WIDTH - Constants.GAME_VIEW_WIDTH/15 - shapeRadius,
                    0);

        }
        else
            Constants.SHAPE_CREATION_AREA = new Rect(shapeRadius,0,Constants.GAME_VIEW_WIDTH - shapeRadius, 0);

    }



    public void update() {
        if (!mediator.isGameOver()) {
            changeGameComponent();
            shapesManager.update();
        }
    }


    public void draw(Canvas canvas) {
        if (!mediator.isGameOver()) {
            shapesManager.draw(canvas);
        }
        else
            gameOver.drawGameOver(canvas, shapesManager.getShapes());

        if (!mediator.hasGameStarted()) {
            startGame.draw(canvas);
        }

    }

    public void receiveTouch(MotionEvent event) {
        if (mediator.isGameOver())
            return;

        if (!mediator.hasGameStarted())
            mediator.startGame();

        shapesManager.receiveTouch(event);

    }

    abstract void changeGameComponent();


}
