package com.thezs.fabianzachs.tapattack.GameFragment.GameModes;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeBitmapManager;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeMovers.DiscreteShapeMover;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes.Circle;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes.NormalShapeBuilder;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes.ShapeObject;
import com.thezs.fabianzachs.tapattack.GameFragment.SoundEffectsManager;
import com.thezs.fabianzachs.tapattack.GameFragment.ThemeManager;
import com.thezs.fabianzachs.tapattack.GameFragment.WarningColorComponent;

import java.util.ArrayList;

public abstract class GameMode {

    protected boolean warningColorEnabled;


    private ArrayList<ShapeObject> shapes;
    private SoundEffectsManager soundEffectsManager;

    public GameMode(View view, boolean warningColorEnabled) {
        this.warningColorEnabled = warningColorEnabled;

        ThemeManager themeManager = new ThemeManager();
        if (warningColorEnabled)
            new WarningColorComponent(view,themeManager.getColors());

        ShapeBitmapManager shapeBitmapManager = new ShapeBitmapManager();




        NormalShapeBuilder builder = new NormalShapeBuilder();
        int shapeRadius = Constants.SCREEN_WIDTH/10;
        mover = new DiscreteShapeMover(shapeRadius, Constants.SCREEN_WIDTH/10);
        ArrayList<Integer> yspots = mover.getyAxisShapeLocations();
        shapes = new ArrayList<>();

        for (Integer y : yspots) {
            ShapeObject object = builder.buildShape("circle", 0xffffffff, new Point(100, y), new Paint(), new Rect(0,0,0,0),shapeRadius);
            shapes.add(0,object);
        }


        this.soundEffectsManager = new SoundEffectsManager();
    }
    private DiscreteShapeMover mover;



    public void update() {
        mover.update(shapes);

    }

    public void draw(Canvas canvas) {
        for (ShapeObject shape : shapes) {
            shape.draw(canvas);
        }

    }

    public void receiveTouch(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN)
            shapes.remove(shapes.size()-1);

    }
}
