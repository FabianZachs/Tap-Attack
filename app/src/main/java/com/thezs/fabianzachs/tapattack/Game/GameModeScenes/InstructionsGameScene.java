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
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.WarningColor;
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
    private Point shapeLocation;
    private String textToDisplay = null;
    private Point textLocation;
    // todo make rect and paint for shape global and paint for text description

    public InstructionsGameScene(CentralGameCommunication mediator) {
        this.mediator = mediator;
        mediator.resetInitTime();
        this.backgroundManager = new BackgroundManager();
        this.shapeColorPicker = new ShapeColorPicker(mediator);
        this.warningColor = new WarningColor(mediator, ThemesManager.getStrColors(Constants.CURRENT_THEME), ThemesManager.getIntColors(Constants.CURRENT_THEME));
        this.shapeBuilder = new ShapeBuilder();
        mediator.addObject(warningColor);
        mediator.addObject(shapeColorPicker);


        //this.shapeLocation = new Point(Constants.SCREEN_HEIGHT/3 - Constants.SHAPE_WIDTH/2, Constants.SCREEN_WIDTH/2 );
        this.shapeLocation = new Point(Constants.SCREEN_WIDTH/2, Constants.SCREEN_HEIGHT/3 );
        this.textLocation = new Point(Constants.SCREEN_WIDTH/2, (Constants.SCREEN_HEIGHT/3) * 2 );


    }


    private void instructionToDraw(Canvas canvas) {
        switch (instructionIndex) {
            case 0:
                if (shapeToDisplay == null) {
                    shapeToDisplay = shapeBuilder.buildShape("circle", shapeColorPicker.getColorForShape(), shapeLocation, new Paint(), new Rect(), mediator, "UP");
                    textToDisplay = "TAP CIRCLES ONCE";
                }
                shapeInstruction(canvas);
                return;
            case 1:
                if (shapeToDisplay == null) {
                    shapeToDisplay = shapeBuilder.buildShape("square", shapeColorPicker.getColorForShape(), shapeLocation, new Paint(), new Rect(), mediator, "UP");
                    textToDisplay = "TAP SQUARES TWICE RELATIVELY QUICKLY";
                }
                shapeInstruction(canvas);
                return;
            case 2:
                if (shapeToDisplay == null) {
                    shapeToDisplay = shapeBuilder.buildShape("arrow", shapeColorPicker.getColorForShape(), shapeLocation, new Paint(), new Rect(), mediator, "RIGHT");
                    textToDisplay = "FLICK ARROWS IN THE DIRECTION IT POINTS";
                }
                shapeInstruction(canvas);
                return;
            case 3:
                if (shapeToDisplay == null) {
                    shapeToDisplay = shapeBuilder.buildShape("arrow", shapeColorPicker.getColorForShape(), shapeLocation, new Paint(), new Rect(), mediator, "LEFT");
                    textToDisplay = "FLICK ARROWS IN THE DIRECTION IT POINTS";
                }
                shapeInstruction(canvas);
                return;
            case 4:
                if (shapeToDisplay == null) {
                    shapeToDisplay = shapeBuilder.buildShape("cross", shapeColorPicker.getColorForShape(), shapeLocation, new Paint(), new Rect(), mediator, "RIGHT");
                    textToDisplay = "NEVER TAP THE X... TAP IT TO CONTINUE :P";
                }
                shapeInstruction(canvas);
                return;
            case 5:
                warningColorInstruction(canvas);
                return;
            case 6:
                if (shapeToDisplay == null) {
                    shapeToDisplay = shapeBuilder.buildShape("star", shapeColorPicker.getColorForShape(), shapeLocation, new Paint(), new Rect(), mediator, "RIGHT");
                    textToDisplay = "TAP STAR ONLY WHEN NOT WARNING COLOR";
                }
                shapeInstruction(canvas);
                return;

        }
        instructionsDone = true;
    }

    private void warningColorInstruction(Canvas canvas) {
        //canvas.drawText("THIS DISPLAY THE COLOR OF SHAPES YOU CANNOT TOUCH", );
    }

    private void shapeInstruction(Canvas canvas) {
        // todo find x and y for shape 1/3 for y
        //ShapeObject newShape = shapeBuilder.buildShape(getShape(), getColor(), newShapeLocation, paint, bitmapHolder, mediator, getDirection());
        shapeToDisplay.draw(canvas);
        //Log.d("shapetodis", "shapeInstruction: " + shapeToDisplay);
        // todo find x and y for text depending on shape location 2/3 for y
        // todo center for x
        //canvas.drawText(textToDisplay, textLocation.x, textLocation.y, new Paint());
        drawDescriptionText(canvas);
    }

    private void drawDescriptionText(Canvas canvas) {
        Paint startGameTextPaint = new Paint();
        Typeface plain = Typeface.createFromAsset(Constants.CURRENT_CONTEXT.getAssets(), "undinaru.ttf");
        Typeface bold = Typeface.create(plain, Typeface.BOLD);
        startGameTextPaint.setTypeface(bold);
        startGameTextPaint.setColor(Color.WHITE);
        startGameTextPaint.setTextSize(40);
        startGameTextPaint.setTextAlign(Paint.Align.CENTER);
        //int xPos = (canvas.getWidth() / 2);
        //int yPos = (int) ((canvas.getHeight() / 2) - ((startGameTextPaint.descent() + startGameTextPaint.ascent()) / 2)) ; center
        //int yPos = (int) (7*canvas.getHeight()) /8;
        //((textPaint.descent() + textPaint.ascent()) / 2) is the distance from the baseline to the center.
        canvas.drawText(textToDisplay, textLocation.x, textLocation.y, startGameTextPaint);
    }


    private void nextInstruction() {
        this.instructionIndex++;
    }

    @Override
    public void update() {
        if (shapeToDisplay != null && shapeToDisplay.getLives() < 1) {
            shapeToDisplay.playDeathSoundEffect();
            shapeToDisplay = null;
            nextInstruction();
        }
        if (instructionsDone)
            ((Activity) Constants.CURRENT_CONTEXT).finish();

        if (shapeToDisplay != null)
            shapeToDisplay.update();
    }

    @Override
    public void draw(Canvas canvas) {
        backgroundManager.draw(canvas);
        instructionToDraw(canvas);

        drawContinueText(canvas);


    }

    private void drawContinueText(Canvas canvas) {
        Paint startGameTextPaint = new Paint();
        Typeface plain = Typeface.createFromAsset(Constants.CURRENT_CONTEXT.getAssets(), "undinaru.ttf");
        Typeface bold = Typeface.create(plain, Typeface.BOLD);
        startGameTextPaint.setTypeface(bold);
        startGameTextPaint.setColor(Color.WHITE);
        startGameTextPaint.setTextSize(100);
        startGameTextPaint.setTextAlign(Paint.Align.CENTER);
        int xPos = (canvas.getWidth() / 2);
        //int yPos = (int) ((canvas.getHeight() / 2) - ((startGameTextPaint.descent() + startGameTextPaint.ascent()) / 2)) ; center
        int yPos = (int) (7*canvas.getHeight()) /8;
        //((textPaint.descent() + textPaint.ascent()) / 2) is the distance from the baseline to the center.
        canvas.drawText("TOUCH TO CONTINUE", xPos, yPos, startGameTextPaint);

    }

    @Override
    public void terminate() {

    }

    @Override
    public void recieveTouch(MotionEvent event) {
        if (shapeToDisplay != null && shapeToDisplay.getBitmapHolder().contains((int) event.getX(), (int) event.getY())) {
            shapeToDisplay.recieveTouch(event);
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

    @Override
    public void setGameOver(Boolean gameOver) {

    }

    @Override
    public Boolean getGameOver() {
        return null;
    }
}
