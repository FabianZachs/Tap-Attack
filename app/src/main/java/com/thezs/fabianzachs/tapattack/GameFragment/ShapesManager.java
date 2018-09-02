package com.thezs.fabianzachs.tapattack.GameFragment;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.thezs.fabianzachs.tapattack.Game.SharedResources.SharedRect;
import com.thezs.fabianzachs.tapattack.GameFragment.GameOverClasses.GameOver;
import com.thezs.fabianzachs.tapattack.GameFragment.RecycledResources.RecycledPaint;
import com.thezs.fabianzachs.tapattack.GameFragment.RecycledResources.RecycledRect;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeMovers.ShapeMover;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.GraveObjects.GraveObject;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes.ShapeObject;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class ShapesManager {

    private CopyOnWriteArrayList<ShapeObject> shapes = new CopyOnWriteArrayList<>();
    private ArrayList<GraveObject> graves = new ArrayList<>();
    private ShapesPopulator shapesPopulator;
    private ShapeMover shapeMover;

    private ShapeObject shapeToBlink;
    private GameOver gameOver = new GameOver();
    private RecycledPaint recycledPaint = new RecycledPaint();
    private RecycledRect recycledRect = new RecycledRect();
    private int numberOfDesttroyedShapes= 0;
    // todo ^ maybe work with number of shapes destroyed -- that way we know easly when game is over
    // todo and makes more sense since it more directly measures progress of player


    public ShapesManager(ShapeMover shapeMover, ShapePicker shapePicker, ColorPicker colorPicker,
                         int shapeRadius, int shapeSpacing) {

        this.shapesPopulator = new ShapesPopulator(shapes, recycledRect, recycledPaint, colorPicker,
                shapePicker, shapeRadius, shapeSpacing);

        this.shapeMover = shapeMover;



    }

    public void receiveTouch(MotionEvent event) {

    }


    public void update() {
        gameOver.checkForGameOver(shapes);
        //removeKilledShapes and create graves if gravableI


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

    private void freeResources(Paint paint, Rect bitmapHolder) {
        recycledPaint.freePaint(paint);
        recycledRect.freeRect(bitmapHolder);
    }

}
