package com.thezs.fabianzachs.tapattack.Game.GameModeScenes;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.MotionEvent;

import com.thezs.fabianzachs.tapattack.Animation.Themes.ThemesManager;
import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.BackgroundHandlers.BackgroundManager;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.ShapeBuilder;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.ShapeColorPicker;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.ShapeObject;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.Star;
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
    private Point shapeTextLocation;

    private Paint shapePaint = new Paint();
    private Point warningColorTextLocation1 = new Point(Constants.SCREEN_WIDTH/2, Constants.SCREEN_HEIGHT/8);
    private Point warningColorTextLocation2 = new Point(Constants.SCREEN_WIDTH/2, Constants.SCREEN_HEIGHT/2);
    private Point warningColorTextLocation3 = new Point(Constants.SCREEN_WIDTH/2, Constants.SCREEN_HEIGHT/2 + 50);
    private Point warningColorTextLocation4 = new Point(Constants.SCREEN_WIDTH/2, Constants.SCREEN_HEIGHT/2 + 100);
    private Rect shapeRect = new Rect();
    private Paint textPaint1 = new Paint();
    private Paint textPaint2 = new Paint();
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
        this.shapeTextLocation = new Point(Constants.SCREEN_WIDTH/2, (Constants.SCREEN_HEIGHT/3) * 2 );
        setupPaint(textPaint1, 40);
        setupPaint(textPaint2, 30);


    }


    private void instructionToDraw(Canvas canvas) {
        switch (instructionIndex) {
            case 0:
                if (shapeToDisplay == null) {
                    shapeToDisplay = shapeBuilder.buildShape("circle", shapeColorPicker.getColorForShape(), shapeLocation, shapePaint, shapeRect, mediator, "UP");
                    textToDisplay = "TAP CIRCLES ONCE";
                }
                shapeInstruction(canvas);
                return;
            case 1:
                if (shapeToDisplay == null) {
                    shapeToDisplay = shapeBuilder.buildShape("square", shapeColorPicker.getColorForShape(), shapeLocation, shapePaint, shapeRect, mediator, "UP");
                    textToDisplay = "TAP SQUARES TWICE RELATIVELY QUICKLY";
                }
                shapeInstruction(canvas);
                return;
            case 2:
                if (shapeToDisplay == null) {
                    shapeToDisplay = shapeBuilder.buildShape("arrow", shapeColorPicker.getColorForShape(), shapeLocation, shapePaint, shapeRect, mediator, "RIGHT");
                    textToDisplay = "FLICK ARROWS IN THE DIRECTION IT POINTS";
                }
                shapeInstruction(canvas);
                return;
            case 3:
                if (shapeToDisplay == null) {
                    shapeToDisplay = shapeBuilder.buildShape("arrow", shapeColorPicker.getColorForShape(), shapeLocation, shapePaint, shapeRect, mediator, "LEFT");
                    textToDisplay = "FLICK ARROWS IN THE DIRECTION IT POINTS";
                }
                shapeInstruction(canvas);
                return;
            case 4:
                if (shapeToDisplay == null) {
                    shapeToDisplay = shapeBuilder.buildShape("cross", shapeColorPicker.getColorForShape(), shapeLocation, shapePaint, shapeRect, mediator, "RIGHT");
                    textToDisplay = "NEVER TAP THE X... TAP IT TO CONTINUE :P";
                }
                shapeInstruction(canvas);
                return;
            case 5:
                warningColorInstruction(canvas);
                return;
            case 6:
                if (shapeToDisplay == null) {
                    shapeToDisplay = shapeBuilder.buildShape("star", shapeColorPicker.getColorForShape(), shapeLocation, shapePaint, shapeRect, mediator, "RIGHT");
                    textToDisplay = "TAP STAR ONLY WHEN NOT WARNING COLOR";
                }
                shapeInstruction(canvas);
                return;
            case 7:
                if (shapeToDisplay == null) {
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
        // todo find x and y for shape 1/3 for y
        //ShapeObject newShape = shapeBuilder.buildShape(getShape(), getColor(), newShapeLocation, paint, bitmapHolder, mediator, getDirection());
        shapeToDisplay.draw(canvas);
        //Log.d("shapetodis", "shapeInstruction: " + shapeToDisplay);
        // todo find x and y for text depending on shape location 2/3 for y
        // todo center for x
        //canvas.drawText(textToDisplay, shapeTextLocation.x, shapeTextLocation.y, new Paint());
        //drawDescriptionText(canvas);
        canvas.drawText(textToDisplay, shapeTextLocation.x, shapeTextLocation.y, textPaint1);
    }

    /*
    private void drawDescriptionText(Canvas canvas) {
        Paint descriptionPaint = new Paint();
        Typeface plain = Typeface.createFromAsset(Constants.CURRENT_CONTEXT.getAssets(), "undinaru.ttf");
        Typeface bold = Typeface.create(plain, Typeface.BOLD);
        descriptionPaint.setTypeface(bold);
        descriptionPaint.setColor(Color.WHITE);
        descriptionPaint.setTextSize(40);
        descriptionPaint.setTextAlign(Paint.Align.CENTER);
        //int xPos = (canvas.getWidth() / 2);
        //int yPos = (int) ((canvas.getHeight() / 2) - ((startGameTextPaint.descent() + startGameTextPaint.ascent()) / 2)) ; center
        //int yPos = (int) (7*canvas.getHeight()) /8;
        //((textPaint.descent() + textPaint.ascent()) / 2) is the distance from the baseline to the center.
        canvas.drawText(textToDisplay, shapeTextLocation.x, shapeTextLocation.y, descriptionPaint);
    }
    */


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

        if ((instructionIndex >= 5 && instructionIndex < 6) || instructionIndex == 8)
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
            if(shapeToDisplay instanceof Star || instructionIndex == 7) {
                if (!warningColor.getCurrentStrColor().equals(shapeToDisplay.getColor()))
                    shapeToDisplay.recieveTouch(event);
            }
            else
                shapeToDisplay.recieveTouch(event);
        }

        if (((instructionIndex >= 5 && instructionIndex < 6) || instructionIndex == 8) && Constants.SHAPE_CLICK_AREA.contains((int) event.getX(), (int) event.getY()) && event.getAction()== MotionEvent.ACTION_DOWN) {
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

    @Override
    public void setGameOver(Boolean gameOver) {

    }

    @Override
    public Boolean getGameOver() {
        return null;
    }
}
