package com.thezs.fabianzachs.tapattack.Game;

        import android.app.Activity;
        import android.app.AlertDialog;
        import android.graphics.Color;
        import android.graphics.Rect;
        import android.graphics.drawable.ColorDrawable;
        import android.graphics.drawable.LayerDrawable;
        import android.media.Image;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.util.Log;
        import android.view.Gravity;
        import android.view.View;
        import android.view.Window;
        import android.view.WindowManager;
        import android.widget.FrameLayout;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;

        import com.google.android.gms.ads.AdRequest;
        import com.google.android.gms.ads.AdView;
        import com.google.android.gms.ads.MobileAds;
        import com.thezs.fabianzachs.tapattack.Constants;
        import com.thezs.fabianzachs.tapattack.R;
        import com.thezs.fabianzachs.tapattack.helper;

/**
 * Created by fabianzachs on 07/02/18.
 */

public class MainGameActivity extends Activity {

    private GamePanel gamePanel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper.makeFullscreen(this);


        setContentView(R.layout.activity_main_game);

        bootstrapViewSetup();
        Constants.GAME_ACTIVITY = this;

        LinearLayout viewForGamePanel = (LinearLayout) findViewById(R.id.game_panel_surface);
        this.gamePanel = new GamePanel(this);
        viewForGamePanel.addView(this.gamePanel);

