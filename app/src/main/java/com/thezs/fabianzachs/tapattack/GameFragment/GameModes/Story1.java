package com.thezs.fabianzachs.tapattack.GameFragment.GameModes;

import android.view.View;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.GameFragment.Mediator;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeMovers.ShapeMover;

public class Story1 extends GameMode {

    private int shapeRadius = Constants.GAME_VIEW_WIDTH/8;
    private int shapeSpacing = Constants.GAME_VIEW_WIDTH/12;
    private int yStepSize = 2*shapeRadius + shapeSpacing;
    private ShapeMover shapeMover;


    public Story1(View view, Mediator mediator) {
        super(view, mediator, false, Constants.GAME_VIEW_WIDTH/8);


    }

    @Override
    void changeGameComponent() {

    }
}
