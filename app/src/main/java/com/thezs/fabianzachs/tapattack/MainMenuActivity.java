package com.thezs.fabianzachs.tapattack;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;
import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.thezs.fabianzachs.tapattack.Animation.Themes.ThemesManager;
import com.thezs.fabianzachs.tapattack.Game.MainGameActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class MainMenuActivity extends  GeneralParent {

    private ArrayList<MediaPlayer> mediaPlayers; // these players loop -> turn of onStop()
    private ThemesManager themesManager;
    private SharedPreferences prefs;
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        helper.makeFullscreen(this);

        // set up Constants
        initializeConstants();

        // method instantiation
        //mediaPlayers = new ArrayList<MediaPlayer>();
        prefs = getSharedPreferences("playerPrefs", MODE_PRIVATE);


        setContentView(R.layout.activity_main_menu);

        helper.bannerAdSetup(this, mAdView);



        // for images in store
        this.themesManager = new ThemesManager();
        //gameTheme = themesManager.buildTheme("vibrant", "curved");
        //themesManager.setCurrentTheme(themesManager.buildTheme(Constants.CURRENT_THEME,Constants.CURRENT_SHAPE_TYPE));
        themesManager.setCurrentTheme(themesManager.buildTheme(prefs.getString("shapeTheme", "neon"),prefs.getString("shapeType","curved")));


        //initMusic(R.raw.mainmenu);
    }

    private void initializeConstants() {

        Constants.CURRENT_CONTEXT = this;

        Point screenDimension = screenResolution();
        // get screen dimensions stored
        Constants.SCREEN_WIDTH = screenDimension.x;
        // TODO bug: screen height from dm is incorrect for pixel
        Constants.SCREEN_HEIGHT = screenDimension.y;
        //Log.d("height", "initializeConstants: height: " + Constants.SCREEN_HEIGHT );

        Constants.SHAPE_WIDTH = Constants.SHAPE_HEIGHT = Constants.SCREEN_WIDTH/5 + Constants.SCREEN_WIDTH/25;


        // put colors for all theme packs
        Constants.COLORS = new HashMap<>();
        Constants.COLORS.put("neon", Constants.NEONCOLORS);

        // holder colors
        Constants.progressBarHolderAndWarningHolderColors = new HashMap<>();
        Constants.progressBarHolderAndWarningHolderColors.put("blue",Constants.holderBlue);
        Constants.progressBarHolderAndWarningHolderColors.put("green",Constants.holderGreen);
        Constants.progressBarHolderAndWarningHolderColors.put("yellow",Constants.holderYellow);
        Constants.progressBarHolderAndWarningHolderColors.put("purple",Constants.holderPurple);
        Constants.progressBarHolderAndWarningHolderColors.put("red",Constants.holderRed);
        // TODO do rest ...

        // top based on where warning color is
        //Log.d("screenwidth", "initializeConstants: width: " + Constants.SCREEN_WIDTH);
        //Constants.SHAPE_CLICK_AREA = new Rect(Constants.SCREEN_WIDTH/20, 30 + (Constants.SCREEN_HEIGHT/40 +25) + 20 + Constants.SCREEN_WIDTH/15 + 10 + Constants.SHAPE_HEIGHT/2, Constants.SCREEN_WIDTH - Constants.SCREEN_WIDTH/20, Constants.SCREEN_HEIGHT - 1);
        //Constants.SHAPE_CREATION_AREA = new Rect(Constants.SHAPE_CLICK_AREA.left + Constants.SHAPE_WIDTH/2,Constants.SHAPE_CLICK_AREA.top + Constants.SHAPE_HEIGHT/2,
        //                                        Constants.SHAPE_CLICK_AREA.right - Constants.SHAPE_WIDTH/2, Constants.SHAPE_CLICK_AREA.bottom - Constants.SHAPE_HEIGHT/2);
        Constants.SHAPE_CLICK_AREA = new Rect(Constants.SCREEN_WIDTH/20, 0, Constants.SCREEN_WIDTH - Constants.SCREEN_WIDTH/20, Constants.SCREEN_HEIGHT - 1);
        Constants.SHAPE_CREATION_AREA = new Rect(Constants.SCREEN_WIDTH/20 + Constants.SHAPE_WIDTH/2, -Constants.SHAPE_HEIGHT -50, Constants.SCREEN_WIDTH - Constants.SCREEN_WIDTH/20 - Constants.SHAPE_WIDTH/2,0 );

        Constants.WARNING_COLOR_CLICK_AREA_LEFT = new Rect(0,30 + (Constants.SCREEN_HEIGHT/40 +25) + 20 + Constants.SCREEN_WIDTH/15 + 10 + Constants.SHAPE_HEIGHT/2,Constants.SCREEN_WIDTH/20, Constants.SCREEN_HEIGHT);
        Constants.WARNING_COLOR_CLICK_AREA_RIGHT = new Rect(Constants.SCREEN_WIDTH - Constants.SCREEN_WIDTH/20,30 + (Constants.SCREEN_HEIGHT/40 +25) + 20 + Constants.SCREEN_WIDTH/15 + 10 + Constants.SHAPE_HEIGHT/2,Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private Point screenResolution() {
        WindowManager windowManager =
                (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point screenResolution = new Point();

        if (Build.VERSION.SDK_INT < 14)
            throw new RuntimeException("Unsupported Android version.");
        display.getRealSize(screenResolution);

        return screenResolution;
    }


    // shows the settings alert dialog
    public void menuClick(View view) {

        // play settings click noise
        //playSound(R.raw.opensettings);

        // inflate the dialog layout
        View alertView = getLayoutInflater().inflate(R.layout.dialog_settings, null);

        soundTogglerSetup(alertView);

        // create a builder for the alert
        AlertDialog.Builder dbuilder = new AlertDialog.Builder(this);

        dbuilder.setView(alertView);
        final AlertDialog dialog = dbuilder.create();

        okButtonSetup(alertView, dialog);

        dialogFullscreen(dialog);
    }

    private void dialogFullscreen(AlertDialog dialog) {

        // to remove square edges from custom dialog shape
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //Set the dialog to not focusable (makes navigation ignore us adding the window)
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        dialog.show();
        //Set the dialog to immersive
        dialog.getWindow().getDecorView().setSystemUiVisibility(
                this.getWindow().getDecorView().getSystemUiVisibility());

        //Clear the not focusable flag from the window
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
    }


    private void okButtonSetup(View alertView, final AlertDialog dialog) {

        TextView okButt = (TextView) alertView.findViewById(R.id.ok_button);

        okButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //playSound(R.raw.closesettings);
                dialog.dismiss();
            }
        });
    }


    private void soundTogglerSetup(View alertView) {
        // sound toggler view
        final TextView soundText = (TextView) alertView.findViewById(R.id.sound_setting);

        // depending on the current sound setting- set to ON or OFF img
        //setSoundText(soundText);

        // on click of the soundText, set to opposite img
        soundText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (soundOn()) {
                    // set OFF
                    setSoundPrefAndText(false, soundText);
                    //repeatMpStop();
                } else {
                    // set ON
                    setSoundPrefAndText(true, soundText);
                    //repeatMpResume();
                }
                //playSound(R.raw.settingsswitch);
            }
        });
    }


    // plays music
    private void initMusic(int sound) {
        MediaPlayer mp = MediaPlayer.create(this, sound);
        mp.setLooping(true);
        mediaPlayers.add(mp);
        if (soundOn()) mp.start();
    }


    // plays sound if sound is ON
    private void playSound(int sound) {

        if (soundOn()) {
            MediaPlayer mp = MediaPlayer.create(this, sound);
            mp.start();
        }
    }


    public boolean soundOn() {
        return prefs.getBoolean("sound", true);
    }


    public void setSoundText(TextView soundText) {
        if (soundOn()) {
            // set ON img
            soundText.setText("ON");
            soundText.setTextColor(getResources().getColor(R.color.soundon));
        } else {
            // set OFF toggle
            soundText.setText("OFF");
            soundText.setTextColor(getResources().getColor(R.color.soundoff));
        }
    }


    private void setSoundPrefAndText(boolean onOrOff, TextView soundText) {
        SharedPreferences.Editor prefsEditior = prefs.edit();
        prefsEditior.putBoolean("sound", onOrOff);
        prefsEditior.apply();
        setSoundText(soundText);
    }

