package com.thezs.fabianzachs.tapattack.GameFragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.R;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    private MainGameFragment gameFragment;
    private boolean gameRunning = false;

    public GamePanel(Context context) {
        super(context);
        getHolder().addCallback(this);
        setFocusable(true);

        final SurfaceHolder holder = getHolder();
        holder.setFormat(PixelFormat.RGBA_8888);


    }

    public void addGame(MainGameFragment gameFragment) {
        this.gameFragment = gameFragment;

    }


    private Paint paint = new Paint();

    public void update() {

    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        /*
        Shader shader = new LinearGradient(0, 0, 0, Constants.GAME_VIEW_HEIGHT/2, 0xff007389, 0xffffffff, Shader.TileMode.REPEAT);
        Paint paint = new Paint();
        paint.setShader(shader);
        canvas.drawRect(new RectF(0, 0, Constants.GAME_VIEW_WIDTH, Constants.GAME_VIEW_HEIGHT), paint);
        */



        /*
        Bitmap myLogo = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.backgroundtriangleblue);

        canvas.drawBitmap(myLogo, null, new Rect(0,0,Constants.GAME_VIEW_WIDTH,Constants.GAME_VIEW_HEIGHT), paint);
        */
        Drawable d = ContextCompat.getDrawable(getContext(), R.drawable.background_test);
        d.setBounds(0, 0, Constants.GAME_VIEW_WIDTH, Constants.SCREEN_HEIGHT);
        d.draw(canvas);
        /*
        paint.setShader(new LinearGradient(0, 0, Constants.GAME_VIEW_WIDTH, Constants.GAME_VIEW_HEIGHT, Color.BLACK, Color.WHITE, Shader.TileMode.CLAMP));
        canvas.drawPaint(paint);
        */
       // if (gameRunning)
            //gameFragment.draw(canvas);

    }


    public void stopCurrentThread() {
        if(thread !=null){
            thread.interrupt();
            thread = null;
        }
    }

    public void startNewThread() {
        thread = new MainThread(getHolder(), this);

        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        stopCurrentThread();
        startNewThread();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        while(retry) {
            try {
                thread.setRunning(false); // stops game loop
                thread.join();            // will finish thread then terminate
                // will pause execution until thread terminates
            } catch(Exception e) {e.printStackTrace();}
            retry = false; // the above succeeded in terminating thread
        }
    }
}
