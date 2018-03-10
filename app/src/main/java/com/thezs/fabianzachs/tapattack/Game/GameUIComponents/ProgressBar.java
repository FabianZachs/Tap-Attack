package com.thezs.fabianzachs.tapattack.Game.GameUIComponents;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapProgressBar;
import com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapBrand;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.ShapesManager;

/**
 * Created by fabianzachs on 28/02/18.
 */

// TODO set progress bar color depending on how low it is
public class ProgressBar {

    private Handler mHandler = new Handler();
    private int YOYO_SHAKE = 1;

    private ImageView progressBarHolder;

    //private ShapesManager shapesManager;

    private BootstrapProgressBar progressBar;
    private int progress;
    private float TIME_PER_REDUCE = 0.2f;
    private int PROGRESS_REDUCE_PER_UNIT_TIME = -1;
    private long timeOfLastReduce;

    public static boolean running = true;

    public ProgressBar() {


        // ===================
        /*mHandler = new Handler(Looper.getMainLooper()) { // this handler is attached to UI thread (put this in progress bar??)

            @Override
            public void handleMessage(Message msg) {
                //super.handleMessage(msg);
                // TODO the yoyo shake
                if (msg.what == YOYO_SHAKE) {
                    YoYo.with(Techniques.Shake)
                            .duration(750)
                            .repeat(0)
                            .playOn(progressBar);
                    YoYo.with(Techniques.Shake)
                            .duration(750)
                            .repeat(0)
                            .playOn(progressBarHolder);
                }
            }
        };*/
        // ===================



        running = true;
        this.progressBarHolder = Constants.progressBarHolder;
        //this.shapesManager = shapesManager;
        //this.shapesManager.attachProgressBarObserver(this);
        timeOfLastReduce = System.currentTimeMillis();
        //progressBar = new BootstrapProgressBar(Constants.CURRENT_CONTEXT);
        //progressBar.setProgress(100);
        //progressBar.setBootstrapSize(100);
        this.progress = 100;
        progressBar = Constants.progressBar;
        progressBar.setProgress(progress);


        YoYo.with(Techniques.FadeIn)
                .duration(1500)
                .repeat(0)
                .playOn(progressBar);

        YoYo.with(Techniques.FadeIn)
                .duration(1500)
                .repeat(0)
                .playOn(progressBarHolder);


        // TODO make sure thread is disposed of properly at end
        Thread timer = new Thread() {
            public void run() {
                while (running) {
                    try {
                        Constants.GAME_ACTIVITY.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                timedReduce();
                                progressBar.setProgress(getProgress());

                                if (getProgress() < 25) {
                                    progressBar.setBootstrapBrand(DefaultBootstrapBrand.DANGER);
                                } else progressBar.setBootstrapBrand(DefaultBootstrapBrand.INFO);
                                if (getProgress() <= 0) running = false;
                            }
                        });
                        Thread.sleep(100);

                    } catch (InterruptedException e) {
                        Log.d("running", "run: error");
                        e.printStackTrace();
                    }
                }
        }
        };
        timer.start();


    }


    private void timedReduce() {
        if (System.currentTimeMillis() - timeOfLastReduce > TIME_PER_REDUCE * 1000) {
            changeProgressBy(PROGRESS_REDUCE_PER_UNIT_TIME);
            timeOfLastReduce = System.currentTimeMillis();
        }

    }

    // TODO maybe have a multiplier for decreasing amount after time
    public void changeProgressBy(final int amount) {
/*
        if (amount < -10) {
            YoYo.with(Techniques.Shake)
                    .duration(750)
                    .repeat(0)
                    .playOn(progressBar);
            YoYo.with(Techniques.Shake)
                    .duration(750)
                    .repeat(0)
                    .playOn(progressBarHolder);
        }*/

        /*if (amount < -10) {
            Message msg = mHandler.obtainMessage();
            msg.what = YOYO_SHAKE;
            msg.arg1 = amount;
            mHandler.sendMessage(msg);
        }*/

        if (amount <= -10) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    YoYo.with(Techniques.Shake)
                            .duration(750)
                            .repeat(0)
                            .playOn(progressBar);
                    YoYo.with(Techniques.Shake)
                            .duration(750)
                            .repeat(0)
                            .playOn(progressBarHolder);
                }
            });
        }

        if (progress + amount >= 100) {
            this.progress = 100;
            timeOfLastReduce = System.currentTimeMillis() + 200;
        }
        else if (progress + amount <= 0)
            this.progress = 0;
        else progress += amount;
        //this.progress = (progress + amount > 0)  && (progress + amount <= 100) ? progress + amount : progress;
        //this.progress = progress + amount >= 100 ? 100 : progress + amount;
        //this.progress = progress + amount <= 0   ? 0   : progress + amount;
        //this.progress = progress + amount > 100 ? 100 : progress + amount;
        //this.progress = progress + amount <= 0 ? 0 : progress + amount;
        //this.progress = progress + amount >= 100 ? 100 : progress + amount;
        //if (progress <= 0) running = false;
    }

    public int getProgress() {
        // TODO if this <0 gameOver
        return this.progress;
    }

}
