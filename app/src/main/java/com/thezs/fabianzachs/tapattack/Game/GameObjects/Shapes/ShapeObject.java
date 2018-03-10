package com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.ProgressBar;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.Score;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.Streak;
import com.thezs.fabianzachs.tapattack.Game.Mediator.CentralGameCommunication;
import com.thezs.fabianzachs.tapattack.R;

import static com.thezs.fabianzachs.tapattack.Game.MainThread.canvas;

/**
 * Created by fabianzachs on 07/02/18.
 */

public abstract class ShapeObject {

    protected CentralGameCommunication mediator;

    public int PROGRESSBAR_REDUCTION_WITH_INCORRECT_TOUCH = -35;



    private Paint alphaPaint;
    private Bitmap[] shapeImages;
    private float durationAlive;
    private Rect bitmapHolder;
    private long initTime;
    private String color;
    private int lives;
    private Point centerLocation;

    private int progressBarAddition;
    private int stateAnimation = 0;
    private int points;

    private boolean graveAble;

    private GestureDetectorCompat mDetector;


    public ShapeObject(float durationAlive, String color, Point centerLocation, Bitmap shapeImg, Bitmap shapeClickImg, Paint paint, Rect rect, CentralGameCommunication mediator) {
        this.mediator = mediator;
        this.alphaPaint = paint;
        this.graveAble = true;
        this.durationAlive = durationAlive;
        this.color = color;
        this.centerLocation = centerLocation;
        this.points = 1;
        this.initTime = System.currentTimeMillis();
        this.progressBarAddition = 10;
        this.shapeImages = new Bitmap[] {shapeImg, shapeClickImg};

        rect.set((centerLocation.x - (Constants.SHAPE_WIDTH/2)),
                (centerLocation.y - (Constants.SHAPE_HEIGHT/2)),
                (centerLocation.x + (Constants.SHAPE_WIDTH/2)),
                (centerLocation.y + (Constants.SHAPE_HEIGHT/2)));

        setBitmapHolder(rect);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(getCurrentShapeImg(), null, getBitmapHolder(),getAlphaPaint());
    }

    public abstract void update();

    public boolean isTimedOut() {
        return (System.currentTimeMillis() - initTime > durationAlive * 1000);
    }

    public void reduceLives() {
        this.lives -= 1;
    }

    // SETTERS & GETTERSgameBoundary
    public void setmDetector(GestureDetectorCompat mDetector) {
        this.mDetector = mDetector;
    }

    public long getTimeLeft() {
        return  (int) (getDurationAlive() * 1000) - (System.currentTimeMillis() - getInitTime());
    }

    public Paint getAlphaPaint() {
        alphaPaint.setAlpha(255);
        if (getTimeLeft() < 1000 )
            alphaPaint.setAlpha( (int) ((255 * getTimeLeft())/1000));
        return alphaPaint;
    }

    public Paint getPaintObj() {
        return this.alphaPaint;
    }

    public Point getCenterLocation() {
        return this.centerLocation;
    }

    public void updateCenterLocation(int x, int y) {
        this.centerLocation.set(x,y);
        updateBitmapHolderLocation();
    }

    private void updateBitmapHolderLocation() {
        Rect newBitmapHolder = getBitmapHolder();
        newBitmapHolder.left = (int) (centerLocation.x - (Constants.SHAPE_WIDTH/2));
        newBitmapHolder.top = (int) (centerLocation.y - (Constants.SHAPE_HEIGHT/2));
        newBitmapHolder.right = (int) (centerLocation.x + (Constants.SHAPE_WIDTH/2));
        newBitmapHolder.bottom = (int) (centerLocation.y + (Constants.SHAPE_HEIGHT/2));

        setBitmapHolder(newBitmapHolder);
    }

    public void setShapeImages(int index, Bitmap img) {
        this.shapeImages[index] = img;
    }

    public Bitmap getCurrentShapeImg() {
        return this.shapeImages[getState()];
    }

    public Bitmap getShapeImg() {
        return this.shapeImages[0];
    }

    public Bitmap getShapeClickImg() {
        return this.shapeImages[1];
    }

    public void setProgressBarAddition(int progressBarAddition) {
        this.progressBarAddition = progressBarAddition;
    }

    public int getProgressBarAddition() {
        return this.progressBarAddition;
    }

    public void setBitmapHolder(Rect rect) {
        this.bitmapHolder = rect;
    }

    public Rect getBitmapHolder() {
        return this.bitmapHolder;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getLives() {
        return this.lives;
    }

    public void setDurationAlive(float durationAlive) {
        this.durationAlive = durationAlive;
    }

    public float getDurationAlive() {
        return this.durationAlive;
    }

    public void setInitTime(long initTime) {
        this.initTime = initTime;
    }

    public long getInitTime() {
        return this.initTime;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPoints() {
        return this.points;
    }

    public void setState(int state) {
        this.stateAnimation = state;
    }

    public int getState() {
        return this.stateAnimation;
    }

    public void setGraveAble(boolean bool) {
        this.graveAble = bool;
    }

    public boolean getGravable() {
        return this.graveAble;
    }

    public void recieveTouch(MotionEvent event) {
        this.mDetector.onTouchEvent(event);
    }

}
