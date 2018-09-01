package com.thezs.fabianzachs.tapattack.GameFragment.GameOverClasses;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.shapes.Shape;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.ShapeBuilder;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeMovers.ContinuousShapeMover;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes.NormalShapeBuilder;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes.ShapeObject;

import java.util.ArrayList;

public class Shield {

    public boolean executable;

    public Shield() {
        SharedPreferences prefs = Constants.CURRENT_CONTEXT.getSharedPreferences("playerInfo", Context.MODE_PRIVATE);
        executable = prefs.getBoolean("shieldEnabled", true);
    }

    public void execute(ArrayList<ShapeObject> shapes) {
        executable = false;
        turnShapesIntoStars(shapes);
        // todo update buttom view bar to black USE MEDIATOR

    }

    private void turnShapesIntoStars(ArrayList<ShapeObject> shapes) {
        NormalShapeBuilder shapeBuilder = new NormalShapeBuilder();
        for (int shapeIndex = 0; shapeIndex < shapes.size(); shapeIndex++) {
            ShapeObject star = shapeBuilder.buildShape("star",
                    shapes.get(shapeIndex).getColor(), shapes.get(shapeIndex).getCenterLocation(),
                    shapes.get(shapeIndex).getPaint(), shapes.get(shapeIndex).getBitmapHolder(),
                    shapes.get(shapeIndex).getShapeRadius(),"up" );
            shapes.set(shapeIndex, star);
        }

    }
}
