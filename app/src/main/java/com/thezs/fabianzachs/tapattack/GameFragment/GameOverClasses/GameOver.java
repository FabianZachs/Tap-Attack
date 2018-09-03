package com.thezs.fabianzachs.tapattack.GameFragment.GameOverClasses;

import android.graphics.Canvas;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes.Arrow;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes.ShapeObject;
import com.thezs.fabianzachs.tapattack.R;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameOver {

    /*
    // todo : LIST OF THINGS THAT TRIGGER GAME OVERShapeBuilder
    // shape left screen
    // incorrect touch
    // touch wasnt on a shape
    // didnt touch bottom shape
    // shape was warning color
    // tapped the cross
     */

    // todo put gameover reasons in here
    // todo make a gameover drawer
    // todo where to put shield class

    public boolean checkForGameOver(CopyOnWriteArrayList<ShapeObject> shapes) {
        // todo run through all tests
        return false;
    }




    public void drawGameOver(Canvas canvas, CopyOnWriteArrayList<ShapeObject> shapes, ShapeObject shapeToBlink) {

    }





    private class GameOverReasons {

        public String xTap() {
            return Constants.CURRENT_CONTEXT.getResources().getString(R.string.xTapGameOver);
        }

        public String warningColorTap(ShapeObject shape) {
            return Constants.CURRENT_CONTEXT.getResources().getString(R.string.warningColorTapGameOver,
                    shape.getClass().getSimpleName().toUpperCase());
        }

        public String shapeLeftScreen(ShapeObject shape) {
            return Constants.CURRENT_CONTEXT.getResources().getString(R.string.shapeLeftScreenGameOver,
                    shape.getClass().getSimpleName().toUpperCase());
        }

        public String wrongShapeTap(ShapeObject shape) {
            return Constants.CURRENT_CONTEXT.getResources().getString(R.string.wrongShapeTapGameOver,
                    shape.getClass().getSimpleName().toUpperCase());
        }

        public String backgroundTap() {
            return Constants.CURRENT_CONTEXT.getResources().getString(R.string.backgroundTapGameOver);
        }

        public String wrongShapeAction(ShapeObject shape, String shapeAction, String extraDetail) {

            if (shape instanceof Arrow && shapeAction.equals("fling")) {
                return Constants.CURRENT_CONTEXT.getResources().getString(R.string.wrongFlingDirectionGameOver);
            }

            return Constants.CURRENT_CONTEXT.getResources().getString(R.string.wrongShapeActionGameOver,
                    shapeAction.toUpperCase(), shape.getClass().getSimpleName().toUpperCase());
        }
    }
}
