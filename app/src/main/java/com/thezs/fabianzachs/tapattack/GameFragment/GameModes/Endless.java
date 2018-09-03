package com.thezs.fabianzachs.tapattack.GameFragment.GameModes;

import android.graphics.Color;
import android.text.Spanned;
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

    public Endless(View view, Mediator mediator) {
        super(view, mediator, true, Constants.GAME_VIEW_WIDTH/10);


        gameOver = new GameOver(mediator);
        ShapeMover shapeMover = new ContinuousShapeMover(mediator,3500f);
        //ShapeMover shapeMover = new DiscreteShapeMover(yStepSize, Constants.GAME_VIEW_HEIGHT - yStepSize);
        ShapePicker shapePicker = new ShapePicker("arrow");
        ColorPicker colorPicker = new ColorPicker(mediator, (new ThemeManager()).getColors(), 0.0f);


        shapesManager = new ShapesManager(mediator, gameOver, shapeMover, shapePicker, colorPicker,
                shapeRadius, shapeSpacing);

    }
}
