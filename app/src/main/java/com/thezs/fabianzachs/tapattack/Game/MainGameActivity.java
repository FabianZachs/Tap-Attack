package com.thezs.fabianzachs.tapattack.Game;

        import android.annotation.TargetApi;
        import android.app.Activity;
        import android.content.Context;
        import android.graphics.Point;
        import android.os.Build;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.util.DisplayMetrics;
        import android.util.Log;
        import android.view.Display;
        import android.view.Gravity;
        import android.view.SurfaceView;
        import android.view.View;
        import android.view.WindowManager;
        import android.widget.FrameLayout;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.RelativeLayout;
        import android.widget.TextView;

        import com.daimajia.androidanimations.library.Techniques;
        import com.daimajia.androidanimations.library.YoYo;
        import com.thezs.fabianzachs.tapattack.Constants;
        import com.thezs.fabianzachs.tapattack.Game.LayoutHeadingHandlers.LayoutHeadings;
        import com.thezs.fabianzachs.tapattack.R;
        import com.thezs.fabianzachs.tapattack.helper;

        import org.w3c.dom.Text;

/**
 * Created by fabianzachs on 07/02/18.
 */

public class MainGameActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper.makeFullscreen(this);


        setContentView(R.layout.activity_main_game);

        bootstrapViewSetup();
        Constants.GAME_ACTIVITY = this;

        LinearLayout viewForGamePanel = (LinearLayout) findViewById(R.id.game_panel_surface);
        viewForGamePanel.addView(new GamePanel(this));

        // below works for setting entire screen the view
        // setContentView(new GamePanel(this));

    }

    private void initializeRemainingConstants() {
    }

    private void bootstrapViewSetup() {
        Constants.progressBar = (com.beardedhen.androidbootstrap.BootstrapProgressBar) findViewById(R.id.progress_bar);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(Constants.SCREEN_WIDTH/2,50);
        params.gravity = (Gravity.CENTER|Gravity.TOP);
        params.topMargin = 40;


        Constants.progressBar.setLayoutParams(params);

        FrameLayout.LayoutParams params1 = new FrameLayout.LayoutParams(Constants.SCREEN_WIDTH/2 + 20, 75);

        ImageView progBackground = (ImageView) findViewById(R.id.bar_background);
        params1.gravity = (Gravity.CENTER|Gravity.TOP);
        params1.topMargin = 30;
        progBackground.setLayoutParams(params1);

        // id progressbarholder

        // we scale it to account for shake
    }

    /*
    private LayoutHeadings createLayoutHeadings() {

        TextView score = (TextView) findViewById(R.id.score_text);
        TextView streak = (TextView) findViewById(R.id.streak_text);
        com.beardedhen.androidbootstrap.BootstrapProgressBar progressBar =
                (com.beardedhen.androidbootstrap.BootstrapProgressBar) findViewById(R.id.progress_bar);
        RelativeLayout parentLayout = (RelativeLayout) findViewById(R.id.parent_layout);


        LayoutHeadings layoutHeadings = new LayoutHeadings(this, score, streak, progressBar, parentLayout);
        return  layoutHeadings;
    }*/


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            this.getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    // TODO make this pause button size relative to screen size
    public void pauseClick(View view) {
        finish();
    }



}
