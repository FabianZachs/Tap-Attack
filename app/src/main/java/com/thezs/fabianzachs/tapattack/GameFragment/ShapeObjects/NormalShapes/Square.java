package com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.GameFragment.Mediator;
import com.thezs.fabianzachs.tapattack.R;

public class Square extends ShapeObject {

    private long timeSetState;
    private int TIME_FOR_SECOND_CLICK = 500;

    public Square(Mediator mediator, Point centerLocation, Bitmap[] shapeImages, Integer color, Paint paint, Rect bitmapHolder, int shapeRadius) {
        super(mediator, centerLocation, shapeImages, false, 2, color, paint, bitmapHolder, shapeRadius);


        setmDetector(new GestureDetectorCompat(Constants.CURRENT_CONTEXT, new MyGestureListener()));
    }

    @Override
    public void update() {
        if (System.currentTimeMillis() - timeSetState > TIME_FOR_SECOND_CLICK && getAnimationState() != 0) {
            increaseLives();
            setAnimationState(0);
        }
    }

    @Override
    protected void playDeathSoundEffect() {
        mediator.playSquare2SoundEffect();
    }
    private void playFirstSquareSoundEffect() {
        mediator.playSquare1SoundEffect();
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent event) {
            reduceLives();
            if (getLives() > 0)
                playFirstSquareSoundEffect();
            else if (getLives()<=0)
                playDeathSoundEffect();
            setAnimationState(1);
            timeSetState = System.currentTimeMillis();
            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {

            StyleableToast.makeText(Constants.CURRENT_CONTEXT, "fling game over", R.style.successtoast).show();
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            StyleableToast.makeText(Constants.CURRENT_CONTEXT, "scroll game over", R.style.successtoast).show();
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

    }
}
