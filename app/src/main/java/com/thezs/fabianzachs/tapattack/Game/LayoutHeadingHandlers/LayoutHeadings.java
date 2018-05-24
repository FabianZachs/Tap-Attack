package com.thezs.fabianzachs.tapattack.Game.LayoutHeadingHandlers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.R;

import org.w3c.dom.Text;

/**
 * Created by fabianzachs on 17/02/18.
 */

public class LayoutHeadings {

    private Context context;
    private TextView scoreView;
    private TextView streakView;
    private int score;
    private int streak;
    private com.beardedhen.androidbootstrap.BootstrapProgressBar progressBar;
    private RelativeLayout parentLayout;

    public LayoutHeadings(Context context, TextView score, TextView streak, com.beardedhen.androidbootstrap.BootstrapProgressBar progressBar, RelativeLayout parentLayout) {
        this.context = context;
        this.scoreView = score;
        this.streakView = streak;
        //this.progressBar = progressBar;
        this.parentLayout = parentLayout;

        this.score = 0;
        this.streak = 0;

        this.scoreView.setText("SCORE");
        this.streakView.setText("STREAK");
    }

    public void setParentLayoutBackground(Bitmap bitmap) {
        Drawable dr = new BitmapDrawable(context.getResources(), bitmap);
        parentLayout.setBackground(dr);
    }
    public void increaseScoreAndStreak(int amount) {

        /*
        try {
            YoYo.with(Techniques.Bounce).duration(1000).playOn(scoreView);
        } catch (Exception e) {
            Log.d("YOYO TEST", e.getMessage());
            // Animators may only be run on Looper threads

        }*/

        this.score += amount;
        this.streak += amount;
        try {
            this.streakView.setText(Integer.toString(streak));
            this.scoreView.setText(Integer.toString(score));
        } catch (Exception e) {
            Log.d("viewstuff", e.getMessage());
            // Only the original thread that created a view hierarchy can touch its views.
        }
        /* implement animation for text
        YoYo.with(Techniques.Tada)
                .duration(700)
                .repeat(5)
                .playOn(streakView);
               */

    }

    public void increaseScore(int amount) {
        this.score += amount;
        this.scoreView.setText(Integer.toString(score));
    }


    public void resetScore() {
        this.score = 0;
        this.scoreView.setText("SCORE");
    }


    public void increaseStreak (int amount) {
        this.streak += amount;
        streakView.setText(Integer.toString(streak));
    }


    public void resetStreak() {
        this.streak = 0;
        this.streakView.setText("STREAK");
    }

}
