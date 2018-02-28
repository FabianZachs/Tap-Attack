package com.thezs.fabianzachs.tapattack.Game.GameUIComponents;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by fabianzachs on 28/02/18.
 */

public class Score implements GameUIComponent {

    private int score;

    private int xLocation;
    private int yLocation;
    private Paint scorePaint;

    public Score() {
        this.score = 0;
        this.xLocation = 10;
        this.yLocation = 10;
        setupPaint();

    }

    private void setupPaint() {
        scorePaint = new Paint();
        scorePaint.setTextSize(30);
        scorePaint.setColor(Color.RED);
    }


    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawText(getStringScore(), xLocation, yLocation, scorePaint);

    }

    public void setScore(int score) {
        this.score = score;
    }


    private String getStringScore() {
        return score == 0 ? "SCORE" : Integer.toString(score);
    }

}
