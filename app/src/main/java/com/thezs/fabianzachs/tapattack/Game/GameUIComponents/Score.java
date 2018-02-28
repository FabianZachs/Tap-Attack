package com.thezs.fabianzachs.tapattack.Game.GameUIComponents;

import android.graphics.Canvas;

/**
 * Created by fabianzachs on 28/02/18.
 */

public class Score implements GameUIComponent {

    private int score;

    public Score() {
        this.score = 0;
    }


    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {

    }

    public void setScore(int score) {
        this.score = score;
    }

}
