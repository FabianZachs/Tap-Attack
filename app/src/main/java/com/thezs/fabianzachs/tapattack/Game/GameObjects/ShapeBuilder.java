package com.thezs.fabianzachs.tapattack.Game.GameObjects;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.util.Log;

import com.thezs.fabianzachs.tapattack.Animation.AnimationManager;
import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.BackgroundHandlers.BackgroundHandler;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.Arrow;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.Circle;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.Cross;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.ShapeObject;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.Square;

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

    public ShapeObject buildShape(String shape, String color, Point centerLocation) {
        return buildShape(shape,color,centerLocation,"UP");
    }

    public ShapeObject buildShape(String shape, String color, Point centerLocation, String direction) {

        switch (shape) {
            case "circle":
                return new Circle(3, color, centerLocation,
                        animationManager.getBitmap("circle", color, false),
                        animationManager.getBitmap("circle", color, true));

            case "square":
                return new Square(3, color, centerLocation,
                        animationManager.getBitmap("square", color, false),
                        animationManager.getBitmap("square", color, true));

            case "cross":
                return new Cross(3, color, centerLocation,
                        animationManager.getBitmap("cross", color, false),
                        animationManager.getBitmap("cross", color, true));

            case "arrow":
                return new Arrow(3, color, centerLocation,
                        translateBitmap(direction,color,false),
                        translateBitmap(direction,color,true),direction);
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
