package com.thezs.fabianzachs.tapattack.GameFragment;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.GraveObjects.GraveObject;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes.ShapeObject;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class ShapesManager {

    private CopyOnWriteArrayList<ShapeObject> shapes = new CopyOnWriteArrayList<>();
    private ArrayList<GraveObject> graves = new ArrayList<>();
    private ShapeObject shapeToBlink;


    public void receiveTouch(MotionEvent event) {

    }


    public void update() {


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
}
