package com.thezs.fabianzachs.tapattack.Game;

import android.graphics.drawable.shapes.Shape;

import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.ShapeObject;

/**
 * Created by fabianzachs on 22/06/18.
 */

public class GameOverReasons {

    public static String xTap() {
        return "OH NO! DON'T TAP THE X";
    }

    public static String warningColorTap(ShapeObject shape) {
        return "THE " + shape.getClass().getSimpleName().toUpperCase() + " HAD THE WARNING COLOR";
    }

    public static String shapeLeftScreen(ShapeObject shape) {
        return "LOOKS LIKE A " +shape.getClass().getSimpleName().toUpperCase()  +" ESCAPED THE SCREEN";
    }

    public static String wrongShapeTap(ShapeObject shape) {
        return "THERE WAS A " + shape.getClass().getSimpleName().toUpperCase()  + " BELOW THE ONE YOU TAPPED";
    }

    public static String backgroundTap() {
        return "DARN! YOU MISSED THE SHAPE";
    }

    public static String wrongShapeAction(ShapeObject shape) {
        return "YOU MADE THE WRONG ACTION WITH THE " + shape.getClass().getSimpleName().toUpperCase();
    }
}
