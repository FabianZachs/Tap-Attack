package com.thezs.fabianzachs.tapattack.Game.GameObjects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.shapes.Shape;
import android.util.Log;
import android.view.MotionEvent;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.Arrow;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.Circle;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.Cross;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.ShapeObject;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.Square;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.Star;
import com.thezs.fabianzachs.tapattack.Game.GameOverReasons;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.ProgressBar;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.Score;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.Streak;
import com.thezs.fabianzachs.tapattack.Game.GraveObjects.GraveFactory;
import com.thezs.fabianzachs.tapattack.Game.GraveObjects.GraveObject;
import com.thezs.fabianzachs.tapattack.Game.Mediator.CentralGameCommunication;
import com.thezs.fabianzachs.tapattack.Game.SharedResources.SharedPaint;
import com.thezs.fabianzachs.tapattack.Game.SharedResources.SharedRect;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by fabianzachs on 07/02/18.
 */

public class ShapesManager {

    private CentralGameCommunication mediator;

    CopyOnWriteArrayList<ShapeObject> shapes = new CopyOnWriteArrayList<>();
    private ArrayList<GraveObject> graveObjects = new ArrayList<>();
    private ShapesPopulator shapesPopulator;
    private GraveFactory graveFactory;
    private SharedPaint sharedPaint;
    private SharedRect sharedRect;
    private ShapeMover shapeMover;
    private int EXTRA_PIXELS_CLICK_AREA = Constants.SCREEN_HEIGHT/35;
    public boolean shapesJustStartedMoving = false;

    private ShapeObject shapeToBlink;


    public ShapesManager(CentralGameCommunication mediator) {

        this.mediator = mediator;
        this.sharedPaint = new SharedPaint();
        this.sharedRect = new SharedRect();
        this.shapesPopulator = new ShapesPopulator(mediator,sharedPaint,sharedRect, shapes);
        this.graveFactory = new GraveFactory();
        this.shapeMover = new ShapeMover(mediator);
        mediator.addObject(shapeMover);
    }

    public void setBlinkingShape(ShapeObject shapeToBlink) {
        this.shapeToBlink = shapeToBlink;
    }

    private Rect shapeClickArea(ShapeObject shape) {
        Rect shapeClickArea = new Rect(shape.getBitmapHolder());
        shapeClickArea.left = shapeClickArea.left - EXTRA_PIXELS_CLICK_AREA;
        shapeClickArea.right = shapeClickArea.right + EXTRA_PIXELS_CLICK_AREA;
        shapeClickArea.top = shapeClickArea.top - EXTRA_PIXELS_CLICK_AREA;
        shapeClickArea.bottom = shapeClickArea.bottom + EXTRA_PIXELS_CLICK_AREA;

        return shapeClickArea;
    }

    public void recieveTouch(MotionEvent event) {

        boolean shapeInteractment = false;

        for (ShapeObject shape : shapes) {
            /*
            if (shape.getBitmapHolder().contains((int) event.getX(), (int) event.getY())) {
                shape.recieveTouch(event);
                shapeInteractment = true;
            }*/
            if (shapeClickArea(shape).contains((int) event.getX(), (int) event.getY())) {
                shape.recieveTouch(event);
                shapeInteractment = true;
            }
        }

         // if user ACTION_DOWNs and not onto a shape
        if (!shapeInteractment && event.getAction() == MotionEvent.ACTION_DOWN) {
            //mediator.changeProgressBarBy(-40);
            mediator.warningComponentShake();
            //mediator.resetStreak();
            shapeToBlink = getFurthestDownNormalShape();
            mediator.setGameOver(GameOverReasons.backgroundTap()); //todo for setting up gameOver we have that background touch triggers gameover
        }
    }

    private ShapeObject getFurthestDownNormalShape() {
        for (int i = shapes.size() - 1; i >= 0; i--) {
            if (!shapeIsStarOrCross(shapes.get(i)))
                return shapes.get(i);
        }
        return shapes.get(0);

    }

