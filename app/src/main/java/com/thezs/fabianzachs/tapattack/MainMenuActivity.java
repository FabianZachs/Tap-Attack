package com.thezs.fabianzachs.tapattack;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;
import com.thezs.fabianzachs.tapattack.Game.GameActivity;

import java.util.ArrayList;

public class MainMenuActivity extends  GeneralParent {

    private ArrayList<MediaPlayer> mediaPlayers; // these players loop -> turn of onStop()
    private SharedPreferences prefs;
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set up Constants
        initializeConstants();

        // method instantiation
        mediaPlayers = new ArrayList<MediaPlayer>();
        prefs = getSharedPreferences("playerPrefs", MODE_PRIVATE);

        helper.makeFullscreen(this);

        setContentView(R.layout.activity_main_menu);

        helper.bannerAdSetup(this, mAdView);

        initMusic(R.raw.mainmenu);
    }

    private void initializeConstants() {

        // get screen dimensions stored
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_WIDTH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;

        Constants.SHAPE_HEIGHT = Constants.SCREEN_WIDTH/10;
        Constants.SHAPE_WIDTH = Constants.SHAPE_WIDTH;

        Constants.SHAPE_SPACING = 5;
    }


    // shows the settings alert dialog
    public void menuClick(View view) {

        // play settings click noise
        playSound(R.raw.opensettings);

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
                playSound(R.raw.closesettings);
                dialog.dismiss();
            }
        });
    }


    private void soundTogglerSetup(View alertView) {
        // sound toggler view
        final TextView soundText = (TextView) alertView.findViewById(R.id.sound_setting);

        // depending on the current sound setting- set to ON or OFF img
        setSoundText(soundText);

        // on click of the soundText, set to opposite img
        soundText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (soundOn()) {
                    // set OFF
                    setSoundPrefAndText(false, soundText);
                    repeatMpStop();
                } else {
                    // set ON
                    setSoundPrefAndText(true, soundText);
                    repeatMpResume();
                }
                playSound(R.raw.settingsswitch);
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


    public void repeatMpStop() {
        for (MediaPlayer mp : mediaPlayers) {
                mp.pause();
        }
    }


    public void repeatMpResume() {
        for (MediaPlayer mp : mediaPlayers) {
            if (soundOn())
                mp.start();
        }
    }


    // when app opens up again
    @Override
    protected void onResume() {
        super.onResume();
        repeatMpResume();
    }


    // when app is closed
    @Override
    protected void onStop() {
        super.onStop();
        repeatMpStop();
    }


    public void playButtonClick(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("gamemode", "classic");
        startActivity(intent);
    }
}
