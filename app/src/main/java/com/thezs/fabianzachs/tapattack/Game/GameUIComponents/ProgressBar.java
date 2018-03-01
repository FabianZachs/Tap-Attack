package com.thezs.fabianzachs.tapattack.Game.GameUIComponents;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapProgressBar;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.thezs.fabianzachs.tapattack.Constants;

/**
 * Created by fabianzachs on 28/02/18.
 */

// TODO set progress bar color depending on how low it is
public class ProgressBar implements GameUIComponent {

    private BootstrapProgressBar progressBar;
    private int progress;

    public ProgressBar() {
        //progressBar = new BootstrapProgressBar(Constants.CURRENT_CONTEXT);
        //progressBar.setProgress(100);
        //progressBar.setBootstrapSize(100);
        progressBar = Constants.progressBar;
        progressBar.setProgress(10);

        /* landing --> FadeIn
        YoYo.with(Techniques.Shake)
                .duration(1000)
                .repeat(2)
                .playOn(progressBar);
                */
        YoYo.with(Techniques.Shake)
                .duration(1500)
                .repeat(5)
                .playOn(progressBar);
    }

    @Override
    public void update() {
        progressBar.setProgress(progress);

    }

    @Override
    public void draw(Canvas canvas) {



        /*
        Rect rect = new Rect();
        rect.set(100, 100, 500, 200);

        //Lay the view out with the known dimensions
        progressBar.layout (0, 0, rect.width(), rect.height());

//Translate the canvas so the view is drawn at the proper coordinates
        canvas.save();
        canvas.translate(rect.left, rect.top);

//Draw the View and clear the translation
        progressBar.draw(canvas);
        canvas.restore();
        */
       //progressBar.draw(canvas);

    }

}