    // todo refactor update into smaller functions with names specifying what each section checks
    public void update() {
        //Log.d("debugpaint", "update: shapes size" + shapes.size());
        //Log.d("debugpaint", "update: grave seize" + graveObjects.size());

        for (ShapeObject shape : shapes) {

            // todo if incorrect touch type
            if (shape.wasIncorrectTouch()) {
                shapeToBlink = shape;
                mediator.setGameOver(GameOverReasons.wrongShapeAction(shape, shape.getTypeOfIncorrectTouch(), ""));
            }

            if (shape.getLives() <= 0) {

                // todo first check if tapped furthest down (unless star or cross)
                for (int i = shapes.size() - 1; i > shapes.indexOf(shape); i--) {
                    if (!shapeIsStarOrCross(shapes.get(i)) && shapes.get(i).getLives() > 0) {
                        shapeToBlink = getFurthestDownNormalShape();
                        mediator.setGameOver(GameOverReasons.wrongShapeTap(shapes.get(i)));
                        return;
                    }
                }

                // todo optimize:
                if (mediator.getStrWarningColor().equals(shape.getColor())) {
                    mediator.warningComponentShake();
                    shapeToBlink = shape; // since we already checked if its the furthest down -- this works for wrong color stars
                    mediator.setGameOver(GameOverReasons.warningColorTap(shape));
                    break;
                }



                /*
                if (shape instanceof Square) {
                    mediator.squareTapTwoSoundEffect();
                }
                */
                shape.playDeathSoundEffect();

                if (shape.getGravable())
                    graveObjects.add(graveFactory.buildGrave(shape));
                else {
                    freeResources(shape.getPaintObj(), shape.getBitmapHolder());
                }
                shapes.remove(shape);
                mediator.incScore(shape.getPoints(), shape.getColor());
                //mediator.incStreak(1, shape.getColor());

                mediator.editWaningColorStreak(shape);


                //mediator.changeProgressBarBy(shape.getProgressBarAddition(), shape.getColor());
            }

            // TODO wont happen anymore
            else if (shape.isTimedOut()) {
                shapes.remove(shape);
                freeResources(shape.getPaintObj(), shape.getBitmapHolder());
                //mediator.resetStreak(); // TODO even for star????
            }


            // TODO this will end game
            //else if (shape.getBitmapHolder().top > Constants.SCREEN_HEIGHT && (shape.getClass() != Star.class && shape.getClass() != Cross.class)) {
            else if (shape.leftScreen() && !shapeIsStarOrCross(shape)) {
                freeResources(shape.getPaintObj(), shape.getBitmapHolder());
                shapes.remove(shape);
                //mediator.setGameOver();
            }

            else if (shape.leftScreen() && shapeIsStarOrCross(shape)) {
                freeResources(shape.getPaintObj(), shape.getBitmapHolder());
                shapes.remove(shape);
            }


            else shape.update();

        }

        if (mediator.gameMoving())  {
            if (shapesJustStartedMoving) {
                shapeMover.resetStartTime();
                shapesJustStartedMoving = false;
            }

            shapeMover.update(shapes);
        }

        //shapeMover.update(shapes);

        for (GraveObject graveObject : graveObjects) {
            if (graveObject.graveDestroyed()) {
                graveObjects.remove(graveObject);
                freeResources(graveObject.getPaint(), graveObject.getBitmapHolder());
            }
            else
                graveObject.update();
        }

        shapes = shapesPopulator.update(shapes);
    }

    private boolean shapeIsStarOrCross(ShapeObject shape) {
        return shape.getClass() == Star.class || shape.getClass() == Cross.class;
    }

    public void drawGameOverREDO(Canvas canvas) {
        // todo depending on the type of game over draw draw different gameover event on canvas
        /*
        String reason = " adwad"
        switch (reason) {
            case "Not touching correct shape":
                drawGameOver(canvas);
                break;
            case "star warning color":

        }
        */

        blink(canvas, shapeToBlink);
        for (ShapeObject shape : shapes) {
            if (!shape.equals(shapeToBlink))
                shape.draw(canvas);
        }
    }

    public void drawGameOver(Canvas canvas) {
        // todo make sure that shape isnt a star or a cross
        int indexOfBlinkingShape = 0;
        for (int i = shapes.size() - 1; i>=0; i--) {
            if (!shapeIsStarOrCross(shapes.get(i))) {
                indexOfBlinkingShape = i;
                blink(canvas, shapes.get(indexOfBlinkingShape));
                break;
            }
        }

        for (ShapeObject shape : shapes) {
            if (shapes.indexOf(shape) != indexOfBlinkingShape)
                shape.draw(canvas);
        }


        //blink(canvas, shapes.get(shapes.size()-1));
        /*
        for (ShapeObject shape : shapes) {
            if (shapes.indexOf(shape) != shapes.size()-1)
                shape.draw(canvas);
        }
        */
    }


    private final float BLINK_TIME = 0.5f;
    private long timeOfBlinkSwitch = System.currentTimeMillis();
    private boolean blinkOn = true;

    private void blink(Canvas canvas, ShapeObject shapeObject) {
        if (System.currentTimeMillis() - timeOfBlinkSwitch > BLINK_TIME * 1000) {
            blinkOn = !blinkOn;
            timeOfBlinkSwitch = System.currentTimeMillis();
        }
        if (blinkOn)
            shapeObject.draw(canvas);
    }

    public void draw(Canvas canvas) {


        for (ShapeObject shape : shapes)
            shape.draw(canvas);

        for (GraveObject graveObject : graveObjects)
            graveObject.draw(canvas);



        /*
        // todo testing for warning color streak
        Paint paint = new Paint();
        paint.setTextSize(40);
        paint.setColor(Color.WHITE);
        canvas.drawText(mediator.getWarningColorStreak() +"" , 200, 200,paint);
        */

    }

    private void freeResources(Paint paintObj, Rect bitmapHolder) {
        sharedPaint.freePaint(paintObj);
        sharedRect.freeRect(bitmapHolder);
    }

    // todo later check to ensure this removes the old shape (garbage collected) and paint/rect is reused -- but logically should be fine
    public void turnShapesIntoStars() {
        for (int shapeIndex = 0; shapeIndex < shapes.size(); shapeIndex++) {
            shapes.set(shapeIndex,shapesPopulator.getShape("star", shapes.get(shapeIndex).getColor(), shapes.get(shapeIndex).getCenterLocation(), shapes.get(shapeIndex).getPaintObj(),
                    shapes.get(shapeIndex).getBitmapHolder(), mediator, "UP"));
        }
    }

}
