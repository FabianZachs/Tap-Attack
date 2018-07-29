package com.thezs.fabianzachs.tapattack.Game.GameModeScenes;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;

import com.thezs.fabianzachs.tapattack.Animation.Themes.ThemesManager;
import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.BackgroundHandlers.BackgroundManager;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.ShapeBuilder;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.ShapeColorPicker;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.ShapeObject;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.Star;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.WarningColor;
import com.thezs.fabianzachs.tapattack.Game.GraveObjects.GraveFactory;
import com.thezs.fabianzachs.tapattack.Game.GraveObjects.GraveObject;
import com.thezs.fabianzachs.tapattack.Game.Mediator.CentralGameCommunication;
import com.thezs.fabianzachs.tapattack.Game.Scene;

/**
 * Created by fabianzachs on 25/07/18.
 */

public class InstructionsGameScene implements Scene{

    private int instructionIndex = 0;
    //private int NUMBER_OF_INSTRUCTIONS = 10; // todo update when number found
    private boolean instructionsDone = false;

    private CentralGameCommunication mediator;
    private BackgroundManager backgroundManager;
    private WarningColor warningColor;
    private ShapeBuilder shapeBuilder;
    private ShapeColorPicker shapeColorPicker;
    private ShapeObject shapeToDisplay = null;
    private GraveObject graveToDisplay = null;
    private boolean readyForNextInstruction = false;
    private GraveFactory graveFactory = new GraveFactory();
    private Point shapeLocation;
    private String textToDisplay = null;
    private Point shapeTextLocation;

    private Paint shapePaint = new Paint();
    private Point warningColorTextLocation1 = new Point(Constants.SCREEN_WIDTH/2, Constants.SCREEN_HEIGHT/8);
    private Point warningColorTextLocation2 = new Point(Constants.SCREEN_WIDTH/2, Constants.SCREEN_HEIGHT/2);
    private Point continueTextLocation = new Point(Constants.SCREEN_WIDTH/2, (7 * Constants.SCREEN_HEIGHT)/8);
    private Rect shapeRect = new Rect();
    private Paint textPaint1 = new Paint();
    private Paint textPaint2 = new Paint();
    private Paint continueTextPaint = new Paint();
    // todo make rect and paint for shape global and paint for text description

    public InstructionsGameScene(CentralGameCommunication mediator) {
        this.mediator = mediator;
        mediator.resetInitTime();
        this.backgroundManager = new BackgroundManager();
        this.shapeBuilder = new ShapeBuilder();
        this.shapeColorPicker = new ShapeColorPicker(mediator);
        mediator.addObject(shapeColorPicker);
        this.warningColor = new WarningColor(mediator, ThemesManager.getStrColors(Constants.CURRENT_THEME), ThemesManager.getIntColors(Constants.CURRENT_THEME));
        mediator.addObject(warningColor);


        //this.shapeLocation = new Point(Constants.SCREEN_HEIGHT/3 - Constants.SHAPE_WIDTH/2, Constants.SCREEN_WIDTH/2 );
        this.shapeLocation = new Point(Constants.SCREEN_WIDTH/2, Constants.SCREEN_HEIGHT/3 );
        this.shapeTextLocation = new Point(Constants.SCREEN_WIDTH/2, (Constants.SCREEN_HEIGHT/3) * 2 );
        setupPaint(textPaint1, 40);
        setupPaint(textPaint2, 30);
        setupPaint(continueTextPaint, 100);


    }


