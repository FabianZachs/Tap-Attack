package com.thezs.fabianzachs.tapattack;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainMenuActivity extends AppCompatActivity {

    private ArrayList<MediaPlayer> mediaPlayers; // these players loop -> turn of onStop()
    private SharedPreferences prefs;
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // method instantiation
        mediaPlayers = new ArrayList<MediaPlayer>();
        prefs = getSharedPreferences("playerPrefs", MODE_PRIVATE);

        makeFullscreen();

        setContentView(R.layout.activity_main_menu);

        // TextView menuText = (TextView) findViewById(R.id.menu_text);
        //menuText.setPaintFlags(menuText.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        //menuText.setText(Html.fromHtml("<u>MENU</u>", Html.FROM_HTML_MODE_COMPACT));
/*
        SpannableString content = new SpannableString(getString(R.string.menu));
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        menuText.setText(content);
*/

        // ads (below setContentView)
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        initMusic(R.raw.mainmenu);
    }


    // shows the settings alert dialog
    public void menuClick(View view) {

        // play settings click noise
        playSound(R.raw.opensettings);

        // create a builder for the alert
        AlertDialog.Builder dbuilder = new AlertDialog.Builder(this);
        View alertView = getLayoutInflater().inflate(R.layout.dialog_settings, null);

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


        dbuilder.setView(alertView);
        final AlertDialog dialog = dbuilder.create();

        // to remove square edges from custom dialog shape
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        // for OK button
        TextView okButt = (TextView) alertView.findViewById(R.id.ok_button);

        okButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSound(R.raw.closesettings);
                dialog.dismiss();
            }
        });

        // dialog fullscreen
        //Set the dialog to not focusable (makes navigation ignore us adding the window)
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        dialog.show();
        //Set the dialog to immersive
        dialog.getWindow().getDecorView().setSystemUiVisibility(
                this.getWindow().getDecorView().getSystemUiVisibility());

//Clear the not focusable flag from the window
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
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


    private void makeFullscreen() {
        // task bar
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        View decorView = getWindow().getDecorView();
        // Hide both the navigation bar and the status bar.
        // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
        // a general rule, you should design your app to hide the status bar whenever you
        // hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);
    }





    // when app is closed
    @Override
    protected void onStop() {
        super.onStop();
        repeatMpStop();
    }

    public void playButton(View view) {
        Intent intent = new Intent(this, Play2Activity.class);
        intent.putExtra("gamemode", "classic");
        startActivity(intent);
    }
}
