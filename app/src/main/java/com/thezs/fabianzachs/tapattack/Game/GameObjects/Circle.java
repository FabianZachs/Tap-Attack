package com.thezs.fabianzachs.tapattack.Game.GameObjects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import com.thezs.fabianzachs.tapattack.Animation.Animation;
import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.R;

/**
 * Created by fabianzachs on 07/02/18.
 */

public class Circle extends ShapeObject {

    //private Animation animation;  make the animation

    public Circle(float durationAlive, String color, Point centerLocation) {
       // call super(durationAlive, color) then in super also make the rect to hold bitmap
        super(durationAlive, color, centerLocation);
        setLives(1);
        setProgressBarAddition(10);
        setBitmapHolder(new Rect((int) (centerLocation.x - (0.5f * Constants.SHAPE_WIDTH)), (int) (centerLocation.y - (.5 * Constants.SHAPE_HEIGHT)),
                (int) (centerLocation.x + (0.5f * Constants.SHAPE_WIDTH)), (int) (centerLocation.y + (0.5f) * Constants.SHAPE_HEIGHT)));


        // TODO use String color to get correct R.drawable.bitmap
        // animation setup -- should this be done individually for each shape?
        BitmapFactory bf = new BitmapFactory();
        Bitmap idleImg = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.blueneon);
        Bitmap onTouchImg = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.blueneonclick);

        // any animation time is fine, itll just switch to the same img
        idle = new Animation(new Bitmap[] {idleImg}, 2);
        onTouch = new Animation(new Bitmap[] {onTouchImg}, 2);
    }


/*
    @Override
    public void recieveTouch(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // play onClick animation
            //reduceLives();
        }

    }
*/



    @Override
    public void draw(Canvas canvas) {
        // test drawing shape to canvas
        Paint paint = new Paint();
        paint.setColor(Color.RED);

        canvas.drawRect(getBitmapHolder(), paint);
    }

    @Override
    public void update() {

    }

    // ANIMATIONS
    private Animation idle;
    private Animation onTouch;
    private Animation onDisappear; // fade out if not clicked




}
