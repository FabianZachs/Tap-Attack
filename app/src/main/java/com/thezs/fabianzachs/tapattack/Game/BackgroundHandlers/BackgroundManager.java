package com.thezs.fabianzachs.tapattack.Game.BackgroundHandlers;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.BackgroundHandlers.Backgrounds.GameBackground;
import com.thezs.fabianzachs.tapattack.Game.BackgroundHandlers.Backgrounds.Sky;
import com.thezs.fabianzachs.tapattack.Game.BackgroundHandlers.Backgrounds.Triangular.BlueTriangular;
import com.thezs.fabianzachs.tapattack.R;

/**
 * Created by fabianzachs on 16/03/18.
 */

public class BackgroundManager {

    private GameBackground gameBackground2;
    private GameBackground gameBackground;
    private Rect entireScreenRect;

    public BackgroundManager() {
        // todo get player sharedprefs for unlocks and find the currently selected background from the database
        // todo get the drawable resource file from the database
        // now we have drawable resouirce and the 2 warning color holders
        int[] colors = {0xff0040ff,0xff00bcfe};
        this.gameBackground = new GameBackground(R.drawable.backgroundtrianglepointy, colors);
        this.entireScreenRect = new Rect(0,0,Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

    }

    public BackgroundManager(String backgroundID) {



        setupGameBackground(backgroundID);
        this.entireScreenRect = new Rect(0,0,Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
    }

    private void setupGameBackground(String backgroundID) {

        switch (backgroundID) {
            case "tri-blue":
            //case "backgroundtriangleblue":
                gameBackground2 = new BlueTriangular();
                return;
            case "sky":
                gameBackground2 = new Sky();
                return;
            case "OTHER BACKGROUNDS":
                return;

        }
    }

    public Bitmap getBackground() {
        return gameBackground.getBackground();
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(gameBackground.getBackground(), null,
                entireScreenRect, null);
    }
}
