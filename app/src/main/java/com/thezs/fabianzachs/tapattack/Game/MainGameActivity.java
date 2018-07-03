package com.thezs.fabianzachs.tapattack.Game;

        import android.app.Activity;
        import android.app.AlertDialog;
        import android.content.SharedPreferences;
        import android.graphics.Typeface;
        import android.graphics.drawable.LayerDrawable;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.view.Gravity;
        import android.view.View;
        import android.view.Window;
        import android.view.WindowManager;
        import android.widget.FrameLayout;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;

        import com.daimajia.androidanimations.library.Techniques;
        import com.daimajia.androidanimations.library.YoYo;
        import com.google.android.gms.ads.AdRequest;
        import com.google.android.gms.ads.AdView;
        import com.google.android.gms.ads.MobileAds;
        import com.thezs.fabianzachs.tapattack.Constants;
        import com.thezs.fabianzachs.tapattack.Game.Mediator.CentralGameCommunication;
        import com.thezs.fabianzachs.tapattack.R;
        import com.thezs.fabianzachs.tapattack.helper;

        import org.w3c.dom.Text;

/**
 * Created by fabianzachs on 07/02/18.
 */

public class MainGameActivity extends Activity {

    private GamePanel gamePanel;
    CentralGameCommunication mediator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper.makeFullscreen(this);

        setContentView(R.layout.activity_main_game);


        bootstrapViewSetup();
        Constants.GAME_ACTIVITY = this;

        LinearLayout viewForGamePanel = (LinearLayout) findViewById(R.id.game_panel_surface);
        // todo initimediator here
        this.mediator = new CentralGameCommunication(System.currentTimeMillis());
        mediator.addObject(this);

        this.gamePanel = new GamePanel(this, mediator);
        viewForGamePanel.addView(this.gamePanel);

        // below works for setting entire screen the view
        // setContentView(new GamePanel(this));

        // todo just for testing
        /*
        SharedPreferences prefs = getSharedPreferences("playerStats", MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putInt("bestScore", 0);
        prefsEditor.apply();
        */

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

