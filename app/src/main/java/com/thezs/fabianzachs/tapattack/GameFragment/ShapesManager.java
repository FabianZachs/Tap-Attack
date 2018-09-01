package com.thezs.fabianzachs.tapattack.GameFragment;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.thezs.fabianzachs.tapattack.Game.SharedResources.SharedPaint;
import com.thezs.fabianzachs.tapattack.Game.SharedResources.SharedRect;
import com.thezs.fabianzachs.tapattack.GameFragment.GameOverClasses.GameOver;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.GraveObjects.GraveObject;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes.ShapeObject;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class ShapesManager {

    private CopyOnWriteArrayList<ShapeObject> shapes = new CopyOnWriteArrayList<>();
    private ArrayList<GraveObject> graves = new ArrayList<>();
    private ShapeObject shapeToBlink;
    private GameOver gameOver = new GameOver();
    private SharedPaint sharedPaint = new SharedPaint();
    private SharedRect sharedRect = new SharedRect();


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

    private void freeResources(Paint paint, Rect bitmapHolder) {
        sharedPaint.freePaint(paint);
        sharedRect.freeRect(bitmapHolder);
    }
}
