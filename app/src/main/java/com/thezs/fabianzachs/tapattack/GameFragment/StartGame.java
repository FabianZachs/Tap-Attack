package com.thezs.fabianzachs.tapattack.GameFragment;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.thezs.fabianzachs.tapattack.Constants;

public class StartGame {

    private Paint paint;
    private int xPos = Constants.GAME_VIEW_WIDTH/2;
    private int yPos = (int) (7*Constants.GAME_VIEW_HEIGHT)/8;

    public StartGame() {
        this.paint = new Paint();
        setupPaint();
    }

    public void draw(Canvas canvas) {
        canvas.drawText("TOUCH TO START", xPos, yPos, paint);
    }

    private void setupPaint() {
        Typeface plain = Typeface.createFromAsset(Constants.CURRENT_CONTEXT.getAssets(), "undinaru.ttf");
        Typeface bold = Typeface.create(plain, Typeface.BOLD);
        paint.setTypeface(bold);
        paint.setColor(Color.WHITE);
        paint.setTextSize(80);
        paint.setTextAlign(Paint.Align.CENTER);
    }
}
