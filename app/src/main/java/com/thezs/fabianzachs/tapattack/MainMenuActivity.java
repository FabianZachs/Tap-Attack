package com.thezs.fabianzachs.tapattack;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainMenuActivity extends AppCompatActivity {

    private SharedPreferences soundImg;
    private ArrayList<MediaPlayer> mediaPlayers; // these players loop -> turn of onStop()

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mediaPlayers = new ArrayList<MediaPlayer>();

        // to make app fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main_menu);
        playSound(R.raw.mainmenu, true);
    }

    // shows the settings alert dialog
    public void settingsClick(View view) {

        // play settings click noise
        playSound(R.raw.opensettings, false);

        // create a builder for the alert
        AlertDialog.Builder dbuilder = new AlertDialog.Builder(this);
        View alertView = getLayoutInflater().inflate(R.layout.dialog_settings, null);

        // define the views inside the layout
        final ImageView soundButt = (ImageView) alertView.findViewById(R.id.sound_setting);

        // depending on the current sound setting- set to ON or OFF img
        final SharedPreferences prefs = getSharedPreferences("playerPrefs", MODE_PRIVATE);
        setSoundImg(prefs, soundButt);


        // on click of the soundButt, set to opposite img
        soundButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (soundOn(prefs)) {
                    setSoundPrefAndImg(false, prefs, soundButt);
                } else {
                    setSoundPrefAndImg(true, prefs, soundButt);
                }
                playSound(R.raw.settingsswitch, false);
            }
        });

        dbuilder.setView(alertView);
        final AlertDialog dialog = dbuilder.create();

        // to remove square edges from custom dialog shape
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        // for OK button
        ImageView okButt = (ImageView) alertView.findViewById(R.id.ok_button);
        okButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSound(R.raw.closesettings, false);
                dialog.dismiss();
            }
        });

        dialog.show();


    }

    private void playSound(int sound, boolean repeat) {
        MediaPlayer mp = MediaPlayer.create(this, sound);
        if (repeat == true) {
            mp.setLooping(true);
            mediaPlayers.add(mp);
        }
        mp.start();
    }

    public boolean soundOn(SharedPreferences prefs) {
        return prefs.getBoolean("sound", true);
    }

    public void setSoundImg(SharedPreferences prefs, ImageView soundButt) {
        if (soundOn(prefs)) {
            // set ON img
            soundButt.setImageResource(R.drawable.soundon);
        } else {
            // set OFF toggle
            soundButt.setImageResource(R.drawable.soundoff);
        }
    }

    private void setSoundPrefAndImg(boolean onOrOff, SharedPreferences prefs, ImageView soundButt) {
        SharedPreferences.Editor prefsEditior = prefs.edit();
        prefsEditior.putBoolean("sound", onOrOff);
        prefsEditior.apply();
        setSoundImg(prefs, soundButt);

    }

    // when app opens up again
    @Override
    protected void onResume() {
        super.onResume();
        for (MediaPlayer mp : mediaPlayers) {
            mp.start();
        }
    }

    // when app is closed
    @Override
    protected void onStop() {
        super.onStop();
        for (MediaPlayer mp : mediaPlayers) {
            mp.pause();
        }
    }
}
