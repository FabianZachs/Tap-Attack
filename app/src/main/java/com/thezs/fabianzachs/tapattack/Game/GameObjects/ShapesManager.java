package com.thezs.fabianzachs.tapattack.Game.GameObjects;

import android.graphics.Canvas;

import com.thezs.fabianzachs.tapattack.Constants;

import java.util.ArrayList;

/**
 * Created by fabianzachs on 07/02/18.
 */

public class ShapesManager {

    private ArrayList<ShapeObject> shapes = new ArrayList<>();
    private int shape_spacing = Constants.SHAPE_SPACING;

    private long initTime;   // initialization of game scene
    private long startTime;  // time of update. startTime - initTime = time passed (good for difficulty)
                             // use sqrt function for difficulty

    private int score;

    public ShapesManager() {

    }

    public void update() {

    }

    public void draw(Canvas canvas) {

    }
}
