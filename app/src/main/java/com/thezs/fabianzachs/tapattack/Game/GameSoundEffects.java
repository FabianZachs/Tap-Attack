package com.thezs.fabianzachs.tapattack.Game;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.provider.MediaStore;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Database.BasicStoreItem;
import com.thezs.fabianzachs.tapattack.R;

/**
 * Created by fabianzachs on 16/07/18.
 */

public class GameSoundEffects {

    // todo should have a reference to all sound effects to play

    private int MAX_STREAMS = 2;

    private AudioAttributes audioAttributes;

    public static SoundPool soundPool;
    private static int circleTap;
    // todo do rest



    public GameSoundEffects() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setAudioAttributes(audioAttributes)
                    .setMaxStreams(MAX_STREAMS)
                    .build();
        } else {
            soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        }



        circleTap = soundPool.load(Constants.CURRENT_CONTEXT, R.raw.circletapsound, 1);
        // todo do rest

    }


    public void playCircleTap() {
        soundPool.play(circleTap, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    // todo do rest
}
