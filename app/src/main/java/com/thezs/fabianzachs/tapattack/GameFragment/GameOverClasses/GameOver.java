package com.thezs.fabianzachs.tapattack.GameFragment.GameOverClasses;

import android.graphics.Canvas;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.GameFragment.Mediator;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes.Arrow;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes.Cross;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes.ShapeObject;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes.Star;
import com.thezs.fabianzachs.tapattack.R;

import java.util.concurrent.CopyOnWriteArrayList;

public class GameOver {


    private Mediator mediator;
    private Shield shield = new Shield();
    private ShapeObject shapeToBlink;

    public GameOver(Mediator mediator) {
        this.mediator = mediator;
    }

    private long timeOfBlinkSwitch = System.currentTimeMillis();
    private boolean blinkOn = true;

    public void drawGameOver(Canvas canvas, CopyOnWriteArrayList<ShapeObject> shapes) {
        blink(canvas, shapeToBlink);
        for (ShapeObject shape : shapes) {
            if (!shape.equals(shapeToBlink))
                shape.draw(canvas);
        }
    }

    private void blink(Canvas canvas, ShapeObject shapeObject) {
        final float BLINK_TIME = 0.5f;
        if (System.currentTimeMillis() - timeOfBlinkSwitch > BLINK_TIME * 1000) {
            blinkOn = !blinkOn;
            timeOfBlinkSwitch = System.currentTimeMillis();
        }
        if (blinkOn)
            shapeObject.draw(canvas);
    }

    public boolean checkForGameOver(CopyOnWriteArrayList<ShapeObject> shapes) {

        boolean gameOverTestFailed = false;
        String reason = "";

        if (wrongShapeTouched(shapes)) {
            gameOverTestFailed = true;
            reason = wrongShapeTapReason();
        }

        for(ShapeObject shape : shapes) {
            if (shape.getLives() <= 0) {

                if (incorrectTouch(shape))  {
                    gameOverTestFailed = true;
                    reason = wrongShapeActionReason(shape, shape.getTypeOfIncorrectTouch(),null);
                }
                if (shapeIsWarningColor(shape)) {
                    gameOverTestFailed = true;
                    reason = warningColorTapReason(shape);
                }
                if (shapeIsCross(shape)) {
                    gameOverTestFailed = true;
                    reason = xTapReason();
                }
            }


           if (shapeLeftScreen(shape)) {
               gameOverTestFailed = true;
               reason = shapeLeftScreenReason(shape);
           }


        }

        if (gameOverTestFailed && shield.executable) {
            shield.execute(shapes);
            return false;
        }

        else if (gameOverTestFailed) {
            mediator.setGameOver(reason);
            return true;
        }

        return false;
    }

    private boolean wrongShapeTouched(CopyOnWriteArrayList<ShapeObject> shapes) {

        int indexOfFurthestDownNormalShape = getIndexOfFurthestDownNormalShape(shapes);

        for (int i = indexOfFurthestDownNormalShape-1; i >= 0; i--)
            if (shapes.get(i).getLives()<=0) {
                shapeToBlink = shapes.get(i);
                return true;
            }

        return false;
    }

    private int getIndexOfFurthestDownNormalShape(CopyOnWriteArrayList<ShapeObject> shapes) {
        for (int i = shapes.size()-1;i>=0; i--) {
            if (!shapeIsStarOrCross(shapes.get(i)) && shapes.get(i).getLives()>0)
                return i;
        }
        return 0;
    }

    private boolean shapeIsStarOrCross(ShapeObject shape) {
        return shape.getClass() == Star.class || shape.getClass() == Cross.class;
    }

    private boolean incorrectTouch(ShapeObject shape) {
        if (shape.wasIncorrectTouch()) {
            shapeToBlink = shape;
            return true;
        }

        return false;
    }

    private boolean shapeLeftScreen(ShapeObject shape) {
        return (!shapeIsStarOrCross(shape) && shape.getBitmapHolder().top>Constants.GAME_VIEW_HEIGHT);
        // todo what should blink?? set shapeToBlink to null to indicate all shapes should blink??
    }


    private boolean shapeIsWarningColor(ShapeObject shape) {
        if (shape.getColor().equals(mediator.getWarningColor())) {
            shapeToBlink = shape;
            return true;
        }

        return false;
    }

    private boolean shapeIsCross(ShapeObject shape) {
        if (shape.getClass() == Cross.class) {
            shapeToBlink = shape;
            return true;
        }

        return false;
    }

    private static String xTapReason() {
        return Constants.CURRENT_CONTEXT.getResources().getString(R.string.xTapGameOver);
    }

    private static String warningColorTapReason(ShapeObject shape) {
        return Constants.CURRENT_CONTEXT.getResources().getString(R.string.warningColorTapGameOver,
                shape.getClass().getSimpleName().toUpperCase());
    }

    private static String shapeLeftScreenReason(ShapeObject shape) {
        return Constants.CURRENT_CONTEXT.getResources().getString(R.string.shapeLeftScreenGameOver,
                shape.getClass().getSimpleName().toUpperCase());
    }

    private static String wrongShapeTapReason() {
        return Constants.CURRENT_CONTEXT.getResources().getString(R.string.wrongShapeTapGameOver);
    }

    public static String backgroundTapReason() {
        return Constants.CURRENT_CONTEXT.getResources().getString(R.string.backgroundTapGameOver);
    }

    private static String wrongShapeActionReason(ShapeObject shape, String shapeAction, String extraDetail) {

        if (shape instanceof Arrow && shapeAction.equals("fling")) {
            return Constants.CURRENT_CONTEXT.getResources().getString(R.string.wrongFlingDirectionGameOver);
        }

        return Constants.CURRENT_CONTEXT.getResources().getString(R.string.wrongShapeActionGameOver,
                shapeAction.toUpperCase(), shape.getClass().getSimpleName().toUpperCase());
    }

}
