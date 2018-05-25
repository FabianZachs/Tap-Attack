package com.thezs.fabianzachs.tapattack.Game.GameObjects;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
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

    CopyOnWriteArrayList<ShapeObject> shapes = new CopyOnWriteArrayList<>();
    private ArrayList<GraveObject> graveObjects = new ArrayList<>();
    private ShapesPopulator shapesPopulator;
    private GraveFactory graveFactory;
    private SharedPaint sharedPaint;
    private SharedRect sharedRect;



    public ShapesManager(CentralGameCommunication mediator, long initTime) {

        this.mediator = mediator;
        this.sharedPaint = new SharedPaint();
        this.sharedRect = new SharedRect();
        this.shapesPopulator = new ShapesPopulator(mediator,initTime,sharedPaint,sharedRect);
        this.graveFactory = new GraveFactory();
    }

    public void recieveTouch(MotionEvent event) {

        boolean shapeInteractment = false;

        for (ShapeObject shape : shapes) {
            if (shape.getBitmapHolder().contains((int) event.getX(), (int) event.getY())) {
                shape.recieveTouch(event);
                shapeInteractment = true;
            }
        }

         // if user ACTION_DOWNs and not onto a shape
        if (!shapeInteractment && event.getAction() == MotionEvent.ACTION_DOWN) {
            //mediator.changeProgressBarBy(-40);
            mediator.warningComponentShake();
            mediator.resetStreak();
        }
    }

    public void update() {

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
                //mediator.changeProgressBarBy(shape.getProgressBarAddition(), shape.getColor());
            }

            else if (shape.isTimedOut()) {
                shapes.remove(shape);
                freeResources(shape.getPaintObj(), shape.getBitmapHolder());
                mediator.resetStreak(); // TODO even for star????
            }

            else shape.update();
        }


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
