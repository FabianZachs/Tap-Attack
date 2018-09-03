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
import com.thezs.fabianzachs.tapattack.GameFragment.WarningColorComponent;
import com.thezs.fabianzachs.tapattack.R;

import java.util.ArrayList;

public abstract class GameMode {

    protected boolean warningColorEnabled;
    private Mediator mediator;
    private ShapesManager shapesManager;
    private StartGame startGame = new StartGame();
    private GameOver gameOver = new GameOver();


    private ArrayList<ShapeObject> shapes;
    private SoundEffectsManager soundEffectsManager;


    public GameMode(View view, boolean warningColorEnabled, ShapeMover shapeMover,
                    ShapePicker shapePicker, ColorPicker colorPicker, int shapeRadius, int shapeSpacing) {
        this.warningColorEnabled = warningColorEnabled;
        this.mediator = new Mediator();
        this.shapesManager = new ShapesManager(mediator, gameOver,shapeMover, shapePicker, colorPicker,
                shapeRadius, shapeSpacing);
        if (warningColorEnabled) {
            new WarningColorComponent(view,themeManager.getColors());
            Constants.SHAPE_CREATION_AREA = new Rect(
                    view.findViewById(R.id.warning_color_change_button_left).getWidth() + shapeRadius,
                    0,Constants.GAME_VIEW_WIDTH - view.findViewById(R.id.warning_color_change_button_right).getWidth() - shapeRadius,
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
            gameOver.drawGameOver(canvas, shapesManager.getShapes(), shapesManager.getShapeToBlink());

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

    private void changeGameComponent() {
        switch (shapesManager.getNumberOfDestroyedShapes()) {
            case 0:
                // todo put specific gamemode code
                break;
            case 1:

        }
    }

}
