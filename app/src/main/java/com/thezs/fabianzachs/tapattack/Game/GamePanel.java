package com.thezs.fabianzachs.tapattack.Game;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.GameModeScenes.SceneManager;
import com.thezs.fabianzachs.tapattack.Game.Mediator.CentralGameCommunication;

/**
 * Created by fabianzachs on 07/02/18.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private MainThreadActivity thread;
    private SceneManager sceneManager;

    //private SceneManager manager;

    public GamePanel(Context context, CentralGameCommunication mediator) {
        super(context);
        mediator.addObject(this);

        getHolder().addCallback(this);

        Constants.CURRENT_CONTEXT = context;

        sceneManager = new SceneManager(mediator);

        setFocusable(true);
    }


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

        thread = new MainThreadActivity(getHolder(),this);

        thread.setRunning(true); // for us later (to start thread)
        thread.start();          // method in Thread class
    }

    public void startNewThread() {
        thread = new MainThreadActivity(getHolder(), this);

        thread.setRunning(true);
        thread.start();
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        sceneManager.recieveTouch(event);

        return true;
        //return super.onTouchEvent(event);
    }

    public void update() {
        sceneManager.update();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        sceneManager.draw(canvas);
    }

    // TODO code here for drawing the ready screen
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void endRunningThread() {
        thread.setRunning(false);
    }

    public void unPauseThread() {
        //thread.isPaused = false;
    }

}
