package com.thezs.fabianzachs.tapattack.Game.GameObjects;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.shapes.Shape;
import android.util.Log;
import android.view.MotionEvent;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.Circle;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.Cross;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.ShapeObject;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.Star;
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

    CopyOnWriteArrayList<ShapeObject> shapes = new CopyOnWriteArrayList<>();
    private ArrayList<GraveObject> graveObjects = new ArrayList<>();
    private ShapesPopulator shapesPopulator;
    private GraveFactory graveFactory;
    private SharedPaint sharedPaint;
    private SharedRect sharedRect;
    private ShapeMover shapeMover;
    private int EXTRA_PIXELS_CLICK_AREA = Constants.SCREEN_HEIGHT/35;



    public ShapesManager(CentralGameCommunication mediator, long initTime) {

        this.mediator = mediator;
        this.sharedPaint = new SharedPaint();
        this.sharedRect = new SharedRect();
        this.shapesPopulator = new ShapesPopulator(mediator,initTime,sharedPaint,sharedRect);
        this.graveFactory = new GraveFactory();
        this.shapeMover = new ShapeMover(mediator);
        mediator.addObject(shapeMover);
    }

    private Rect shapeClickArea(ShapeObject shape) {
        Rect shapeClickArea = new Rect(shape.getBitmapHolder());
        shapeClickArea.left = shapeClickArea.left - EXTRA_PIXELS_CLICK_AREA;
        shapeClickArea.right = shapeClickArea.right + EXTRA_PIXELS_CLICK_AREA;
        shapeClickArea.top = shapeClickArea.top - EXTRA_PIXELS_CLICK_AREA;
        shapeClickArea.bottom = shapeClickArea.bottom + EXTRA_PIXELS_CLICK_AREA;

        return shapeClickArea;
    }

    public void recieveTouch(MotionEvent event) {

        boolean shapeInteractment = false;

        for (ShapeObject shape : shapes) {
            /*
            if (shape.getBitmapHolder().contains((int) event.getX(), (int) event.getY())) {
                shape.recieveTouch(event);
                shapeInteractment = true;
            }*/
            if (shapeClickArea(shape).contains((int) event.getX(), (int) event.getY())) {
                shape.recieveTouch(event);
                shapeInteractment = true;
            }
        }

         // if user ACTION_DOWNs and not onto a shape
        if (!shapeInteractment && event.getAction() == MotionEvent.ACTION_DOWN) {
            //mediator.changeProgressBarBy(-40);
            mediator.warningComponentShake();
            mediator.resetStreak();
            mediator.setGameOver(); //todo for setting up gameOver we have that background touch triggers gameover
        }
    }

    public void update() {
        //Log.d("debugpaint", "update: shapes size" + shapes.size());
        //Log.d("debugpaint", "update: grave seize" + graveObjects.size());

        for (ShapeObject shape : shapes) {

            if (shape.getLives() <= 0) {

                if (shape.getGravable())
                    graveObjects.add(graveFactory.buildGrave(shape));
                else {
                    freeResources(shape.getPaintObj(), shape.getBitmapHolder());
                }
                shapes.remove(shape);
                mediator.incScore(shape.getPoints(), shape.getColor());
                mediator.incStreak(1, shape.getColor());
                // todo optimize:
                if (mediator.getStrWarningColor().equals(shape.getColor()))
                    mediator.setGameOver();
                //mediator.changeProgressBarBy(shape.getProgressBarAddition(), shape.getColor());
            }

            // TODO wont happen anymore
            else if (shape.isTimedOut()) {
                shapes.remove(shape);
                freeResources(shape.getPaintObj(), shape.getBitmapHolder());
                mediator.resetStreak(); // TODO even for star????
            }


            // TODO this will end game
            //else if (shape.getBitmapHolder().top > Constants.SCREEN_HEIGHT && (shape.getClass() != Star.class && shape.getClass() != Cross.class)) {
            else if (shape.leftScreen() && !shapeIsStarOrCross(shape)) {
                freeResources(shape.getPaintObj(), shape.getBitmapHolder());
                shapes.remove(shape);
                //mediator.setGameOver();
            }

            else if (shape.leftScreen() && shapeIsStarOrCross(shape)) {
                freeResources(shape.getPaintObj(), shape.getBitmapHolder());
                shapes.remove(shape);
            }


            else shape.update();

        }
        shapeMover.update(shapes);


        for (GraveObject graveObject : graveObjects) {
            if (graveObject.graveDestroyed()) {
                graveObjects.remove(graveObject);
                freeResources(graveObject.getPaint(), graveObject.getBitmapHolder());
            }
            else
                graveObject.update();
        }

        shapes = shapesPopulator.update(shapes);
    }

    private boolean shapeIsStarOrCross(ShapeObject shape) {
        return shape.getClass() == Star.class || shape.getClass() == Cross.class;
    }

    public void draw(Canvas canvas) {

        for (ShapeObject shape : shapes)
            shape.draw(canvas);

        for (GraveObject graveObject : graveObjects)
            graveObject.draw(canvas);
    }

    private void freeResources(Paint paintObj, Rect bitmapHolder) {
        sharedPaint.freePaint(paintObj);
        sharedRect.freeRect(bitmapHolder);
    }
}
