package com.thezs.fabianzachs.tapattack.GameFragment.GameModes;

import android.view.View;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.GameFragment.ColorPicker;
import com.thezs.fabianzachs.tapattack.GameFragment.GameOverClasses.GameOver;
import com.thezs.fabianzachs.tapattack.GameFragment.Mediator;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeMovers.ContinuousShapeMover;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeMovers.ShapeMover;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapePicker;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapesManager;
import com.thezs.fabianzachs.tapattack.GameFragment.ThemeManager;

public class Endless extends GameMode {

    private int shapeRadius = Constants.GAME_VIEW_WIDTH/8;
    private int shapeSpacing = Constants.GAME_VIEW_WIDTH/12;
    private int yStepSize = 2*shapeRadius + shapeSpacing;
    private ShapeMover shapeMover;

    public Endless(View view, Mediator mediator) {
        super(view, mediator, true, Constants.GAME_VIEW_WIDTH/10);


        gameOver = new GameOver(view, mediator);
        shapeMover = new ContinuousShapeMover(mediator,1000f);
        //shapeMover = new ContinuousShapeMover(mediator,3500f,1000f,9000);
        //ShapeMover shapeMover = new DiscreteShapeMover(mediator, yStepSize);
        ShapePicker shapePicker = new ShapePicker(1,1,1,1,1);
        ColorPicker colorPicker = new ColorPicker(mediator, (new ThemeManager()).getColors(), .1f);


        shapesManager = new ShapesManager(mediator, gameOver, shapeMover, shapePicker, colorPicker,
                shapeRadius, shapeSpacing, 1000);
        // todo fix TOTAL_NUMBER.. to have it not required

    }

    protected void changeGameComponent() {
    }

    @Override
    void checkLevelPassed() {

    }
}
