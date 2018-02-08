package com.thezs.fabianzachs.tapattack.Game.GameObjects;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.GameModeScenes.ClassicGameScene;

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

    public void recieveTouch(MotionEvent event) {
        boolean shapeInteractment = false; // used to see if user interacted with shape.


        for (ShapeObject shape : shapes) {
            if (shape.getBitmapHolder().contains((int) event.getX(), (int) event.getY())) {
                shape.recieveTouch(event);
                shapeInteractment = true;
            }
        }

         // if user ACTION_DOWNs and not onto a shape
        if (!shapeInteractment && event.getAction() == MotionEvent.ACTION_DOWN)
            ClassicGameScene.gameOver = true;
    }

    public void update() {
        /*
        for shape in shapes {
            if shape.life == 0
                remove it
            else shape.update()
         */

    }

    public void draw(Canvas canvas) {

    }
}
