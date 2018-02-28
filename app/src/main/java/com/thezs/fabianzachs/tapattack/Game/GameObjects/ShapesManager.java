package com.thezs.fabianzachs.tapattack.Game.GameObjects;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.thezs.fabianzachs.tapattack.Animation.AnimationManager;
import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.BackgroundHandlers.BackgroundHandler;
import com.thezs.fabianzachs.tapattack.Game.GameModeScenes.ClassicGameScene;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.Circle;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.ShapeObject;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.Square;
import com.thezs.fabianzachs.tapattack.Game.GraveObjects.Grave;
import com.thezs.fabianzachs.tapattack.Game.SceneManager;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by fabianzachs on 07/02/18.
 */

public class ShapesManager {

    //private ArrayList<ShapeObject> shapes = new ArrayList<>();
    CopyOnWriteArrayList<ShapeObject> shapes = new CopyOnWriteArrayList<>();

    private ArrayList<Grave> graves = new ArrayList<>();

    private ShapesPopulator shapesPopulator;
    private BackgroundHandler backgroundHandler;


    private long initTime;   // initialization of game scene
    private long startTime;  // time of update. startTime - initTime = time passed (good for difficulty)
                             // use sqrt function for difficulty

    private int score;


    public ShapesManager() {

        this.initTime = System.currentTimeMillis();
        this.shapesPopulator = new ShapesPopulator(initTime);

        initializeAnimationsAndBackground();

        //populateShapes();

    }
/*
    private void populateShapes() {

        for (int i = 100 ; i < 1600; i = i + 400) {
            for (int j = 100; j < 1000; j = j + 400) {
                //Square testCircle = new Square(8, "blue", new Point(i,j),clickedImg,img);
                shapes.add(shapeBuilder.buildCross("blue", new Point(i,j)));


            }
        }

    }
*/
    // TODO do this in the store menu so we dont have to do this everytime we startup game
    private void initializeAnimationsAndBackground() {
        SharedPreferences prefs = Constants.CURRENT_CONTEXT.getSharedPreferences("playerPrefs", MODE_PRIVATE);

        // change error: no theme to basic colorscheme
        String theme = prefs.getString("theme", "error: no theme");

        this.backgroundHandler = new BackgroundHandler(theme);

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
            SceneManager.setGameOver(true);
    }

    public void update() {

        for (ShapeObject shape : shapes) {

            // if deleted by click
            if (shape.getLives() == 0) {
                // TODO maybe better to add method .isGraveable() to see whether shape leaves grave
                // grave for square does not seem smooth and not needed for corss
                if (shape instanceof Circle)
                    graves.add(new Grave(shape.getCenterLocation(), shape.getBitmapHolder(), shape.getShapeClickImg()));
                // TODO make a seperate method which handles everything when a shape is removed (progress bar, streak, score)
                shapes.remove(shape);
            }

            else if (shape.isTimedOut())
                shapes.remove(shape);

                // TODO add one shape.getPoints to the score -- and streak (add this when we find out lives = 0
            else shape.update();
        }

        for (Grave grave : graves) {
            if (grave.graveDestroyed())
                graves.remove(grave);
        }
        shapes = shapesPopulator.update(shapes);



    }

    public void draw(Canvas canvas) {

        // setting background
        Bitmap background = backgroundHandler.getBackgroundBitmap("blue");

        // commented out for now because score is behind it
        //canvas.drawBitmap(background, null, new Rect(0,0,Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT), null);


        for (ShapeObject shape : shapes) {
            shape.draw(canvas);
        }

        for (Grave grave : graves)
            grave.draw(canvas);

    }

}
