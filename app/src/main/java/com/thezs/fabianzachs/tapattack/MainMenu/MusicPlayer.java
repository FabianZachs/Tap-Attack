package com.thezs.fabianzachs.tapattack.MainMenu;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import com.thezs.fabianzachs.tapattack.R;

import java.net.URI;

/**
 * Created by fabianzachs on 12/08/18.
 */

public class MusicPlayer implements MediaPlayer.OnErrorListener {

    private MediaPlayer musicPlayer;
    private Activity activity;


    public MusicPlayer(Activity activity) {
        this.activity = activity;
    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        musicPlayer.reset();
        return false;
    }

    public void play() {
        if (musicPlayer == null) {
            //musicPlayer = MediaPlayer.create(activity, R.raw.mysong2);
            musicPlayer = MediaPlayer.create(activity, R.raw.enchantedtiki86);
            musicPlayer.setLooping(true);
        }
        musicPlayer.start();
    }

    public void pausePlaying() {
        if (musicPlayer != null)
            musicPlayer.pause();
    }

    public void musicPlayerNotNeeded() {
        if (musicPlayer != null) {
            musicPlayer.release();
            musicPlayer = null;
        }
    }
}
