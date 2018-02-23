package com.thezs.fabianzachs.tapattack.Game.GameObjects;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;

import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.thezs.fabianzachs.tapattack.Animation.AnimationManager;
import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.BackgroundHandlers.BackgroundHandler;
import com.thezs.fabianzachs.tapattack.Game.GameModeScenes.ClassicGameScene;
import com.thezs.fabianzachs.tapattack.Game.GraveObjects.Grave;
import com.thezs.fabianzachs.tapattack.Game.LayoutHeadingHandlers.LayoutHeadings;
import com.thezs.fabianzachs.tapattack.R;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static com.thezs.fabianzachs.tapattack.Game.MainThread.canvas;

/**
 * Created by fabianzachs on 07/02/18.
 */

public class ShapesManager {

    private ArrayList<ShapeObject> shapes = new ArrayList<>();
    private ArrayList<Grave> graves = new ArrayList<>();
    private int shape_spacing = Constants.SHAPE_SPACING;

    private AnimationManager animationManager;
    private BackgroundHandler backgroundHandler;


    private long initTime;   // initialization of game scene
    private long startTime;  // time of update. startTime - initTime = time passed (good for difficulty)
                             // use sqrt function for difficulty

    private int score;


    public ShapesManager() {

        initializeAnimationsAndBackground();

        populateShapes();

    }

    private void populateShapes() {


        // testing: creating a circle shape:
        // TODO use creational design pattern for each shape in shapesmanager https://sourcemaking.com/design_patterns/creational_patterns
        Bitmap img = animationManager.getBitmap("circle", "blue", false);
        Bitmap clickedImg = animationManager.getBitmap("circle", "blue", true);

        for (int i = 100 ; i < 1600; i = i + 400) {
            for (int j = 0; j < 1000; j = j + 400) {
                Circle testCircle = new Circle(1000, "blue", new Point(i,j),clickedImg,img);
                shapes.add(testCircle);

            }
        }

    }

    // TODO do this in the store menu so we dont have to do this everytime we startup game
    private void initializeAnimationsAndBackground() {
        SharedPreferences prefs = Constants.CURRENT_CONTEXT.getSharedPreferences("playerPrefs", MODE_PRIVATE);

        // change error: no theme to basic colorscheme
        String theme = prefs.getString("theme", "error: no theme");

        this.animationManager = new AnimationManager(theme);

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
        //if (!shapeInteractment && event.getAction() == MotionEvent.ACTION_DOWN)
            //ClassicGameScene.gameOver = true;
    }

    public void update() {

        for (ShapeObject shape : shapes) {

            // if deleted by click
            if (shape.getLives() == 0) {
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



    }

    public void draw(Canvas canvas) {

        // setting background
        Bitmap background = backgroundHandler.getBackgroundBitmap("blue");

        canvas.drawBitmap(background, null, new Rect(0,0,Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT), null);


        for (ShapeObject shape : shapes) {
            shape.draw(canvas);
        }

        for (Grave grave : graves)
            grave.draw(canvas);

    }

}
