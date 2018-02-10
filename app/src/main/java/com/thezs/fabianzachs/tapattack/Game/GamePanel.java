package com.thezs.fabianzachs.tapattack.Game;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.GameModeScenes.ClassicGameScene;
import com.thezs.fabianzachs.tapattack.R;

/**
 * Created by fabianzachs on 07/02/18.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    private SceneManager sceneManager;

    //private SceneManager manager;

    public GamePanel(Context context) {
        super(context);

        getHolder().addCallback(this);

        Constants.CURRENT_CONTEXT = context;

        sceneManager = new SceneManager();

        setFocusable(true);
    }


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

        thread = new MainThread(getHolder(),this);

        thread.setRunning(true); // for us later (to start thread)
        thread.start();          // method in Thread class
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

    // TODO
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            StyleableToast.makeText(Constants.CURRENT_CONTEXT, ClassicGameScene.gameOver + "", R.style.successtoast).show();
        }
        sceneManager.recieveTouch(event);

        return true;
        //return super.onTouchEvent(event);
    }

    // TODO
    public void update() {
        sceneManager.update();
    }

    // TODO
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        sceneManager.draw(canvas);
    }
}
