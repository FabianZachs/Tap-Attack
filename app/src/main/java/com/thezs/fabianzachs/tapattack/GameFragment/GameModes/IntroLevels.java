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

public class IntroLevels extends GameMode {

    private final int TOTAL_NUMBER_SHAPES;
    private static int shapeRadius = Constants.GAME_VIEW_WIDTH/8;
    private int shapeSpacing = Constants.GAME_VIEW_WIDTH/12;
    private int yStepSize = 2*shapeRadius + shapeSpacing;
    private ShapeMover shapeMover;


    public IntroLevels(View view, Mediator mediator, int TOTAL_NUMBER_SHAPES, String shape,
                       float secondsToMoveEntireScreen, boolean warningColorEnabled, float probabilityOfWarningColor) {

        super(view, mediator, warningColorEnabled, shapeRadius);
        this.TOTAL_NUMBER_SHAPES = TOTAL_NUMBER_SHAPES;
        gameOver = new GameOver(view, mediator);
        shapeMover = new ContinuousShapeMover(mediator,secondsToMoveEntireScreen);
        ShapePicker shapePicker = new ShapePicker(shape);
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

    public static IntroLevels buildIntroLevel(View view, Mediator mediator, int levelNumber) {
        switch (levelNumber) {

            case 1:
                return new IntroLevels(view, mediator, 10, "circle", 3500f, false,0);
            case 2:
                return new IntroLevels(view, mediator, 10, "square", 3500f, false,0);
            case 3:
                return new IntroLevels(view, mediator, 10, "arrow", 3500f, false,0);
            case 4:
                return new IntroLevels(view, mediator, 20, "circle", 2500f, false, 0);
            case 5:
                return new IntroLevels(view, mediator, 20, "square", 2500f, false,0);
            case 6:
                return new IntroLevels(view, mediator, 20, "arrow", 2500f, false,0);
            case 7:
                return new IntroLevels(view, mediator, 30, "circle",1500f, false,0);
            case 8:
                return new IntroLevels(view, mediator, 30, "square", 1800f, false,0);
            case 9:
                return new IntroLevels(view, mediator, 30, "arrow", 1500f, false,0);
            case 10:
                return new IntroLevels(view, mediator, 30, "circle", 2500f, true,.2f);
            case 11:
                return new IntroLevels(view, mediator, 30, "square", 2500f, true,.2f);
            case 12:
                return new IntroLevels(view, mediator, 30, "arrow", 2500f, true,.2f);
            case 13:
                return new IntroLevels(view, mediator, 50, "circle", 1000f,false,0f);
            case 14:
                return new IntroLevels(view, mediator, 100, "circle", 1000f,false,0f);
            case 15:
                return new IntroLevels(view, mediator, 50, "circle", 1200f,true,1f);
            case 16:
                return new IntroLevels(view, mediator, 50, "circle", 1500f,true,.2f);
            case 17:
                return new IntroLevels(view, mediator, 50, "circle", 1500f,true,.4f);
            case 18:
                return new IntroLevels(view, mediator, 50, "arrow", 1500f,false,0f);
            case 19:
                return new IntroLevels(view, mediator, 25, "arrow", 1700f,true,1f);
            case 20:
                return new IntroLevels(view, mediator, 25, "arrow", 2000f,true,.2f);
            case 21:
                return new IntroLevels(view, mediator, 25, "arrow", 2000f,true,.4f);
        }
        throw new IllegalArgumentException("unknown intro level");
    }
}