/*
    public void repeatMpStop() {
        for (MediaPlayer mp : mediaPlayers) {
                mp.pause();
        }
    }*/


    /*public void repeatMpResume() {
        for (MediaPlayer mp : mediaPlayers) {
            if (soundOn())
                mp.start();
        }
    }*/


    // when app opens up again
    @Override
    protected void onResume() {
        super.onResume();
        //repeatMpResume();
    }


    // when app is closed
    @Override
    protected void onStop() {
        super.onStop();
        //repeatMpStop();
    }


    public void playButtonClick(View view) {
        Intent intent = new Intent(this, MainGameActivity.class);
        intent.putExtra("gamemode", "classic");
        this.startActivity(intent);
    }



    /*
    private void okButtonSetup(View alertView, final AlertDialog dialog) {

        TextView okButt = (TextView) alertView.findViewById(R.id.ok_button);

        okButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //playSound(R.raw.closesettings);
                dialog.dismiss();
            }
        });
    }*/



    public void storeClick(View view) {
        // TODO intialize to default theme in startup- breaks if user doesnt click store to set theme


        View alertView = getLayoutInflater().inflate(R.layout.store_main_menu, null);






        AlertDialog.Builder dbuilder = new AlertDialog.Builder(this);


        dbuilder.setView(alertView);
        final AlertDialog dialog = dbuilder.create();

        okButtonSetup(alertView, dialog);

        dialogFullscreen(dialog);


        /// =====================
        // shape_color_image
        ImageView shapeColorImg = (ImageView) alertView.findViewById(R.id.shape_color_image);

        android.view.ViewGroup.LayoutParams layoutParams = shapeColorImg.getLayoutParams();
        layoutParams.width = Constants.SCREEN_WIDTH/4;
        layoutParams.height = Constants.SCREEN_WIDTH/4;
        shapeColorImg.setLayoutParams(layoutParams);

        shapeColorImg.setImageBitmap(themesManager.getCurrentTheme().getShapeBitmap("circle", "red", false));

        TextView setColorTheme = (TextView) alertView.findViewById(R.id.shape_color_set);
        setColorTheme.setText(prefs.getString("shapeTheme", "neon").toUpperCase());

        // todo make size correct
        // todo border around image


        ////// ================================

        // shape_type_image
        ImageView shapeTypeImg = (ImageView) alertView.findViewById(R.id.shape_type_image);

        android.view.ViewGroup.LayoutParams layoutParams2 = shapeColorImg.getLayoutParams();
        layoutParams.width = Constants.SCREEN_WIDTH/4;
        layoutParams.height = Constants.SCREEN_WIDTH/4;
        shapeTypeImg.setLayoutParams(layoutParams);

        String outlineName = prefs.getString("shapeTpe", "curved") + "outline";
        int resID = this.getResources().getIdentifier(outlineName, "drawable", this.getPackageName());
        shapeTypeImg.setImageResource(resID);


        TextView setTypeShape = (TextView) alertView.findViewById(R.id.shape_type_set);
        setTypeShape.setText(prefs.getString("shapeType", "curved").toUpperCase());

        // =======================================

        // background_image


        // =======================================









        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putString("shapeTheme", "neon");
        prefsEditor.apply();



        StyleableToast.makeText(this,  prefs.getString("theme","error-no theme"), R.style.successtoast).show();
    }


    public void openShapeTypeStore(View view) {
        // todo complete
        StyleableToast.makeText(this,  "shapetype store click",R.style.successtoast).show();
    }

    public void openBackgroundStore(View view) {
        StyleableToast.makeText(this,  "background store click",R.style.successtoast).show();
    }

    public void openShapeColorStore(View view) {
        StyleableToast.makeText(this,  "shape color store click",R.style.successtoast).show();
    }
}
