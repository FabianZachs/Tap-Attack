package com.thezs.fabianzachs.tapattack.Game.GameObjects;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;

import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.thezs.fabianzachs.tapattack.Animation.Animation;
import com.thezs.fabianzachs.tapattack.Animation.AnimationManager;
import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.GameModeScenes.ClassicGameScene;
import com.thezs.fabianzachs.tapattack.R;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by fabianzachs on 07/02/18.
 */

public class ShapesManager {

    private ArrayList<ShapeObject> shapes = new ArrayList<>();
    private int shape_spacing = Constants.SHAPE_SPACING;

    private AnimationManager animationManager;

    private long initTime;   // initialization of game scene
    private long startTime;  // time of update. startTime - initTime = time passed (good for difficulty)
                             // use sqrt function for difficulty

    private int score;

    private float shapeProportionOfView = .2f;

    public ShapesManager() {

        // TODO code to initialize all needed bitmaps into shape manager for selected color scheme
        initializeAnimations();

        populateShapes();

    }

    private void populateShapes() {


        // testing: creating a circle shape:
        // TODO use creational design pattern for each shape in shapesmanager https://sourcemaking.com/design_patterns/creational_patterns
        Bitmap img = animationManager.getBitmap("circle", "blue", false);
        Bitmap clickedImg = animationManager.getBitmap("circle", "blue", true);
        Circle testCircle = new Circle(5, "blue", new Point(100,100),clickedImg,img);
        shapes.add(testCircle);
    }

    private void initializeAnimations() {
        SharedPreferences prefs = Constants.CURRENT_CONTEXT.getSharedPreferences("playerPrefs", MODE_PRIVATE);

        String theme = prefs.getString("theme", "error: no theme");

        this.animationManager = new AnimationManager(theme);

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
        //if (!shapeInteractment && event.getAction() == MotionEvent.ACTION_DOWN)
            //ClassicGameScene.gameOver = true;
    }

    public void update() {

        for (ShapeObject shape : shapes) {
            if (shape.getLives() <= 0)
                shapes.remove(shape);
                // TODO add one shape.getPoints to the score -- and streak
            else shape.update();
        }
    }

    public void draw(Canvas canvas) {
        for (ShapeObject shape : shapes) {
            shape.draw(canvas);
        }

    }

}
