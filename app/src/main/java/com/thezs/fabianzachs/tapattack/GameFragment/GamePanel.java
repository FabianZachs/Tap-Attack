package com.thezs.fabianzachs.tapattack.GameFragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    private MainGameFragment gameFragment;
    private Background background;

    public GamePanel(Context context) {
        super(context);
        getHolder().addCallback(this);
        setFocusable(true);

        final SurfaceHolder holder = getHolder();
        holder.setFormat(PixelFormat.RGBA_8888);
        background = new Background((Activity)context);


    }

    public void addGame(MainGameFragment gameFragment) {
        this.gameFragment = gameFragment;

    }



    public void update() {
        if (gameFragment != null)
            gameFragment.update();

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (gameFragment != null)
            gameFragment.onTouchEvent(event);
        return true;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        background.draw(canvas);

        if (gameFragment != null)
            gameFragment.draw(canvas);

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
