package com.thezs.fabianzachs.tapattack.Game.GameUIComponents;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.ShapesManager;
import com.thezs.fabianzachs.tapattack.R;

/**
 * Created by fabianzachs on 28/02/18.
 */

public class Score {

    //private ShapesManager shapesManager;   // TODO why is this attribute needed. Can't we just pass in as parameter and attach this

    private int score;

    private int xLocation;
    private int yLocation;
    private Paint scorePaint;

    public Score() {
        //this.shapesManager = shapesManager;
        //this.shapesManager.attachScoreObserver(this);
        this.score = 0;
        // TODO do this relative to screen height and width
        this.xLocation = 40;
        this.yLocation = 80;
        setupPaint();

    }

    private void setupPaint() {
        scorePaint = new Paint();

        // for font
        Typeface plain = Typeface.createFromAsset(Constants.CURRENT_CONTEXT.getAssets(), "undinaru.ttf");
        Typeface bold = Typeface.create(plain, Typeface.BOLD);
        scorePaint.setTypeface(bold);


        scorePaint.setTextSize(40);
        scorePaint.setColor(Color.WHITE);
    }



    public void draw(Canvas canvas) {
        canvas.drawText(getStringScore(), xLocation, yLocation, scorePaint);

    }

    public void setScore(int score) {
        this.score = score;
    }

    public void incScore(int amount) {
        this.score += amount;
    }


    private String getStringScore() {
        return score == 0 ? "SCORE" : Integer.toString(score);
    }

}
