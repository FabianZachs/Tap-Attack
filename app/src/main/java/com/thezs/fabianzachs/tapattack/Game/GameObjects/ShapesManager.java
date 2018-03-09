package com.thezs.fabianzachs.tapattack.Game.GameObjects;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.Circle;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.Cross;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.ShapeObject;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.ProgressBar;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.Score;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.Streak;
import com.thezs.fabianzachs.tapattack.Game.GraveObjects.GraveFactory;
import com.thezs.fabianzachs.tapattack.Game.GraveObjects.GraveObject;
import com.thezs.fabianzachs.tapattack.Game.Mediator.CentralGameCommunication;
import com.thezs.fabianzachs.tapattack.Game.SharedResources.SharedPaint;
import com.thezs.fabianzachs.tapattack.Game.SharedResources.SharedRect;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by fabianzachs on 07/02/18.
 */

public class ShapesManager {

    private CentralGameCommunication mediator;

    //private ArrayList<ShapeObject> shapes = new ArrayList<>();
    CopyOnWriteArrayList<ShapeObject> shapes = new CopyOnWriteArrayList<>();

    private ArrayList<GraveObject> graveObjects = new ArrayList<>();
    private GraveFactory graveFactory;
    private SharedPaint sharedPaint;
    private SharedRect sharedRect;

    private ShapesPopulator shapesPopulator;
    //private BackgroundHandler backgroundHandler;

    //private Score scoreObserver;
    //private Streak streakObserver;
    //private ProgressBar progressBarObserver;


    private long initTime;   // initialization of game scene
    private long startTime;  // time of update. startTime - initTime = time passed (good for difficulty)
                             // use sqrt function for difficulty

    //private int score;
    //private int streak;


    public ShapesManager(CentralGameCommunication mediator) {

        this.mediator = mediator;
        this.sharedPaint = new SharedPaint();
        this.sharedRect = new SharedRect();
        this.initTime = System.currentTimeMillis();
        this.shapesPopulator = new ShapesPopulator(mediator,initTime,sharedPaint,sharedRect);
        this.graveFactory = new GraveFactory();



        //this.score = 0;

        //initializeAnimationsAndBackground();

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
/*
    // TODO do this in the store menu so we dont have to do this everytime we startup game
    private void initializeAnimationsAndBackground() {
        SharedPreferences prefs = Constants.CURRENT_CONTEXT.getSharedPreferences("playerPrefs", MODE_PRIVATE);

        // change error: no theme to basic colorscheme
        String theme = prefs.getString("theme", "error: no theme");

        this.backgroundHandler = new BackgroundHandler(theme);

    }
    */

    public void recieveTouch(MotionEvent event) {
        boolean shapeInteractment = false; // used to see if user interacted with shape.



        for (ShapeObject shape : shapes) {
            if (shape.getBitmapHolder().contains((int) event.getX(), (int) event.getY())) {
                // TODO
                /*
                   if (shape.color.equals(WarningComponent.currentColor)
                        color.reduceLives(shape.getLives);
                        progressBarObserver.changeProgressBy(-50);

                   else
                 */
                shape.recieveTouch(event);
                shapeInteractment = true;
            }
        }

         // if user ACTION_DOWNs and not onto a shape
        if (!shapeInteractment && event.getAction() == MotionEvent.ACTION_DOWN) {
            //progressBarObserver.changeProgressBy(-40); // TODO make this relative to time
            mediator.changeProgressBarBy(-40);
            //streakObserver.resetStreak();
            mediator.resetStreak();
        }
            //SceneManager.setGameOver(true);
    }

    public void update() {

        for (ShapeObject shape : shapes) {

            // if deleted by click
            if (shape.getLives() <= 0) {

                if (shape.getGravable())
                    graveObjects.add(graveFactory.buildGrave(shape));
                else {
                    sharedPaint.freePaint(shape.getPaintObj());
                    sharedRect.freeRect(shape.getBitmapHolder());
                }
                // TODO make a seperate method which handles everything when a shape is removed (progress bar, streak, score)
                shapes.remove(shape);
                //scoreObserver.incScore(shape.getPoints());
                mediator.incScore(shape.getPoints(), shape.getColor());
                //streakObserver.incStreak(1);
                mediator.incStreak(1, shape.getColor());
                //progressBarObserver.changeProgressBy(shape.getProgressBarAddition());
                mediator.changeProgressBarBy(shape.getProgressBarAddition(), shape.getColor());
            }

            else if (shape.isTimedOut()) {
                shapes.remove(shape);
                sharedPaint.freePaint(shape.getPaintObj());
                sharedRect.freeRect(shape.getBitmapHolder());
                //streakObserver.resetStreak();
                mediator.resetStreak();
                //streak = 0;
            }

                // TODO add one shape.getPoints to the score -- and streak (add this when we find out lives = 0
            else shape.update();
        }


        for (GraveObject graveObject : graveObjects) {
            if (graveObject.graveDestroyed()) {
                graveObjects.remove(graveObject);
                sharedPaint.freePaint(graveObject.getPaint());
                sharedRect.freeRect(graveObject.getBitmapHolder());
            }
            else
                graveObject.update();
        }
        shapes = shapesPopulator.update(shapes);



    }

    public void draw(Canvas canvas) {

        // setting background
        //Bitmap background = backgroundHandler.getBackgroundBitmap("blue");

        // commented out for now because score is behind it
        //canvas.drawBitmap(background, null, new Rect(0,0,Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT), null);


        for (ShapeObject shape : shapes) {
            shape.draw(canvas);
        }


        for (GraveObject graveObject : graveObjects)
            graveObject.draw(canvas);

    }

    /*
    public int getScore() {
        return score;
    }*/
    /*
    public int getStreak() {
        return streak;
    }*/


/*
    public void attachScoreObserver(Score scoreObserver) {
        //this.scoreObserver = scoreObserver;
        this.shapesPopulator.attachScoreObserver(scoreObserver);
    }

    public void attachStreakObserver(Streak streakObserver) {
        //this.streakObserver = streakObserver;
        this.shapesPopulator.attachStreakObserver(streakObserver);
    }

    public void attachProgressBarObserver(ProgressBar progressBarObserver) {
        //this.progressBarObserver = progressBarObserver;
        this.shapesPopulator.attachProgressBarObserver(progressBarObserver);
    }
*/
}
