package com.thezs.fabianzachs.tapattack.Game.GameUIComponents;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapProgressBar;
import com.thezs.fabianzachs.tapattack.Constants;

/**
 * Created by fabianzachs on 28/02/18.
 */

public class ProgressBar implements GameUIComponent {

    private BootstrapProgressBar progressBar;
    private int progress;

    public ProgressBar() {
        //progressBar = new BootstrapProgressBar(Constants.CURRENT_CONTEXT);
        //progressBar.setProgress(100);
        //progressBar.setBootstrapSize(100);
        progressBar = Constants.progressBar;
        progressBar.setProgress(10);
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
