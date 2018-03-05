package com.thezs.fabianzachs.tapattack.Game;

        import android.annotation.TargetApi;
        import android.app.Activity;
        import android.content.Context;
        import android.content.res.Resources;
        import android.graphics.Color;
        import android.graphics.Point;
        import android.graphics.PorterDuff;
        import android.graphics.PorterDuffColorFilter;
        import android.graphics.drawable.Drawable;
        import android.graphics.drawable.GradientDrawable;
        import android.graphics.drawable.LayerDrawable;
        import android.media.Image;
        import android.os.Build;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.annotation.RequiresApi;
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void bootstrapViewSetup() {
        Constants.progressBar = (com.beardedhen.androidbootstrap.BootstrapProgressBar) findViewById(R.id.progress_bar);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(Constants.SCREEN_WIDTH/2,Constants.SCREEN_HEIGHT/40);
        Log.d("TEST", "bootstrapViewSetup: " + Constants.SCREEN_HEIGHT/40);
        //FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(Constants.SCREEN_WIDTH/2,50);
        params.gravity = (Gravity.CENTER|Gravity.TOP);
        params.topMargin = 40;
        Constants.progressBar.setLayoutParams(params);

        ImageView progressBarHolder = (ImageView) findViewById(R.id.bar_background);
        FrameLayout.LayoutParams params1 = new FrameLayout.LayoutParams(Constants.SCREEN_WIDTH/2 + 20, Constants.SCREEN_HEIGHT/40 + 25);
        //FrameLayout.LayoutParams params1 = new FrameLayout.LayoutParams(Constants.SCREEN_WIDTH/2 + 20, 75);
        params1.gravity = (Gravity.CENTER|Gravity.TOP);
        params1.topMargin = 30;
        progressBarHolder.setLayoutParams(params1);


        ImageView warningComponent = (ImageView) findViewById(R.id.warning_component);
        FrameLayout.LayoutParams params2 = new FrameLayout.LayoutParams(Constants.SCREEN_WIDTH/15,Constants.SCREEN_WIDTH/15);
        params2.gravity = (Gravity.CENTER|Gravity.TOP);
        params2.topMargin = (30) + (Constants.SCREEN_HEIGHT/40 + 25) + 20;
        warningComponent.setLayoutParams(params2);




        // ==== MESSING WITH DRAWABLE COLOR SETTING ====
        Constants.warningComponent = (LayerDrawable) warningComponent.getDrawable();


        LayerDrawable layeredCompnent = (LayerDrawable) warningComponent.getDrawable();
        GradientDrawable replacewarning = (GradientDrawable) getResources().getDrawable(R.drawable.warningcolor);
        replacewarning.setColor(Color.BLACK);

        boolean testFactor = layeredCompnent.setDrawableByLayerId(R.id.warning, replacewarning);
        //////////


        GradientDrawable replaceHolder = (GradientDrawable) getResources().getDrawable(R.drawable.warningholder);

        int colors[] = { 0xff000000, 0xffffffff};
        replaceHolder.setColors(colors);
        // set to gradient:
        //replaceHolder.setColor(0xff0040ff);

        // 0xff_______


        boolean testFactor2 = layeredCompnent.setDrawableByLayerId(R.id.holder, replaceHolder/*replacing drawable*/);
        /*
        GradientDrawable replaceHolder = (GradientDrawable) getResources().getDrawable(R.drawable.warningholder);


        int colors[] = { 0xff255779, 0xffa6c0cd };

        GradientDrawable gradientDrawable = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM, colors);

        replaceHolder.setBackgroundDrawable(gradientDrawable);

        boolean othertestFactor = layeredCompnent.setDrawableByLayerId(R.id.holder,replaceHolder);
        */

        // ======================================




        // WORKS FOR CONSTANT COLOR
        //get the image button by id
        ImageView myImg = (ImageView) findViewById(R.id.warning_color);

        //get drawable from image button
        GradientDrawable drawable = (GradientDrawable) myImg.getDrawable();

        //set color
        drawable.setColor(Color.RED);


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
