package com.thezs.fabianzachs.tapattack.Game;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by fabianzachs on 07/02/18.
 */

public interface Scene {

    public void update();
    public void draw(Canvas canvas);
    public void terminate();
    public void recieveTouch(MotionEvent event);
}
