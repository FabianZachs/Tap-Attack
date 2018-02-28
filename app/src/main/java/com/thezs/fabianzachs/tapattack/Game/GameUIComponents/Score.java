package com.thezs.fabianzachs.tapattack.Game.GameUIComponents;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.R;

/**
 * Created by fabianzachs on 28/02/18.
 */

public class Score implements GameUIComponent {

    private int score;

    private int xLocation;
    private int yLocation;
    private Paint scorePaint;

    public Score() {
        this.score = 1;
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
