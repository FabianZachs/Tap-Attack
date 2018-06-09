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

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Created by fabianzachs on 04/03/18.
 */

public class Star extends ShapeObject {

    private final int ROTATION_ANGLE = 90;
    private boolean clicked = false;
    private long timeOfLastColorChange;
    //private float[] hsv = {0,1f,1f};
    private Matrix rotationMatrix;
    private float TIME_PER_COLOR = 0.5f;
    //private int COLOR_INTERVAL = 5;
    //private String[] colors = Constants.COLORS.get(Constants.CURRENT_THEME);
    //private int[] colors = Constants.neonColorsInt;
    private ArrayList<Integer> intColors;
    private ArrayList<String> strColors;

    private int colorIndex = 0; // TODO start at random index and if warning colow matches color of star -- reduce progress

    public Star(float durationAlive, String color, Point centerLocation, Bitmap shapeImg, Bitmap shapeClickImg, Paint paint, Rect bitmapHolder, ArrayList<Integer> intColors, ArrayList<String> strColors,CentralGameCommunication mediator) {
        super(durationAlive, color, centerLocation, shapeImg, shapeImg, paint, bitmapHolder, mediator);
        //setProgressBarAddition(0);
        setGraveAble(false);
        setLives(1);
        setPoints(10);
        setGraveAble(false);
        this.intColors = intColors;
        this.strColors = strColors;

        timeOfLastColorChange = 0;
        //setProgressBarAddition(10); // TODO called when we reduce lives of star ie when wrong color hit

        Random randIndex = new Random();
        //colorIndex = randIndex.nextInt(color.length());
        colorIndex = randIndex.nextInt(intColors.size());
        //hsv[0] = colorIndex;

        rotationMatrix = new Matrix();
        rotationMatrix.postRotate(ROTATION_ANGLE);

        setmDetector(new GestureDetectorCompat(Constants.CURRENT_CONTEXT, new MyGestureListener()));

    }


    // TODO in the update we want the star to rotate every x milliseconds
    @Override
    public void update() {

        if (System.currentTimeMillis() - timeOfLastColorChange > TIME_PER_COLOR * 1000 && !clicked) {
            timeOfLastColorChange = System.currentTimeMillis();
            //colorIndex += COLOR_INTERVAL;
            colorIndex++;
        }
        colorIndex = colorIndex%(intColors.size());
        setColor(this.strColors.get(colorIndex));
        //colorIndex = colorIndex%360;
        //hsv[0] = colorIndex;
    }

    @Override
    public void draw(Canvas canvas) {

        // tODO fix colors
        //super.draw(canvas);



        //ColorFilter filter = new PorterDuffColorFilter(Color.HSVToColor(255,hsv),PorterDuff.Mode.SRC_IN);
        ColorFilter filter = new PorterDuffColorFilter(intColors.get(colorIndex),PorterDuff.Mode.SRC_IN);
        //Log.d("colorindex", "update: colorindex: " + colorIndex);



        getAlphaPaint().setColorFilter(filter);

        canvas.drawBitmap(getCurrentShapeImg(),null, getBitmapHolder(), getAlphaPaint());
        //colorIndex += 5;
        //colorIndex = colorIndex > 355 ? 0 : colorIndex++;

        //canvas.drawBitmap(getCurrentShapeImg(),null, getBitmapHolder(),getAlphaPaint());
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

        @Override
        public boolean onDown(MotionEvent event) {
            clicked = true;
            setShapeImages(0, Bitmap.createBitmap(getShapeImg(), 0, 0, getShapeImg().getWidth(), getShapeImg().getHeight(), rotationMatrix, true));
            if (mediator.getIntWarningColor().equals(intColors.get(colorIndex))) {
                reduceLives();
                mediator.resetStreak();
            }
            else {
                //mediator.incScore(getPoints());
                reduceLives();
                //mediator.incScore(getPoints() /*get string based Color based on index of color*/);
            }

            // if hit warning color, reduce lives of shape and progressbar, otherwise make it unable inc score with that color maintained
            return true;
        }

    }

}
