package com.thezs.fabianzachs.tapattack.GameFragment.GameModes;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.GameFragment.ColorPicker;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeBitmapManager;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeMovers.ContinuousShapeMover;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes.NormalShapeBuilder;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes.ShapeObject;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapePicker;
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
        int shapeRadius = Constants.SCREEN_WIDTH/9;
        mover = new ContinuousShapeMover();
        mover.setSpeed(mover.new GrowingSlowSpeed());
        //mover = new DiscreteShapeMover(shapeRadius, Constants.SCREEN_WIDTH/10);
        //ArrayList<Integer> yspots = mover.getyAxisShapeLocations();
        shapes = new ArrayList<>();

        ShapeObject object = builder.buildShape("circle", 0xffffffff, new Point(100, 5), new Paint(), new Rect(0,0,0,0),shapeRadius);
        shapes.add(0,object);
        ShapeObject object2 = builder.buildShape("circle", 0xffffffff, new Point(300,-300), new Paint(), new Rect(0,0,0,0),shapeRadius);
        shapes.add(0,object2);
        ShapeObject object3 = builder.buildShape("circle", 0xffffffff, new Point(100, -650), new Paint(), new Rect(0,0,0,0),shapeRadius);
        shapes.add(0,object3);
        ShapeObject object4 = builder.buildShape("circle", 0xffffffff, new Point(200, -900), new Paint(), new Rect(0,0,0,0),shapeRadius);
        shapes.add(0,object4);
        ShapeObject object5 = builder.buildShape("circle", 0xffffffff, new Point(300, -1250), new Paint(), new Rect(0,0,0,0),shapeRadius);
        shapes.add(0,object5);


        this.soundEffectsManager = new SoundEffectsManager();
        picker = new ColorPicker(0.8f);

    }
    private ContinuousShapeMover mover;
    private long startTime = System.currentTimeMillis();
    private ColorPicker picker;



    public void update() {
        mover.update(shapes);
        if (System.currentTimeMillis() - startTime > 1000)
            mover.setSpeed(mover.new ConstantSlowSpeed());
        Log.d("shapepicker", "update: " + picker.getColor());

    }

    public void draw(Canvas canvas) {
        for (ShapeObject shape : shapes) {
            shape.draw(canvas);
        }

    }

    public void receiveTouch(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN);
            //shapes.remove(shapes.size()-1);

    }
}
