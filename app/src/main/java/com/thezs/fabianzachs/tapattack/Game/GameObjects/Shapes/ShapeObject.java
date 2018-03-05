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
import com.thezs.fabianzachs.tapattack.R;

import static com.thezs.fabianzachs.tapattack.Game.MainThread.canvas;

/**
 * Created by fabianzachs on 07/02/18.
 */

public abstract class ShapeObject {

    public int PROGRESSBAR_REDUCTION_WITH_INCORRECT_TOUCH = -15;

    private boolean graveAble;

    private Score scoreObserver;
    private Streak streakObserver;
    private ProgressBar progressBarObserver;

    private int progressBarAddition;
    private long distructionTime;
    private Bitmap[] shapeImages;
    private int stateAnimation = 0;
    private float durationAlive;
    private Rect bitmapHolder;  // rectangle to hold bitmap ?? needed??
    private long initTime;
    private String color;  // will be used to find correct bitmap img
    private int points;
    private int lives;
    private Point centerLocation;


    private GestureDetectorCompat mDetector;


    // TODO
    // for arrow shape make at least a little direction movement neccessary (small flick)  , so that
    // a simple press (with fractional vertical/ horizontal movement doesnt trigger fail/sucess


    public void draw(Canvas canvas) {
        canvas.drawBitmap(getCurrentShapeImg(), null, getBitmapHolder(),getAlphaPaint());
    }

    public abstract void update();

    public ShapeObject(float durationAlive, String color, Point centerLocation, Bitmap shapeImg, Bitmap shapeClickImg) {
        this.graveAble = true;
        this.durationAlive = durationAlive;
        this.color = color;
        this.centerLocation = centerLocation;
        this.points = 1; // all shapes are worth one point?
        this.initTime = System.currentTimeMillis();
        this.progressBarAddition = progressBarAddition;
        this.shapeImages = new Bitmap[] {shapeImg, shapeClickImg};
        setBitmapHolder(new Rect((int) (centerLocation.x - (Constants.SHAPE_WIDTH/2)), (int) (centerLocation.y - (Constants.SHAPE_HEIGHT/2)),
                (int) (centerLocation.x + (Constants.SHAPE_WIDTH/2)), (int) (centerLocation.y + (Constants.SHAPE_HEIGHT/2))));
    }


    // only for timing out
    public boolean isTimedOut() {
        //return !(System.currentTimeMillis() - initTime > durationAlive * 1000) && lives > 0;
        return (System.currentTimeMillis() - initTime > durationAlive * 1000);
    }

    public void attachScoreObserver(Score scoreObserver) {
        this.scoreObserver = scoreObserver;
    }

    public void attachStreakObserver(Streak streakObserver) {
        this.streakObserver = streakObserver;
    }


    public void attachProgressBarObserver(ProgressBar progressBarObserver) {
        this.progressBarObserver = progressBarObserver;
    }

    public void attachAllObservers(Score scoreObserver, Streak streakObserver, ProgressBar progressBarObserver) {
        attachScoreObserver(scoreObserver);
        attachStreakObserver(streakObserver);
        attachProgressBarObserver(progressBarObserver);
    }

    public Score getScoreObserver() {
        return this.scoreObserver;
    }

    public Streak getStreakObserver() {
        return this.streakObserver;
    }

    public ProgressBar getProgressBarObserver() {
        return this.progressBarObserver;
    }





    public void reduceLives() {
        this.lives -= 1;
    }

    // SETTERS & GETTERSgameBoundary

    public GestureDetectorCompat getMDetector() {
        return mDetector;
    }

    public void setmDetector(GestureDetectorCompat mDetector) {
        this.mDetector = mDetector;
    }

    public long getTimeLeft() {
        return  (int) (getDurationAlive() * 1000) - (System.currentTimeMillis() - getInitTime());
    }

    public Paint getAlphaPaint() {
        Paint alphaPaint = new Paint();
        //alphaPaint.setAlpha(255);

        /*
        // ERROR if time is below 150 - shape flashes
        if (150 < getTimeLeft() && getTimeLeft() < 1000 ) {
            alphaPaint.setAlpha( (int) ((255 * getTimeLeft())/1000));
        }
        else if (getTimeLeft() < 150)
            alphaPaint.setAlpha(0);
*/
        if (getTimeLeft() < 1000 ) {
            alphaPaint.setAlpha( (int) ((255 * getTimeLeft())/1000));
        }
        return alphaPaint;
    }

    public Point getCenterLocation() {
        return this.centerLocation;
    }

    public void setCenterLocation(int x, int y) {
        this.centerLocation.set(x,y);
        updateBitmapHolderLocation();
    }

    private void updateBitmapHolderLocation() {
        // TODO create method will update the bitmapholder by using the current set centerLocation
        Rect newBitmapHolder = getBitmapHolder();
        newBitmapHolder.left = (int) (centerLocation.x - (Constants.SHAPE_WIDTH));
        newBitmapHolder.top = (int) (centerLocation.y - (Constants.SHAPE_HEIGHT));
        newBitmapHolder.right = (int) (centerLocation.x + (Constants.SHAPE_WIDTH));
        newBitmapHolder.bottom = (int) (centerLocation.y + (Constants.SHAPE_HEIGHT));

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
