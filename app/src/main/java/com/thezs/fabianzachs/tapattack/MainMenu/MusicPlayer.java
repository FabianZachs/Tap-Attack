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
        Log.d("musicplayer", "MusicPlayer: A");
        this.activity = activity;
    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        Log.d("musicerror", "onError: musicplayererrow");
        Log.d("musicplayer", "MusicPlayer: B");
        musicPlayer.reset();
        return false;
    }

    public void play() {
        Log.d("musicplayer", "MusicPlayer: C.2");
        if (musicPlayer == null) {
            musicPlayer = MediaPlayer.create(activity, R.raw.mysong2);
            musicPlayer.setLooping(true);
            Log.d("musicplayer", "MusicPlayer: C");
        }
        musicPlayer.start();
    }

    public void pausePlaying() {
        Log.d("musicplayer", "MusicPlayer: D");
        if (musicPlayer != null)
            musicPlayer.pause();
    }

    public void musicPlayerNotNeeded() {
        Log.d("musicplayer", "MusicPlayer: E");
        if (musicPlayer != null) {
            musicPlayer.release();
            musicPlayer = null;
        }
    }
}
