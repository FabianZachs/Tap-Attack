package com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.GameUIComponents.Score;
import com.thezs.fabianzachs.tapattack.Game.Mediator.CentralGameCommunication;
import com.thezs.fabianzachs.tapattack.R;

/**
 * Created by fabianzachs on 04/03/18.
 */

public class Star extends ShapeObject {

    private final float TIME_OF_CLICK_IMG = 0.08f;
    private long timeSetState;
    private final int ROTATION_ANGLE = 90;
    private final float TIME_PER_ROATION = 0.5f;
    private Matrix rotationMatrix;
    private long timeOfLastRotation;

    private int colorIndex = 0;

    public Star(float durationAlive, String color, Point centerLocation, Bitmap shapeImg, Bitmap shapeClickImg, Paint paint, Rect bitmapHolder, CentralGameCommunication mediator) {
        super(durationAlive, color, centerLocation, shapeImg, shapeClickImg, paint, bitmapHolder, mediator);
        setLives(1);
        setProgressBarAddition(0);
        setGraveAble(false);

        rotationMatrix = new Matrix();
        rotationMatrix.postRotate(ROTATION_ANGLE);
        timeOfLastRotation = System.currentTimeMillis();

        // handling touch events
        setmDetector(new GestureDetectorCompat(Constants.CURRENT_CONTEXT, new MyGestureListener()));

    }


    // TODO in the update we want the star to rotate every x milliseconds
    @Override
    public void update() {

        if (System.currentTimeMillis() - timeSetState > TIME_OF_CLICK_IMG * 1000)
            setState(0);


        if (System.currentTimeMillis() - timeOfLastRotation> TIME_PER_ROATION * 1000) {
            //rotationMatrix.postRotate(ROTATION_ANGLE);
            timeOfLastRotation = System.currentTimeMillis();
            setShapeImages(0, Bitmap.createBitmap(getShapeImg(), 0, 0, getShapeImg().getWidth(), getShapeImg().getHeight(), rotationMatrix, true));
            // TODO rotate bitmap holder as well
            //setBitmapHolder(getBitmapHolder().);
        }
/*
        Matrix m = new Matrix();
// point is the point about which to rotate.
        m.setRotate(degrees, point.x, point.y);
        m.mapRect(r);*/

/*
        Matrix matrix = new Matrix();
        matrix.setRotate(ROTATION_ANGLE, getBitmapHolder().centerX(), getBitmapHolder().centerY());
        mcanvas.drawBitmap(yourBitmap, matrix, null);
*/
        //canvas.drawBitmap(getCurrentShapeImg(), null, getBitmapHolder(),getAlphaPaint());
/*
        mcanvas.save(Canvas.MATRIX_SAVE_FLAG); //Saving the canvas and later restoring it so only this image will be rotated.
        2
        mcanvas.rotate(-angle);
        3
        mcanvas.drawBitmap(yourBitmap, left, top, null);
        4
        mcanvas.restore();
        */


    }

    @Override
    public void draw(Canvas canvas) {

        // tODO fix colors
        //super.draw(canvas);

        float[] hsv = {colorIndex,0.9f,1f};
        Log.d("color", "draw: colow: " + colorIndex);
        colorIndex++;
        //colorIndex = colorIndex > 359 ? 0 : colorIndex++;

        int myRGBColor = Color.HSVToColor(255, hsv);

        Paint paint = new Paint();
        ColorFilter filter = new PorterDuffColorFilter(myRGBColor,PorterDuff.Mode.SRC_IN);


        paint.setColorFilter(filter);


        canvas.drawBitmap(getCurrentShapeImg(),null, getBitmapHolder(), paint);

        //Bitmap star = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.star);
        /*Drawable star = Constants.CURRENT_CONTEXT.getDrawable(R.drawable.star);
        star.setColorFilter(0xff52ff, PorterDuff.Mode.DST);

        star.setBounds(getBitmapHolder().left, getBitmapHolder().top, getBitmapHolder().right, getBitmapHolder().bottom);

        star.draw(canvas);
*/
        //canvas.drawBitmap(getCurrentShapeImg().,null,getBitmapHolder(),new Paint());



        /*
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.rotate(-ROTATION_ANGLE);
        canvas.drawBitmap(getCurrentShapeImg(),null, getBitmapHolder(),getAlphaPaint());
        canvas.restore();
        */
        /*
        Matrix matrix = new Matrix();
        matrix.setRotate(ROTATION_ANGLE, getBitmapHolder().centerX(), getBitmapHolder().centerY());
        canvas.drawBitmap(getCurrentShapeImg(), , null);
        */

    }




    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";

        @Override
        public boolean onDown(MotionEvent event) {
            setState(1);
            timeSetState = System.currentTimeMillis();
            mediator.incScore(getPoints());
            return true;
        }

    }

}
