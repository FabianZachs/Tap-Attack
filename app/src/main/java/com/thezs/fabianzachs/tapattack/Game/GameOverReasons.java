package com.thezs.fabianzachs.tapattack.Game;

import android.graphics.drawable.shapes.Shape;

import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.Arrow;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.Circle;
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


    public static String wrongShapeAction(ShapeObject shape, String shapeAction, String extraDetail) {
        //return "YOU MADE THE WRONG ACTION WITH THE " + shape.getClass().getSimpleName().toUpperCase();
        //return "WRONG ACTION FOR THE " + shape.getClass().getSimpleName().toUpperCase();

        if (shape instanceof Arrow && shapeAction.equals("fling")) {
            return "WRONG FLING DIRECTION FOR THE ARROW " + extraDetail; // todo maybe include actusl direction to fling in
        }

        return "DON'T " + shapeAction.toUpperCase() + " THE " + shape.getClass().getSimpleName().toUpperCase();
    }

    /*
    public static String wrongShapeAction(ShapeObject shape) {
        //return "YOU MADE THE WRONG ACTION WITH THE " + shape.getClass().getSimpleName().toUpperCase();
        return "WRONG ACTION FOR THE " + shape.getClass().getSimpleName().toUpperCase();
    }
    */
}
