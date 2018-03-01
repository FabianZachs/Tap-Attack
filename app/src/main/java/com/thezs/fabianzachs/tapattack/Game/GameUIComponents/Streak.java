package com.thezs.fabianzachs.tapattack.Game.GameUIComponents;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.thezs.fabianzachs.tapattack.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabianzachs on 28/02/18.
 */

public class Streak {

    private final List<Integer> STREAK_COLORS = new ArrayList<>();

    private int streak;

    private int xLocation;
    private int yLocation;
    private Paint streakPaint;

    public Streak() {
        this.streak = 0;
        setupSTREAK_COLORS();
        // TODO do this relative to screen height and width
        this.xLocation = 40;
        this.yLocation = 130;
        setupPaint();


    }

    private void setupPaint() {
        streakPaint = new Paint();

        // for font
        Typeface plain = Typeface.createFromAsset(Constants.CURRENT_CONTEXT.getAssets(), "undinaru.ttf");
        Typeface bold = Typeface.create(plain, Typeface.BOLD);
        streakPaint.setTypeface(bold);


        streakPaint.setTextSize(40);
        streakPaint.setColor(Color.RED);
    }

    private void setupSTREAK_COLORS() {
        this.STREAK_COLORS.add(Color.WHITE);
        this.STREAK_COLORS.add(Color.BLUE);
        this.STREAK_COLORS.add(Color.CYAN);
        this.STREAK_COLORS.add(Color.GREEN);
        this.STREAK_COLORS.add(Color.YELLOW);
        this.STREAK_COLORS.add(Color.RED);
    }

    public void update() {

    }

    public void draw(Canvas canvas) {
        canvas.drawText(getStringStreak(), xLocation, yLocation, streakPaint);
    }

    public void setStreak(int streak) {
        this.streak = streak;
    }

    private String getStringStreak() {
        return streak == 0 ? "STREAK" : Integer.toString(streak);
    }
}