    public void setupGameOverFields(View alertView, String gameOverReason, int currentGameScore, int currentGameStreak) {

        TextView gameOverText = (TextView) alertView.findViewById(R.id.game_over_text);
        gameOverText.setText(gameOverReason);

        /*
        TextView streakText = (TextView) alertView.findViewById(R.id.shape_theme_text);
        streakText.setText("STREAK: " + currentGameStreak);
        */

        TextView scoreText = (TextView) alertView.findViewById(R.id.shape_type_text);
        scoreText.setText("SCORE: " + currentGameScore);

        SharedPreferences prefs = getSharedPreferences("playerStats", MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        int bestScore = prefs.getInt(/*CONSTANTS.CURRENTGAEMODE + */"bestScore", 0);// todo make work  for different current gamemodes
        //int bestStreak = prefs.getInt(/*CONSTANTS.CURRENTGAEMODE + */"bestStreak", 0);// todo make work  for different current gamemodes


        TextView bestScoreText = (TextView) alertView.findViewById(R.id.shape_type_image_description);
        //TextView bestStreakText = (TextView) alertView.findViewById(R.id.shape_theme_image_description);

        // todo refactorable
        if (currentGameScore > bestScore) {
            bestScore = currentGameScore;
            prefsEditor.putInt(/*CONSTANTS.CURRENTGAEMODE + */"bestScore", currentGameScore);
            prefsEditor.apply();
            //YoYo.with(Techniques.Tada).duration(1000).repeat(0).playOn(bestScoreText); // todo this works for text animation if bestScore/bestStreak >= score do animaton
            //YoYo.with(Techniques.Landing).duration(1000).repeat(0).playOn(bestScoreText); // todo this works for text animation if bestScore/bestStreak >= score do animaton
            //YoYo.with(Techniques.Pulse).duration(1000).repeat(0).playOn(bestScoreText); // todo this works for text animation if bestScore/bestStreak >= score do animaton
            YoYo.with(Techniques.BounceIn).duration(1000).repeat(0).playOn(bestScoreText); // todo this works for text animation if bestScore/bestStreak >= score do animaton
            //YoYo.with(Techniques.FadeIn).duration(1000).repeat(0).playOn(bestScoreText); // todo this works for text animation if bestScore/bestStreak >= score do animaton

            bestScoreText.setTextColor(getResources().getColor(R.color.soundon));
            bestScoreText.setTypeface(bestScoreText.getTypeface(),Typeface.BOLD); // todo messes up font
            //Typeface tf = Typeface.createFromAsset(getAssets(),"res/font/undinaru.ttf");
            //bestScoreText.setTypeface(tf);
            //Typeface typeface = getResources().getFont(R.font.undinaru);
           // bestScoreText.setTypeface(typeface);
        }
        /*
        if (currentGameStreak > bestStreak) {
            bestStreak = currentGameStreak;
            prefsEditor.putInt("bestStreak", currentGameStreak);
            prefsEditor.apply();
            YoYo.with(Techniques.BounceIn).duration(1000).repeat(0).playOn(bestScoreText); // todo this works for text animation if bestScore/bestStreak >= score do animaton
            bestStreakText.setTextColor(getResources().getColor(R.color.soundon));
            bestStreakText.setTypeface(bestScoreText.getTypeface(),Typeface.BOLD); // todo messes up font
        }*/


        bestScoreText.setText("BEST SCORE: " + bestScore);

        //bestStreakText.setText("BEST STREAK: " + bestStreak);
        TextView pointsEquation = (TextView) alertView.findViewById(R.id.points_text);
        //float scoreMultiplier = prefs.getFloat("scoremultiplier", 1);
        float scoreMultiplier = 2.2f;
        pointsEquation.setText(currentGameScore + " x MULTIPLIER (" + scoreMultiplier + ") = +" + (int) (scoreMultiplier * currentGameScore) + " POINTS");


    }

    public void showGameOverScreen(final String gameOverReason, final int scoreToDisplay, final int streakToDisplay) {

        final Activity activity = this;
        this.runOnUiThread(new Runnable() {
            public void run() {
                //View alertView = this.getLayoutInflater().inflate(R.layout.game_over, null);
                View alertView = helper.getAlertView(activity, R.layout.game_over);

                setupGameOverFields(alertView, gameOverReason, scoreToDisplay, streakToDisplay);

                final AlertDialog dialog = helper.getBuiltDialog(activity, alertView);
                //AlertDialog.Builder dbuilder = new AlertDialog.Builder(this);
                //dbuilder.setView(alertView);
                //final AlertDialog dialog = dbuilder.create();

                dialog.setCancelable(false); // todo add after done testign




                helper.dialogFullscreen(activity, dialog);

                //TextView score = (TextView) alertView.findViewById(R.id.score_text);
                //YoYo.with(Techniques.Bounce).duration(1000).repeat(3).playOn(score);

                AdView pauseBannerAd;
                // todo banner ad
                bannerAdSetup(alertView);
            }
        });



    }


    // TODO make this pause button size relative to screen size
    public void pauseClick(View view) {
        //com.thezs.fabianzachs.tapattack.Game.GameUIComponents.ProgressBar.running = false;
        long timeOfPauseClick = System.currentTimeMillis();
        gamePanel.endRunningThread();

        View alertView = helper.getAlertView(this, R.layout.pause_screen);
        final AlertDialog dialog = helper.getBuiltDialog(this, alertView);
        dialog.setCancelable(false);

        Window window = dialog.getWindow();
        if(window != null){
            window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND); // This flag is required to set otherwise the setDimAmount method will not show any effect
            window.setDimAmount(1.0f); //0 for no dim to 1 for full dim
        }


        resumeButtonSetup(alertView, dialog, timeOfPauseClick);

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
        finish(); // todo somehow tell mainmenu activity to do yoyo animation and update new points
    }

    /*
    public void resumeClick(View view) {
        //gamePanel.unPauseThread();
        // todo create new thread
        Log.d("threadingdebug", "startNewThread: called");
        gamePanel.startNewThread();

    }
    */
    private void resumeButtonSetup(View alertView, final AlertDialog dialog, final long timeOfPauseClick) {

        TextView okButt = (TextView) alertView.findViewById(R.id.resumeButton);

        okButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //playSound(R.raw.closesettings);
                dialog.dismiss();
                resumeClick(view, timeOfPauseClick);
            }
        });
    }

    public void resumeClick(View view, long timeOfPauseClick) {
        //Log.d("threadingdebug3", "startNewThread: called");
        long timeInPauseScreen = System.currentTimeMillis() - timeOfPauseClick;
        mediator.incrementStartTimeBy(timeInPauseScreen);
        //Log.d("timeinpause", "resumeClick: " + timeInPauseScreen);
        gamePanel.startNewThread();
    }

}
