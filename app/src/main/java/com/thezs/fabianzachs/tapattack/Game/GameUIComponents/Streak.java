package com.thezs.fabianzachs.tapattack.Game.GameUIComponents;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabianzachs on 28/02/18.
 */

public class Streak implements GameUIComponent {

    private final List<Integer> STREAK_COLORS = new ArrayList<>();

    private int streak;

    private int xLocation;
    private int yLocation;
    private Paint scorePaint;

    public Streak() {
        setupSTREAK_COLORS();
        setupPaint();


    }

    private void setupPaint() {
        scorePaint.setTextSize(30);
    }

    private void setupSTREAK_COLORS() {
        this.STREAK_COLORS.add(Color.WHITE);
        this.STREAK_COLORS.add(Color.BLUE);
        this.STREAK_COLORS.add(Color.CYAN);
        this.STREAK_COLORS.add(Color.GREEN);
        this.STREAK_COLORS.add(Color.YELLOW);
        this.STREAK_COLORS.add(Color.RED);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {

    }

    public void setStreak(int streak) {
        this.streak = streak;
    }

    private String getStringStreak() {
        return streak == 0 ? "STREAK" : Integer.toString(streak);
    }
}