        // below works for setting entire screen the view
        // setContentView(new GamePanel(this));

    }


    private void bootstrapViewSetup() {

        // Layout Settings


        /*
        Constants.progressBar = (com.beardedhen.androidbootstrap.BootstrapProgressBar) findViewById(R.id.progress_bar);
        FrameLayout.LayoutParams progressBarParameters = new FrameLayout.LayoutParams(Constants.SCREEN_WIDTH/2,Constants.SCREEN_HEIGHT/40);
        //FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(Constants.SCREEN_WIDTH/2,50);
        progressBarParameters.gravity = (Gravity.CENTER|Gravity.TOP);
        progressBarParameters.topMargin = 40;
        Constants.progressBar.setLayoutParams(progressBarParameters);


        ImageView progressBarHolder = (ImageView) findViewById(R.id.bar_background);
        Constants.progressBarHolder = progressBarHolder;
        FrameLayout.LayoutParams progressBarHolderParameters = new FrameLayout.LayoutParams(Constants.SCREEN_WIDTH/2 + Constants.SCREEN_WIDTH/12, Constants.SCREEN_HEIGHT/40 + 25);
        //FrameLayout.LayoutParams params1 = new FrameLayout.LayoutParams(Constants.SCREEN_WIDTH/2 + 20, 75);
        progressBarHolderParameters.gravity = (Gravity.CENTER|Gravity.TOP);
        progressBarHolderParameters.topMargin = 30;
        progressBarHolder.setLayoutParams(progressBarHolderParameters);
        */


        ImageView warningComponent = (ImageView) findViewById(R.id.warning_component);
        FrameLayout.LayoutParams warningComponentParameters = new FrameLayout.LayoutParams(Constants.SCREEN_WIDTH/2,Constants.SCREEN_WIDTH/15);
        warningComponentParameters.gravity = (Gravity.CENTER|Gravity.TOP);
        warningComponentParameters.topMargin = /*(30)*/ + (Constants.SCREEN_HEIGHT/40 );
        warningComponent.setLayoutParams(warningComponentParameters);

        Constants.warningComponent = (LayerDrawable) warningComponent.getDrawable();
        Constants.warningComponentImg = warningComponent; // FOR SHAKE

        ImageView warningColorChangeButtonLeft = (ImageView) findViewById(R.id.warning_color_change_button_left);
        FrameLayout.LayoutParams colorChangeParamsLeft = new FrameLayout.LayoutParams(Constants.WARNING_COLOR_CLICK_AREA_LEFT.width(), Constants.WARNING_COLOR_CLICK_AREA_LEFT.height());
        colorChangeParamsLeft.gravity = (Gravity.LEFT|Gravity.BOTTOM);
        warningColorChangeButtonLeft.setLayoutParams(colorChangeParamsLeft);


        ImageView warningColorChangeButtonRight = (ImageView) findViewById(R.id.warning_color_change_button_right);
        FrameLayout.LayoutParams colorChangeParamsRight = new FrameLayout.LayoutParams(Constants.WARNING_COLOR_CLICK_AREA_RIGHT.width(), Constants.WARNING_COLOR_CLICK_AREA_RIGHT.height());
        colorChangeParamsRight.gravity = (Gravity.RIGHT|Gravity.BOTTOM);
        warningColorChangeButtonRight.setLayoutParams(colorChangeParamsRight);
        // boolean testFactor = layeredCompnent.setDrawableByLayerId(R.id.warning, replacewarning);

        // TODO find the right place to change these color
        Constants.warningComponentButtonLeft = /*(LayerDrawable)*/ warningColorChangeButtonLeft.getDrawable();
        Constants.warningComponentButtonRight = /*(LayerDrawable)*/ warningColorChangeButtonRight.getDrawable();


    }

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

        ImageView warningComponent = (ImageView) findViewById(R.id.warning_component);
        int[] location = new int[2];
        warningComponent.getLocationInWindow(location);
        //Constants.WARNING_COLOR_CLICK_AREA_LEFT = new Rect(location[0] - Constants.SHAPE_WIDTH, 10, location[0] + Constants.SHAPE_WIDTH, Constants.SHAPE_CLICK_AREA.top);
        //Constants.WARNING_COLOR_CLICK_AREA_LEFT = new Rect(0,30 + (Constants.SCREEN_HEIGHT/40 +25) + 20 + Constants.SCREEN_WIDTH/15 + 10 + Constants.SHAPE_HEIGHT/2,Constants.SCREEN_WIDTH/20, Constants.SCREEN_HEIGHT);
        //Constants.WARNING_COLOR_CLICK_AREA_RIGHT = new Rect(Constants.SCREEN_WIDTH - Constants.SCREEN_WIDTH/20,30 + (Constants.SCREEN_HEIGHT/40 +25) + 20 + Constants.SCREEN_WIDTH/15 + 10 + Constants.SHAPE_HEIGHT/2,Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        //Constants.SHAPE_CLICK_AREA = new Rect(Constants.SCREEN_WIDTH/20, 30 + (Constants.SCREEN_HEIGHT/40 +25) + 20 + Constants.SCREEN_WIDTH/15 + 10 + Constants.SHAPE_HEIGHT/2, Constants.SCREEN_WIDTH - Constants.SCREEN_WIDTH/20, Constants.SCREEN_HEIGHT - 1);
    }

    // TODO make this pause button size relative to screen size
    public void pauseClick(View view) {
        //com.thezs.fabianzachs.tapattack.Game.GameUIComponents.ProgressBar.running = false;
        gamePanel.pauseThread();

        //this.getLayoutInflater(R.layout.pause_screen);
        View alertView = this.getLayoutInflater().inflate(R.layout.pause_screen, null);
        AlertDialog.Builder dbuilder = new AlertDialog.Builder(this);
        dbuilder.setView(alertView);
        final AlertDialog dialog = dbuilder.create();
        dialog.setCancelable(false);

        Window window = dialog.getWindow();
        if(window != null){
            window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND); // This flag is required to set otherwise the setDimAmount method will not show any effect
            window.setDimAmount(1.0f); //0 for no dim to 1 for full dim
        }


        standardOkButtonSetup(alertView, dialog);

        helper.dialogFullscreen(this, dialog);

        AdView pauseBannerAd;
        // todo banner ad
        bannerAdSetup(alertView);
        //finish();
    }


    public void bannerAdSetup(View alertView) {
        // ads (below setContentView)
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        AdView adView = (AdView) alertView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void exitClick(View view) {
        // todo save current score and streak?? prbly not
        finish();
    }

    /*
    public void resumeClick(View view) {
        //gamePanel.unPauseThread();
        // todo create new thread
        Log.d("threadingdebug", "startNewThread: called");
        gamePanel.startNewThread();

    }
    */
    private void standardOkButtonSetup(View alertView, final AlertDialog dialog) {

        TextView okButt = (TextView) alertView.findViewById(R.id.resumeButton);

        okButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //playSound(R.raw.closesettings);
                dialog.dismiss();
                gamePanel.startNewThread();
            }
        });
    }

    public void resumeClick(View view) {
        Log.d("threadingdebug", "startNewThread: called");
    }
}
