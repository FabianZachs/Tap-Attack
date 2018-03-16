package com.thezs.fabianzachs.tapattack.Game.BackgroundHandlers;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.BackgroundHandlers.Backgrounds.GameBackground;
import com.thezs.fabianzachs.tapattack.Game.BackgroundHandlers.Backgrounds.Triangular.BlueTriangular;

/**
 * Created by fabianzachs on 16/03/18.
 */

public class BackgroundManager {

    private GameBackground gameBackground;
    private Rect entireScreenRect;

    public BackgroundManager(String backgroundID) {
        setupGameBackground(backgroundID);
        this.entireScreenRect = new Rect(0,0,Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
    }

    private void setupGameBackground(String backgroundID) {

        switch (backgroundID) {
            case "backgroundtriangleblue":
                gameBackground = new BlueTriangular();
                return;
            case "OTHER BACKGROUNDS":
                return;

        }
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(gameBackground.getBackground(), null,
                entireScreenRect, null);
    }
}
