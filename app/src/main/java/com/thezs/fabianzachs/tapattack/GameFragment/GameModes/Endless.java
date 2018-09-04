package com.thezs.fabianzachs.tapattack.GameFragment.GameModes;

import android.graphics.Color;
import android.text.Spanned;
import android.util.Log;
import android.view.View;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.GameFragment.ColorPicker;
import com.thezs.fabianzachs.tapattack.GameFragment.GameOverClasses.GameOver;
import com.thezs.fabianzachs.tapattack.GameFragment.Mediator;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeMovers.ContinuousShapeMover;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeMovers.DiscreteShapeMover;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeMovers.ShapeMover;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapePicker;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapesManager;
import com.thezs.fabianzachs.tapattack.GameFragment.ThemeManager;

import java.util.ArrayList;

public class Endless extends GameMode {

    private int shapeRadius = Constants.GAME_VIEW_WIDTH/10;
    private int shapeSpacing = 20;
    private int yStepSize = 2*shapeRadius + shapeSpacing;
    private ShapeMover shapeMover;

    public Endless(View view, Mediator mediator) {
        super(view, mediator, true, Constants.GAME_VIEW_WIDTH/10);


        gameOver = new GameOver(view, mediator);
        //shapeMover = new ContinuousShapeMover(mediator,3500f);
        //shapeMover = new ContinuousShapeMover(mediator,5500f,1000f,3000);
        ShapeMover shapeMover = new DiscreteShapeMover(mediator, yStepSize);
        ShapePicker shapePicker = new ShapePicker("circle");
        ColorPicker colorPicker = new ColorPicker(mediator, (new ThemeManager()).getColors(), 1.0f);


        shapesManager = new ShapesManager(mediator, gameOver, shapeMover, shapePicker, colorPicker,
                shapeRadius, shapeSpacing);

    }

    protected void changeGameComponent() {
        switch (shapesManager.getNumberOfDestroyedShapes()) {
            case 10:

                break;
            case 1:

        }
    }
}
