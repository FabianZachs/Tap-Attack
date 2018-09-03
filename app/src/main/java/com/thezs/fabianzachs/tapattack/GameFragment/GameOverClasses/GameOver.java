package com.thezs.fabianzachs.tapattack.GameFragment.GameOverClasses;

import android.graphics.Canvas;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.GameFragment.Mediator;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes.Arrow;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes.ShapeObject;
import com.thezs.fabianzachs.tapattack.R;

import java.util.concurrent.CopyOnWriteArrayList;

public class GameOver {

    /*
    // todo : LIST OF THINGS THAT TRIGGER GAME OVERShapeBuilder
    // shape left screen
    // incorrect touch
    // touch wasnt on a shape todo handled
    // didnt touch bottom shape
    // shape was warning color
    // tapped the cross
     */

    // todo put gameover reasons in here
    // todo make a gameover drawer
    // todo where to put shield class
    private Mediator mediator;
    private Shield shield = new Shield();

    public GameOver(Mediator mediator) {
        this.mediator = mediator;
    }

    public void drawGameOver(Canvas canvas, CopyOnWriteArrayList<ShapeObject> shapes, ShapeObject shapeToBlink) {
    }

    // todo dont return true or setGameOver() if we still have shield
    /*
    if shield.executable then execute el
     */
    public boolean checkForGameOver(CopyOnWriteArrayList<ShapeObject> shapes) {

        boolean gameOverTestFailed = false;
        String reason = "";

        if (!touchBottomShape(shapes)) {
            gameOverTestFailed = true;
            reason = "reason";
        }

        for(ShapeObject shape : shapes) {
           if (incorrectTouch(shape))  {
               gameOverTestFailed = true;
               reason = "reason";
           }

           if (shapeLeftScreen(shape)) {
               gameOverTestFailed = true;
               reason = "reason";
           }

           if (shapeIsWarningColor(shape)) {
               gameOverTestFailed = true;
               reason = "reason";
           }

           if (shapeIsCross(shape)) {
               gameOverTestFailed = true;
               reason = "reason";
           }
        }

        if (gameOverTestFailed && shield.executable) {
            shield.execute(shapes);
            return false;
        }

        else if (gameOverTestFailed) {
            mediator.setGameOver(/*reason*/);
            return true;
        }

        return false;
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
