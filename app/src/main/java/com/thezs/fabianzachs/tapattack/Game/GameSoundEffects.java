package com.thezs.fabianzachs.tapattack.Game;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.provider.MediaStore;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.R;

/**
 * Created by fabianzachs on 16/07/18.
 */

public class GameSoundEffects {

    // todo should have a reference to all sound effects to play

    private int MAX_STREAMS = 2;

    public static SoundPool soundPool;
    private static int circleTap;
    // todo do rest



    public GameSoundEffects() {

        soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);

        circleTap = soundPool.load(Constants.CURRENT_CONTEXT, R.raw.circletapsound, 1);
        // todo do rest

    }


    public void playCircleTap() {
        soundPool.play(circleTap, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    // todo do rest
}
