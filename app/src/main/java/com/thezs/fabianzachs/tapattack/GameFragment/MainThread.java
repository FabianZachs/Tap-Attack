package com.thezs.fabianzachs.tapattack.GameFragment;


import android.graphics.Canvas;
import android.os.Looper;
import android.view.SurfaceHolder;

public class MainThread extends Thread {

    private static final int MAX_FPS = 80;
    private double averageFPS;

    private final SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    public Canvas canvas;

    private boolean running;


    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }


    public void run() {
        long startTime;
        long timeMillis;
        long waitTime;
        int frameCount = 0;
        long totalTime = 0;
        long targetTime = 1000/MAX_FPS;

        while (running) {

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
                if (waitTime > 0)
                    sleep(waitTime);
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
