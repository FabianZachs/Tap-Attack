package com.thezs.fabianzachs.tapattack.Game.GameUIComponents;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.ShapesManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabianzachs on 28/02/18.
 */

public class Streak {

    //private ShapesManager shapesManager;
    private final List<Integer> STREAK_COLORS = new ArrayList<>();
    private final int STREAK_POINTS_PER_COLOR = 5;

    private int streak;

    private int xLocation;
    private int yLocation;
    private Paint streakPaint;

    public Streak() {
        //this.shapesManager = shapesManager;
        //this.shapesManager.attachStreakObserver(this);
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
        streakPaint.setColor(STREAK_COLORS.get(0));
    }

    private void setupSTREAK_COLORS() {
        this.STREAK_COLORS.add(Color.argb(255,252,218,155));
        this.STREAK_COLORS.add(Color.argb(255,249,218,99));
        this.STREAK_COLORS.add(Color.argb(255,248,218,54));
        this.STREAK_COLORS.add(Color.argb(255,248,218,46));
        this.STREAK_COLORS.add(Color.argb(255,249,195,41));
        this.STREAK_COLORS.add(Color.argb(255,251,168,38));
        this.STREAK_COLORS.add(Color.argb(255,251,140,34));
        this.STREAK_COLORS.add(Color.argb(255,252,114,31));
        this.STREAK_COLORS.add(Color.argb(255,253,88,28));
        this.STREAK_COLORS.add(Color.argb(255,253,65,26));
        this.STREAK_COLORS.add(Color.argb(255,253,44,25));
        this.STREAK_COLORS.add(Color.argb(255,254,32,25));
    }


    public void draw(Canvas canvas) {
        int index = streak/STREAK_POINTS_PER_COLOR> STREAK_COLORS.size() - 1 ? STREAK_COLORS.size() - 1 : streak/STREAK_POINTS_PER_COLOR;
        streakPaint.setColor(STREAK_COLORS.get(index));
        canvas.drawText(getStringStreak(), xLocation, yLocation, streakPaint);
    }

    public void setStreak(int streak) {
        this.streak = streak;
    }

    public void incStreak(int amount) {
        this.streak += amount;
    }

    public void resetStreak() {
        this.streak = 0;
    }

    private String getStringStreak() {
        return streak == 0 ? "STREAK" : Integer.toString(streak);
    }
}
