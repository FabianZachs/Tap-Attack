package com.thezs.fabianzachs.tapattack.GameFragment.GameModes;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import com.thezs.fabianzachs.tapattack.GameFragment.ColorPicker;
import com.thezs.fabianzachs.tapattack.GameFragment.Mediator;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeMovers.ShapeMover;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes.ShapeObject;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapePicker;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapesManager;
import com.thezs.fabianzachs.tapattack.GameFragment.SoundEffectsManager;

import java.util.ArrayList;

public abstract class GameMode {

    protected boolean warningColorEnabled;
    private Mediator mediator;
    private ShapesManager shapesManager;


    private ArrayList<ShapeObject> shapes;
    private SoundEffectsManager soundEffectsManager;


    public GameMode(View view, boolean warningColorEnabled, ShapeMover shapeMover,
                    ShapePicker shapePicker, ColorPicker colorPicker, int shapeRadius, int shapeSpacing) {
        this.warningColorEnabled = warningColorEnabled;
        this.mediator = new Mediator();
        this.shapesManager = new ShapesManager(shapeMover, shapePicker, colorPicker,
                shapeRadius, shapeSpacing);
        /*
        if (warningColorEnabled)
            new WarningColorComponent(view,themeManager.getColors());
        */
        // todo set shapeClick/Creattion area depending on whether warning color enabled


    }



    public void update() {
        changeGameComponent();
        shapesManager.update();
    }


    public void draw(Canvas canvas) {
        shapesManager.draw(canvas);

        // todo if not game over, draw shapesmanager
        // todo if gameover draw gameOver.drawGameOver(shapesMaanger.getShapes, shapesManger.getShapeToBlink)
        // todo if not yet started, draw StartGame class

    }

    public void receiveTouch(MotionEvent event) {
        // todo if touch is in touch section
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
