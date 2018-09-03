package com.thezs.fabianzachs.tapattack.GameFragment;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.GameFragment.GameOverClasses.GameOver;
import com.thezs.fabianzachs.tapattack.GameFragment.RecycledResources.RecycledPaint;
import com.thezs.fabianzachs.tapattack.GameFragment.RecycledResources.RecycledRect;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeMovers.ShapeMover;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.GraveObjects.GraveObject;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.GraveObjects.GraveShapeBuilder;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes.ShapeObject;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class ShapesManager {

    private CopyOnWriteArrayList<ShapeObject> shapes = new CopyOnWriteArrayList<>();
    private ArrayList<GraveObject> graves = new ArrayList<>();
    private ShapesPopulator shapesPopulator;
    private ShapeMover shapeMover;
    private Mediator mediator;

    private ShapeObject shapeToBlink;
    private GameOver gameOver;
    private RecycledPaint recycledPaint = new RecycledPaint();
    private RecycledRect recycledRect = new RecycledRect();
    private int numberOfDesttroyedShapes= 0;


    public ShapesManager(Mediator mediator, GameOver gameOver, ShapeMover shapeMover, ShapePicker shapePicker, ColorPicker colorPicker,
                         int shapeRadius, int shapeSpacing) {

        this.gameOver = gameOver;
        this.mediator = mediator;

        this.shapesPopulator = new ShapesPopulator(shapes, recycledRect, recycledPaint, colorPicker,
                shapePicker, shapeRadius, shapeSpacing);

        this.shapeMover = shapeMover;
    }



    public void update() {
        if (gameOver.checkForGameOver(shapes))
            return;

        handleShapesUpdate();
        handleGravesUpdate();

        if (mediator.hasGameStarted())
            shapeMover.update(shapes);

        shapesPopulator.update(shapes);


        // todo in start of testing note how many shape objects and how many paints/rects are present
    }

    public void receiveTouch(MotionEvent event) {
        boolean shapeInteractment = false;

        for (ShapeObject shape : shapes) {
            if (shapeTouchArea(event, shape)) {
                shape.recieveTouch(event);
                shapeInteractment = true;
            }
        }

        if (!shapeInteractment && event.getAction() == MotionEvent.ACTION_DOWN) {
            // todo shapeToBlink = getFurthestDownShape
            mediator.setGameOver(); // todo pass in reason
        }

    }

    public void draw(Canvas canvas) {
        for (ShapeObject shape : shapes)
            shape.draw(canvas);
        for (GraveObject grave : graves)
            grave.draw(canvas);
    }

    public CopyOnWriteArrayList<ShapeObject> getShapes() {
        return shapes;
    }

    public ShapeObject getShapeToBlink() {
        return shapeToBlink;
    }

    public int getNumberOfDestroyedShapes() {
        return numberOfDesttroyedShapes;
    }

    private boolean isShapeDead(ShapeObject shape) {
        return shape.getLives() <= 0;
    }

    private void handleGraveAndResources(ShapeObject shape) {
        if (shape.isGraveable())
            graves.add(GraveShapeBuilder.buildGrave(shape));
        else
            freeResources(shape.getPaint(), shape.getBitmapHolder());
    }

    private boolean shapeLeftScreen(ShapeObject shape) {
        return shape.getBitmapHolder().top > Constants.GAME_VIEW_HEIGHT;

    }

    private void handleShapesUpdate() {
        for (ShapeObject shape : shapes) {
            if (isShapeDead(shape)) {
                numberOfDesttroyedShapes++;
                // todo shape.playDeathSound();
                handleGraveAndResources(shape);
                shapes.remove(shape);
                // todo increase score if there is one (mediator should haave score interface)
            }
            else if (shapeLeftScreen(shape)) {
                freeResources(shape.getPaint(), shape.getBitmapHolder());
                shapes.remove(shape);
            }

            else shape.update();
        }
    }

    private void handleGravesUpdate() {
        ArrayList<GraveObject> gravesToRemove = new ArrayList<>();
        for (GraveObject grave : graves) {
            if (grave.isGraveDestroyed()) {
                gravesToRemove.add(grave);
                freeResources(grave.getGravePaint(), grave.getGraveHolder());
            }
            else
                grave.update();
        }
        graves.removeAll(gravesToRemove);
    }

    private boolean shapeTouchArea(MotionEvent event, ShapeObject shape) {
        int EXTRA_PIXEL_TOUCH_AREA = Constants.GAME_VIEW_WIDTH/10;
        Rect shapeClickArea = new Rect(shape.getBitmapHolder());
        shapeClickArea.left = shapeClickArea.left - EXTRA_PIXEL_TOUCH_AREA;
        shapeClickArea.right = shapeClickArea.right + EXTRA_PIXEL_TOUCH_AREA;
        shapeClickArea.top = shapeClickArea.top - EXTRA_PIXEL_TOUCH_AREA;
        shapeClickArea.bottom = shapeClickArea.bottom + EXTRA_PIXEL_TOUCH_AREA;

        return shapeClickArea.contains((int) event.getX(), (int) event.getY());
    }

    private void freeResources(Paint paint, Rect bitmapHolder) {
        recycledPaint.freePaint(paint);
        recycledRect.freeRect(bitmapHolder);
    }

}
