package com.thezs.fabianzachs.tapattack.Game.GameUIComponents;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.ShapesManager;
import com.thezs.fabianzachs.tapattack.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabianzachs on 28/02/18.
 */

public class Score {

    //private ShapesManager shapesManager;   // TODO why is this attribute needed. Can't we just pass in as parameter and attach this
    private final int SCORE_POINTS_PER_COLOR = 15;
    private final List<Integer> SCORE_COLORS = new ArrayList<>();

    private int score;

    private int xLocation;
    private int yLocation;
    private Paint scorePaint;

    public Score() {
        //this.shapesManager = shapesManager;
        //this.shapesManager.attachScoreObserver(this);
        this.score = 0;
        // TODO do this relative to screen height and width
        this.xLocation = 140;
        //this.yLocation = 80;
        this.yLocation =(Constants.SCREEN_HEIGHT/16 ) ;
        setupSCORE_COLORS();
        setupPaint();

    }

    private void setupSCORE_COLORS() {
        this.SCORE_COLORS.add(Color.argb(255,255,255,255));
        this.SCORE_COLORS.add(Color.argb(255,254,237,206));
        this.SCORE_COLORS.add(Color.argb(255,252,218,155));
        this.SCORE_COLORS.add(Color.argb(255,249,218,99));
        this.SCORE_COLORS.add(Color.argb(255,248,218,54));
        this.SCORE_COLORS.add(Color.argb(255,248,218,46));
        this.SCORE_COLORS.add(Color.argb(255,249,195,41));
        this.SCORE_COLORS.add(Color.argb(255,251,168,38));
        this.SCORE_COLORS.add(Color.argb(255,251,140,34));
        this.SCORE_COLORS.add(Color.argb(255,252,114,31));
        this.SCORE_COLORS.add(Color.argb(255,253,88,28));
        this.SCORE_COLORS.add(Color.argb(255,253,65,26));
        this.SCORE_COLORS.add(Color.argb(255,253,44,25));
        this.SCORE_COLORS.add(Color.argb(255,254,32,25));
    }

    private void setupPaint() {
        scorePaint = new Paint();

        // for font
        Typeface plain = Typeface.createFromAsset(Constants.CURRENT_CONTEXT.getAssets(), "undinaru.ttf");
        Typeface bold = Typeface.create(plain, Typeface.BOLD);
        scorePaint.setTypeface(bold);

        scorePaint.setTextAlign(Paint.Align.CENTER);

        scorePaint.setTextSize(80);
        scorePaint.setColor(Color.WHITE);
    }



    public void draw(Canvas canvas) {
        int index = score / SCORE_POINTS_PER_COLOR> SCORE_COLORS.size() - 1 ? SCORE_COLORS.size() - 1 : score/SCORE_POINTS_PER_COLOR;
        scorePaint.setColor(SCORE_COLORS.get(index));
        canvas.drawText(getStringScore(), xLocation, yLocation, scorePaint);

    }

    public void setScore(int score) {
        this.score = score;
    }

    public void incScore(int amount) {
        this.score += amount;
    }

    public int getScore() {
        return score;
    }

    private String getStringScore() {
        return score == 0 ? "SCORE" : Integer.toString(score);
    }

}
