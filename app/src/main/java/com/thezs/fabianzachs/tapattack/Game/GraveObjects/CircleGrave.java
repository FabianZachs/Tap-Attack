package com.thezs.fabianzachs.tapattack.Game.GraveObjects;

import android.graphics.Canvas;

import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.Circle;

/**
 * Created by fabianzachs on 03/03/18.
 */

public class CircleGrave extends GraveObject {

    public CircleGrave(Circle circle) {
        super(circle);
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        //canvas.drawBitmap(getGraveImg(), null, getBitmapHolder(), getPaint());
    }

}
