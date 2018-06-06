package com.thezs.fabianzachs.tapattack.Game;

import android.graphics.Canvas;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Created by fabianzachs on 07/02/18.
 */

public class MainThread extends Thread {

    public static final int MAX_FPS = 30;
    private double averageFPS;

    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    public static Canvas canvas;

    private boolean running;
    public boolean isPaused = false;


    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }


    // called when thread.start() is called
    public void run() {
        long startTime; // of iterations
        long timeMillis;  // gets millisec per frame its taken
        long waitTime;
        int frameCount = 0;
        long totalTime = 0;
        long targetTime = 1000/MAX_FPS;

        while (running) {

            Log.d("threaddubug", "run: ispaused" + isPaused);
            while (isPaused) {
                try {
                    Log.d("threaddubug", "run: ispaused" + isPaused);
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Log.d("threaddubug", "run: ispaused" + isPaused);

                }
            }

            if (Looper.myLooper() == null)
                Looper.prepare();


            startTime = System.nanoTime();
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();

                synchronized (surfaceHolder) {
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }

            // wait if finished early
            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - timeMillis;
            try {
                // if frame finished before target time
                // this is where we cap the framerate
                if (waitTime > 0)
                    this.sleep(waitTime);
            } catch (Exception e) {
                e.printStackTrace();
            }


            // output frames
            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if (frameCount == MAX_FPS) {
                averageFPS = 1000 / ((totalTime / frameCount) / 1000000);
                frameCount = 0;
                totalTime = 0;
                System.out.println(averageFPS);
            }

        }
    }
}