    private void instructionToDraw(Canvas canvas) {
        switch (instructionIndex) {
            case 0:
                if (shapeToDisplay == null && graveToDisplay == null) {
                    shapeToDisplay = shapeBuilder.buildShape("circle", shapeColorPicker.getRandomNonWarningColor(), shapeLocation, shapePaint, shapeRect, mediator, "UP");
                    textToDisplay = "TAP CIRCLES ONCE";
                }
                shapeInstruction(canvas);
                return;
            case 1:
                if (shapeToDisplay == null && graveToDisplay == null) {
                    shapeToDisplay = shapeBuilder.buildShape("square", shapeColorPicker.getRandomNonWarningColor(), shapeLocation, shapePaint, shapeRect, mediator, "UP");
                    textToDisplay = "TAP SQUARES TWICE RELATIVELY QUICKLY";
                }
                shapeInstruction(canvas);
                return;
            case 2:
                if (shapeToDisplay == null && graveToDisplay == null) {
                    shapeToDisplay = shapeBuilder.buildShape("arrow", shapeColorPicker.getRandomNonWarningColor(), shapeLocation, shapePaint, shapeRect, mediator, "RIGHT");
                    textToDisplay = "FLICK ARROWS IN THE DIRECTION IT POINTS";
                }
                shapeInstruction(canvas);
                return;
            case 3:
                if (shapeToDisplay == null && graveToDisplay == null) {
                    shapeToDisplay = shapeBuilder.buildShape("arrow", shapeColorPicker.getRandomNonWarningColor(), shapeLocation, shapePaint, shapeRect, mediator, "LEFT");
                    textToDisplay = "FLICK ARROWS IN THE DIRECTION IT POINTS";
                }
                shapeInstruction(canvas);
                return;
            case 4:
                if (shapeToDisplay == null && graveToDisplay == null) {
                    shapeToDisplay = shapeBuilder.buildShape("cross", shapeColorPicker.getRandomNonWarningColor(), shapeLocation, shapePaint, shapeRect, mediator, "RIGHT");
                    textToDisplay = "NEVER TAP THE X... TAP IT TO CONTINUE :P";
                }
                shapeInstruction(canvas);
                return;
            case 5:
                warningColorInstruction(canvas);
                return;
            case 6:
                if (shapeToDisplay == null && graveToDisplay == null) {
                    shapeToDisplay = shapeBuilder.buildShape("star", shapeColorPicker.getColorForShape(), shapeLocation, shapePaint, shapeRect, mediator, "RIGHT");
                    textToDisplay = "TAP STAR ONLY WHEN NOT WARNING COLOR";
                }
                shapeInstruction(canvas);
                return;
            case 7:
                if (shapeToDisplay == null && graveToDisplay == null) {
                    shapeToDisplay = shapeBuilder.buildShape("circle", warningColor.getCurrentStrColor(), shapeLocation, shapePaint, shapeRect, mediator, "RIGHT");
                    textToDisplay = "CHANGE WARNING COLOR TO DESTROY CIRCLE";
                }
                shapeInstruction(canvas);
                return;
            case 8:
                finishInstruction(canvas);
                return;

        }
        instructionsDone = true;
    }

    private void finishInstruction(Canvas canvas) {
        canvas.drawText("INSTRUCTIONS COMPLETE!", warningColorTextLocation2.x, warningColorTextLocation2.y, textPaint1);
        canvas.drawText("CHANGE GAMEMODE IN STORE", warningColorTextLocation2.x, warningColorTextLocation2.y + 50, textPaint1);
    }

    private void warningColorInstruction(Canvas canvas) {
        //canvas.drawText("THIS DISPLAYS THE COLOR OF SHAPES YOU CANNOT TOUCH", warningColorTextLocation1.x, warningColorTextLocation1.y, textPaint1);
        canvas.drawText("THE COLOR OF SHAPES YOU CANNOT TOUCH", warningColorTextLocation1.x, warningColorTextLocation1.y, textPaint1);
        canvas.drawText("IS DISPLAYED ABOVE", warningColorTextLocation1.x, warningColorTextLocation1.y + 50, textPaint1);
        canvas.drawText("TAP EITHER SIDE BAR TO CHANGE WARNING COLOR", warningColorTextLocation2.x, warningColorTextLocation2.y, textPaint1);
        canvas.drawText("THEN YOU CAN DESTROY THE SHAPE", warningColorTextLocation2.x, warningColorTextLocation2.y + 50, textPaint1);
        canvas.drawText("CHANGING WARNING COLOR TO DESTROY SHAPES GIVES MORE POINTS", warningColorTextLocation2.x, warningColorTextLocation2.y + 200, textPaint2);
        canvas.drawText("CHANGING 2x IN A ROW GIVES EVEN MORE POINTS", warningColorTextLocation2.x, warningColorTextLocation2.y + 250, textPaint2);
        canvas.drawText("CHANGING 3x IN A ROW TRIGGERS COLOR STREAK REWARD", warningColorTextLocation2.x, warningColorTextLocation2.y + 300, textPaint2);
    }

    private void setupPaint(Paint paint, int size) {
        Typeface plain = Typeface.createFromAsset(Constants.CURRENT_CONTEXT.getAssets(), "undinaru.ttf");
        Typeface bold = Typeface.create(plain, Typeface.BOLD);
        paint.setTypeface(bold);
        paint.setColor(Color.WHITE);
        paint.setTextSize(size);
        paint.setTextAlign(Paint.Align.CENTER);
    }

    private void shapeInstruction(Canvas canvas) {
        if (shapeToDisplay != null)
            shapeToDisplay.draw(canvas);
        if (graveToDisplay != null) {
            Log.d("instruction", "update: x");
            graveToDisplay.draw(canvas);
        }
        canvas.drawText(textToDisplay, shapeTextLocation.x, shapeTextLocation.y, textPaint1);
    }

