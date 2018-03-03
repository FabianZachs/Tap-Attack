package com.thezs.fabianzachs.tapattack.Game.GraveObjects;

import android.graphics.Canvas;

import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.Cross;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.ShapeObject;

/**
 * Created by fabianzachs on 03/03/18.
 */

public class CrossGrave extends GraveObject {

    private final float BLINK_TIME = 0.5f;
    private long timeOfBlinkSwitch;
    private boolean blinkOn;

    public CrossGrave(Cross shapeToCreateGraveFrom) {
        super(shapeToCreateGraveFrom);
        setDURATION(5);
        this.blinkOn = false;
        timeOfBlinkSwitch = System.currentTimeMillis();

    }

    public void draw(Canvas canvas) {
        if (System.currentTimeMillis() - timeOfBlinkSwitch > BLINK_TIME * 1000) {
            blinkOn = blinkOn ? false : true;
            timeOfBlinkSwitch = System.currentTimeMillis();
        }
        if (blinkOn)
            canvas.drawBitmap(getGraveImg(),null,getBitmapHolder(), getPaint());

    }
}
