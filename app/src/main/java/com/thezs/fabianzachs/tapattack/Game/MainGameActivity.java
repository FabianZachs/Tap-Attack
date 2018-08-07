package com.thezs.fabianzachs.tapattack.Game;

        import android.app.Activity;
        import android.app.AlertDialog;
        import android.app.Dialog;
        import android.content.Context;
        import android.content.SharedPreferences;
        import android.graphics.Color;
        import android.graphics.Typeface;
        import android.graphics.drawable.ColorDrawable;
        import android.graphics.drawable.Drawable;
        import android.graphics.drawable.LayerDrawable;
        import android.media.MediaPlayer;
        import android.os.Bundle;
        import android.os.Handler;
        import android.support.annotation.Nullable;
        import android.support.v4.content.ContextCompat;
        import android.util.Log;
        import android.view.Gravity;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
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
    MediaPlayer gameOverMusic;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper.makeFullscreen(this);

        gameOverMusic = MediaPlayer.create(this, R.raw.gameoverscreen);

        setContentView(R.layout.activity_main_game);


        bootstrapViewSetup();
        Constants.GAME_ACTIVITY = this;

        LinearLayout viewForGamePanel = (LinearLayout) findViewById(R.id.game_panel_surface);
        // todo initimediator here
        this.mediator = new CentralGameCommunication(System.currentTimeMillis());
        mediator.addObject(this);

        // soundeffects
        this.mediator.addObject(new GameSoundEffects());


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

        SharedPreferences prefs = getSharedPreferences("playerInfo", MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        //int bestScore = prefs.getInt(/*CONSTANTS.CURRENTGAEMODE + */"bestScore", 0);// todo make work  for different current gamemodes
        String selectedGameMode = prefs.getString("gamemode", Constants.GAMEMODES[0]);
        int highscoreForSelectedGame = prefs.getInt(selectedGameMode+"highscore", 0);
        //int bestStreak = prefs.getInt(/*CONSTANTS.CURRENTGAEMODE + */"bestStreak", 0);// todo make work  for different current gamemodes


        TextView bestScoreText = (TextView) alertView.findViewById(R.id.shape_type_image_description);
        //TextView bestStreakText = (TextView) alertView.findViewById(R.id.shape_theme_image_description);

        // todo refactorable
        if (currentGameScore > highscoreForSelectedGame) {
            highscoreForSelectedGame = currentGameScore;
            prefsEditor.putInt(selectedGameMode+"highscore", currentGameScore);
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


        bestScoreText.setText("BEST SCORE: " + highscoreForSelectedGame);

        //bestStreakText.setText("BEST STREAK: " + bestStreak);
        TextView pointsEquation = (TextView) alertView.findViewById(R.id.points_text);


        float scoreMultiplier = getScoreMultiplier(prefs.getString("multiplier", "basic"));






        //float scoreMultiplier = 10000;
        int pointsearned = (int) (scoreMultiplier * currentGameScore);
        pointsEquation.setText(currentGameScore + " x MULTIPLIER (" + scoreMultiplier + ") = +" + pointsearned + " POINTS");

        int currentPoints = prefs.getInt("points", 0);
        prefsEditor.putInt("points", currentPoints + pointsearned);
        prefsEditor.apply();
        //Log.d("endintent", "setupGameOverFields: " + prefs.getInt("points", 0));

    }

    private float getScoreMultiplier(String multiplierText) {
        float multiplier = 0;
        switch (multiplierText) {
            case "basic":
                multiplier = 1.0f;
                break;
            case "decent":
                multiplier = 1.5f;
                break;
            case "heating up":
                multiplier = 2.0f;
                break;
            case "burning":
                multiplier = 3.0f;
                break;
            case "fire!":
                multiplier = 5.0f;
                break;
            case "insane!!":
                multiplier = 10.0f;
                break;
        }
        return multiplier;
    }

    public void showGameOverScreen(final String gameOverReason, final int scoreToDisplay, final int streakToDisplay) {

        gameOverMusic.setLooping(true);
        gameOverMusic.start();


        /*
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                gameOverMusic.start();
            }
        }, 600);
        */

        final Activity activity = this;
        this.runOnUiThread(new Runnable() {
            public void run() {
                //View alertView = this.getLayoutInflater().inflate(R.layout.game_over, null);
                /*
                View alertView = helper.getAlertView(activity, R.layout.game_over);

                //setupGameOverFields(alertView, gameOverReason, scoreToDisplay, streakToDisplay);

                final AlertDialog dialog = helper.getBuiltDialog(activity, alertView);

                //AlertDialog.Builder dbuilder = new AlertDialog.Builder(this);
                //dbuilder.setView(alertView);
                //final AlertDialog dialog = dbuilder.create();

                dialog.setCancelable(false); // todo add after done testign


                //WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
                //lp.dimAmount=0.9f; // Dim level. 0.0 - no dim, 1.0 - completely opaque


                helper.dialogFullscreen(activity, dialog);
                */



                Dialog dialog1 = new Dialog(activity,android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                dialog1.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
                dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog1.setContentView(R.layout.game_over2);

                TextView gameOverText = (TextView) dialog1.findViewById(R.id.game_over_reason);
                gameOverText.setGravity(View.TEXT_ALIGNMENT_CENTER);
                gameOverText.setText(gameOverReason);

                Window window = dialog1.getWindow();
                WindowManager.LayoutParams wlp = window.getAttributes();

                wlp.gravity = Gravity.CENTER;
                wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
                window.setAttributes(wlp);
                dialog1.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);


                //WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
                //lp.dimAmount=0.0f; // Dim level. 0.0 - no dim, 1.0 - completely opaque
                //window.setDimAmount(1f);
                //dialog1.getWindow().setAttributes(lp);


                AdView adView = (AdView) dialog1.findViewById(R.id.adView);
                MobileAds.initialize(activity, "ca-app-pub-3940256099942544~3347511713");
                //AdView adView = (AdView) activity.findViewById(R.id.adView);
                AdRequest adRequest = new AdRequest.Builder().build();
                adView.loadAd(adRequest);

                //dialog1.getWindow().setBackgroundDrawable(new ColorDrawable("0xffffff"));
                Drawable background = ContextCompat.getDrawable(activity, R.drawable.game_over_dialog_shape);
                background.setAlpha(245);

                dialog1.getWindow().setBackgroundDrawable(background);

                dialog1.show();








                /*
                //Dialog dialog1 = new Dialog(activity, android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
                Dialog dialog1 = new Dialog(activity, android.R.style.Theme_DeviceDefault_NoActionBar_TranslucentDecor);
                dialog1.setContentView(getLayoutInflater().inflate(R.layout.game_over,null));
                dialog1.show();



                /*

                Dialog dialog1 =new Dialog(activity,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
                dialog1.setContentView(R.layout.game_over);

                WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
                lp.dimAmount=0.5f; // Dim level. 0.0 - no dim, 1.0 - completely opaque
                dialog1.getWindow().setAttributes(lp);


                dialog1.show();
                */


                //dialog.getWindow().setLayout(Constants.SCREEN_WIDTH, WindowManager.LayoutParams.MATCH_PARENT);

                //TextView score = (TextView) alertView.findViewById(R.id.score_text);
                //YoYo.with(Techniques.Bounce).duration(1000).repeat(3).playOn(score);

                AdView pauseBannerAd;
                // todo banner ad
                //bannerAdSetup(alertView);
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
        //bannerAdSetup(alertView);
    }


    /*
    public void bannerAdSetup(View alertView) {
        // ads (below setContentView)
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        AdView adView = (AdView) alertView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }*/

    @Override
    protected void onPause() {
        super.onPause();
        gameOverMusic.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mediator.isGameOver)
            gameOverMusic.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        gameOverMusic.stop();
    }

    public void exitClick(View view) {
        setResult(1);
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
