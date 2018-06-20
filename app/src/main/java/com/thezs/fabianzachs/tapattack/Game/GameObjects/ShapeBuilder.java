package com.thezs.fabianzachs.tapattack.Game.GameObjects;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

import com.thezs.fabianzachs.tapattack.Animation.AnimationManager;
import com.thezs.fabianzachs.tapattack.Animation.Themes.ThemesManager;
import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.Arrow;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.Circle;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.Cross;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.ShapeObject;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.Square;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.Star;
import com.thezs.fabianzachs.tapattack.Game.Mediator.CentralGameCommunication;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by fabianzachs on 25/02/18.
 */

public class ShapeBuilder {

    //private AnimationManager animationManager;
    //private NeonTheme neonTheme;
    private ThemesManager themesManager; //TODO do we need this as an attribute?
    //private ThemeObject gameTheme;

    public ShapeBuilder(long initTime) {
        // decides durationAlive depending on initTime & current time
        //initializeAnimations();
        themesManager = new ThemesManager();
        //gameTheme = themesManager.buildTheme("vibrant", "curved");
        themesManager.setCurrentTheme(themesManager.buildTheme(Constants.CURRENT_THEME,Constants.CURRENT_SHAPE_TYPE));
    }

    public ShapeObject buildShape(String shape, String color, Point centerLocation, Paint paint, Rect bitmapHolder, CentralGameCommunication mediator) {
        return buildShape(shape,color,centerLocation,paint,bitmapHolder,mediator,"UP");
    }

    // TODO refactor star and cross which dont take 2 bitmaps
    public ShapeObject buildShape(String shape, String color, Point centerLocation, Paint paint, Rect bitmapHolder, CentralGameCommunication mediator ,String direction) {

        Integer colorInt = themesManager.getCurrentTheme().getColorToInt(color);
        Log.d("colorint", "buildShape: colr" + colorInt);

        switch (shape) {

            case "circle":
                return new Circle(100, color, colorInt,centerLocation,
                        // TODO somehow call correct method of subclass (SimpleTheme)
                        themesManager.getCurrentTheme().getShapeBitmap(shape,color,false),
                        themesManager.getCurrentTheme().getShapeBitmap(shape,color,true),
                        themesManager.getCurrentTheme().getShapePaint(paint,color), bitmapHolder, mediator);


/*
            case "circle":
                return new Circle(8, color, centerLocation,
                        animationManager.getBitmap("circle", color, false),
                        animationManager.getBitmap("circle", color, true),
                        paint, bitmapHolder, mediator);
*/
            case "square":
                return new Square(100, color, colorInt, centerLocation,
                        themesManager.getCurrentTheme().getShapeBitmap(shape,color,false),
                        themesManager.getCurrentTheme().getShapeBitmap(shape,color,true),
                        themesManager.getCurrentTheme().getShapePaint(paint,color), bitmapHolder, mediator);

                /*
            case "square":
                return new Square(3, color, centerLocation,
                        animationManager.getBitmap("square", color, false),
                        animationManager.getBitmap("square", color, true),
                        paint, bitmapHolder, mediator);
*/
            case "cross":
                //Log.d("crosscolor", "buildShape: color"+color);

                return new Cross(100, color, colorInt, centerLocation,
                        themesManager.getCurrentTheme().getShapeBitmap(shape,color,false),
                        themesManager.getCurrentTheme().getShapeBitmap(shape,color,true),
                        themesManager.getCurrentTheme().getShapePaint(paint,color), bitmapHolder, mediator);
                /*
            case "cross":
                return new Cross(3, color, centerLocation,
                        animationManager.getBitmap("cross", color, false),
                        animationManager.getBitmap("cross", color, true),
                        paint, bitmapHolder, mediator);
*/
            case "arrow":
                return new Arrow(100, color, colorInt, centerLocation,
                        translateBitmap(direction, themesManager.getCurrentTheme().getShapeBitmap(shape,color,false)),
                        null,
                        direction, themesManager.getCurrentTheme().getShapePaint(paint,color), bitmapHolder, mediator);
                /*
            case "arrow":
                return new Arrow(3, color, centerLocation,
                        translateBitmap(direction,color,false),
                        translateBitmap(direction,color,true),
                        direction, paint, bitmapHolder, mediator);
                        */

            case "star":
                return new Star(100, color, colorInt, centerLocation,
                        themesManager.getCurrentTheme().getShapeBitmap(shape,color,false),
                        null,
                        themesManager.getCurrentTheme().getShapePaint(paint,color), bitmapHolder, themesManager.getCurrentTheme().getIntColors(),
                        themesManager.getCurrentTheme().getStrColors(), mediator);
                /*
            case "star":
                return new Star(5, "yellow", centerLocation,
                        animationManager.getBitmap("star", "yellow", false),
                        animationManager.getBitmap("star", "yellow", true),
                        paint, bitmapHolder, mediator); // have it always take color gold
                        */
        }

        return null;
    }

    private Bitmap translateBitmap(String direction, Bitmap origonalBitmap) {

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
    /*
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
    }*/

/*

    private void initializeAnimations() {

        SharedPreferences prefs = Constants.CURRENT_CONTEXT.getSharedPreferences("playerPrefs", MODE_PRIVATE);

        // change error: no theme to basic colorscheme
        String theme = prefs.getString("theme", "error: no theme");

        this.animationManager = new AnimationManager(theme);
    }*/
}