    private void nextInstruction() {
        //if (readyForNextInstruction)
            this.instructionIndex++;
    }

    // REFACTOR =============================
    @Override
    public void update() {
        /*
        if (shapeToDisplay != null && shapeToDisplay.getLives() < 1) {
            shapeToDisplay.playDeathSoundEffect();
            shapeToDisplay = null;
            nextInstruction();
        }
        else if (shapeToDisplay != null)
            shapeToDisplay.update();

        if (instructionsDone)
            ((Activity) Constants.CURRENT_CONTEXT).finish();
         */
        // shape killed and then grow grave
        if (shapeToDisplay != null && shapeToDisplay.getLives() < 1) {
            shapeToDisplay.playDeathSoundEffect();
            if (shapeToDisplay.getGravable()) {
                graveToDisplay = graveFactory.buildGrave(shapeToDisplay);

            }
            else {
                nextInstruction();
            }
            shapeToDisplay = null;
        }
        // shape not yet killed
        else if (shapeToDisplay != null) {
            shapeToDisplay.update();
        }

        else if (graveToDisplay != null)
            graveToDisplay.update();

        if (shapeToDisplay == null && graveToDisplay != null && graveToDisplay.graveDestroyed()) {
            graveToDisplay = null;
            nextInstruction();
        }


        if (instructionsDone)
            ((Activity) Constants.CURRENT_CONTEXT).finish();




        /*

        if (shapeToDisplay != null && shapeToDisplay.getLives() < 1) {
            shapeToDisplay.playDeathSoundEffect();
            if (shapeToDisplay.getGravable())
                graveToDisplay = graveFactory.buildGrave(shapeToDisplay);
            shapeToDisplay = null;
        }
        else if (graveToDisplay == null && shapeToDisplay == null)
            nextInstruction();
        else if (shapeToDisplay != null)
            shapeToDisplay.update();

        if (instructionsDone)
            ((Activity) Constants.CURRENT_CONTEXT).finish();

        if (graveToDisplay != null && graveToDisplay.graveDestroyed())
            graveToDisplay = null;
            */

    }

    @Override
    public void draw(Canvas canvas) {
        backgroundManager.draw(canvas);
        instructionToDraw(canvas);

        if (instructionIndex == 5 || instructionIndex == 8)
            drawContinueText(canvas);

        if (warningColor.getCurrentStrColor().equals(shapeToDisplay.getColor()) && instructionIndex != 4) {
            drawChangeWarningColorText(canvas);
        }
    }

    private void drawChangeWarningColorText(Canvas canvas) {
        canvas.drawText("TAP SIDE BAR TO CHANGE WARNING COLOR", shapeTextLocation.x, shapeTextLocation.y + 100, textPaint1);
    }

    @Override
    public void recieveTouch(MotionEvent event) {
        if (shapeToDisplay != null && shapeToDisplay.getBitmapHolder().contains((int) event.getX(), (int) event.getY())) {
            if (!warningColor.getCurrentStrColor().equals(shapeToDisplay.getColor()))
                shapeToDisplay.recieveTouch(event);
            /*
            if(shapeToDisplay instanceof Star || instructionIndex == 7) {
                if (!warningColor.getCurrentStrColor().equals(shapeToDisplay.getColor()))
                    shapeToDisplay.recieveTouch(event);
            }
            else
                shapeToDisplay.recieveTouch(event);
                */
        }

        if (((instructionIndex == 5) || instructionIndex == 8) && Constants.SHAPE_CLICK_AREA.contains((int) event.getX(), (int) event.getY()) && event.getAction()== MotionEvent.ACTION_DOWN) {
            nextInstruction();
        }
        /*
        if (Constants.SHAPE_CLICK_AREA.contains((int) event.getX(), (int) event.getY()) && event.getAction()== MotionEvent.ACTION_UP) {
            shapeToDisplay = null;
            nextInstruction();
        }*/
        else if (Constants.WARNING_COLOR_CLICK_AREA_LEFT.contains((int) event.getX(), (int) event.getY()))
            warningColor.recieveTouch(event, "left");
        else if(Constants.WARNING_COLOR_CLICK_AREA_RIGHT.contains((int) event.getX(), (int) event.getY()))
            warningColor.recieveTouch(event, "right");
    }
    // REFACTOR =============================

    private void drawContinueText(Canvas canvas) {
        canvas.drawText("TOUCH TO CONTINUE", continueTextLocation.x, continueTextLocation.y, continueTextPaint);
    }

    @Override
    public void terminate() {

    }

    @Override
    public void setGameOver(Boolean gameOver) {

    }

    @Override
    public Boolean getGameOver() {
        return null;
    }
}
