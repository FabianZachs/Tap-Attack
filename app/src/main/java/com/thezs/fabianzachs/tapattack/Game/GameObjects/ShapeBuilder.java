package com.thezs.fabianzachs.tapattack.Game.GameObjects;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;

import com.thezs.fabianzachs.tapattack.Animation.AnimationManager;
import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.Arrow;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.Circle;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.Cross;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.ShapeObject;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.Square;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.Star;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by fabianzachs on 25/02/18.
 */

public class ShapeBuilder {

    private AnimationManager animationManager;

    public ShapeBuilder(long initTime) {
        // decides durationAlive depending on initTime & current time
        initializeAnimations();
    }

    public ShapeObject buildShape(String shape, String color, Point centerLocation, Paint paint) {
        return buildShape(shape,color,centerLocation,paint,"UP");
    }

    public ShapeObject buildShape(String shape, String color, Point centerLocation, Paint paint, String direction) {

        switch (shape) {
            case "circle":
                return new Circle(8, color, centerLocation,
                        animationManager.getBitmap("circle", color, false),
                        animationManager.getBitmap("circle", color, true), paint);

            case "square":
                return new Square(3, color, centerLocation,
                        animationManager.getBitmap("square", color, false),
                        animationManager.getBitmap("square", color, true), paint);

            case "cross":
                return new Cross(3, color, centerLocation,
                        animationManager.getBitmap("cross", color, false),
                        animationManager.getBitmap("cross", color, true), paint);

            case "arrow":
                return new Arrow(3, color, centerLocation,
                        translateBitmap(direction,color,false),
                        translateBitmap(direction,color,true),direction, paint);
            case "star":
                return new Star(2, "yellow", centerLocation,
                        animationManager.getBitmap("star", "yellow", false),
                        animationManager.getBitmap("star", "yellow", true), paint); // have it always take color gold
        }

        return null;
    }

    private Bitmap translateBitmap(String direction, String color, boolean click) {

        Bitmap origonalBitmap = animationManager.getBitmap("arrow", color, click);
        Matrix rotationMatrix = new Matrix();

        switch (direction) {
            case "UP":
                break;
            case "LEFT":
                rotationMatrix.postRotate(-90);
                break;
            case "RIGHT":
                rotationMatrix.postRotate(90);
                break;
            case "DOWN":
                rotationMatrix.postRotate(180);
                break;

        }
        return Bitmap.createBitmap(origonalBitmap, 0, 0, origonalBitmap.getWidth(), origonalBitmap.getHeight(), rotationMatrix, true);
    }


    private void initializeAnimations() {

        SharedPreferences prefs = Constants.CURRENT_CONTEXT.getSharedPreferences("playerPrefs", MODE_PRIVATE);

        // change error: no theme to basic colorscheme
        String theme = prefs.getString("theme", "error: no theme");

        this.animationManager = new AnimationManager(theme);
    }
}
