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

public class Stage1Levels extends GameMode {

    private final int TOTAL_NUMBER_SHAPES;
    private static int shapeRadius = Constants.GAME_VIEW_WIDTH/8;
    private int shapeSpacing = Constants.GAME_VIEW_WIDTH/12;
    private int yStepSize = 2*shapeRadius + shapeSpacing;
    private ShapeMover shapeMover;


    public Stage1Levels(View view, Mediator mediator, int TOTAL_NUMBER_SHAPES,
                        int probCircle, int probSquare, int probArrow, int probCross, int probStar,
                        float secondsToMoveEntireScreen, boolean warningColorEnabled, float probabilityOfWarningColor) {

        super(view, mediator, warningColorEnabled, shapeRadius);
        this.TOTAL_NUMBER_SHAPES = TOTAL_NUMBER_SHAPES;
        gameOver = new GameOver(view, mediator);
        shapeMover = new ContinuousShapeMover(mediator,secondsToMoveEntireScreen);
        ShapePicker shapePicker = new ShapePicker(probCircle,probSquare,probArrow,probCross,probStar);
        ColorPicker colorPicker = new ColorPicker(mediator, (new ThemeManager()).getColors(),probabilityOfWarningColor);
        shapesManager = new ShapesManager(mediator, gameOver, shapeMover, shapePicker, colorPicker,
                shapeRadius, shapeSpacing, TOTAL_NUMBER_SHAPES);

    }

    @Override
    void changeGameComponent() {

    }

    @Override
    void checkLevelPassed() {
        // todo use numberofKilledShapes instead when we add shape kill countdown
        if ((shapesManager.getCurrentNumberOfCreatedShapes() == TOTAL_NUMBER_SHAPES)
                && (shapesManager.getShapes().size() == 0))
            mediator.levelComplete();
    }

    public static Stage1Levels buildStage1Level(View view, Mediator mediator, int levelNumber) {
        switch (levelNumber) {
            case 22:
                return new Stage1Levels(view, mediator, 50, 4,2,3,1,1, 2000,false,0f);
            case 23:
                return new Stage1Levels(view, mediator, 50, 4,2,3,1,1, 1500,false,0f);
            case 24:
                return new Stage1Levels(view, mediator, 50, 4,2,4,1,1, 1300,false,0f);
            case 25:
                return new Stage1Levels(view, mediator, 30, 4,1,3,1,1, 2500,true,1f);
            case 26:
                return new Stage1Levels(view, mediator, 30, 4,2,3,1,1, 2500,true,.5f);
            case 27:
                return new Stage1Levels(view, mediator, 30, 4,2,3,1,1, 2500,true,.2f);
        }
        throw new IllegalArgumentException("unknown intro level");
    }
}
