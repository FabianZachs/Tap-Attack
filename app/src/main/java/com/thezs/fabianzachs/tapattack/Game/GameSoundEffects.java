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


    private int MAX_STREAMS = 4;

    private AudioAttributes audioAttributes;

    public static SoundPool soundPool;
    private static int circleTap;
    private static int squareTapOne;
    private static int squareTapTwo;
    private static int arrowSwipe;
    private static int warningColorTap;
    private static int starTap;
    private static int gameOver;


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

        // 1
        squareTapOne = soundPool.load(Constants.CURRENT_CONTEXT, R.raw.squaretaponesound, 1);
        squareTapTwo = soundPool.load(Constants.CURRENT_CONTEXT, R.raw.squaretaptwosound, 1);


        starTap = soundPool.load(Constants.CURRENT_CONTEXT, R.raw.coin2, 1);
        //arrowSwipe = soundPool.load(Constants.CURRENT_CONTEXT, R.raw.swosh43, 1);
        //arrowSwipe = soundPool.load(Constants.CURRENT_CONTEXT, R.raw.arrow1, 1);
        arrowSwipe = soundPool.load(Constants.CURRENT_CONTEXT, R.raw.swosh08, 1);
        //arrowSwipe = soundPool.load(Constants.CURRENT_CONTEXT, R.raw.swosh25, 1);
        //arrowSwipe = soundPool.load(Constants.CURRENT_CONTEXT, R.raw.swosh01, 1);


        gameOver = soundPool.load(Constants.CURRENT_CONTEXT, R.raw.dm25, 1);
        //gameOver = soundPool.load(Constants.CURRENT_CONTEXT, R.raw.rollover6, 1);
        //gameOver = soundPool.load(Constants.CURRENT_CONTEXT, R.raw.zap2, 1);



        //starTap = soundPool.load(Constants.CURRENT_CONTEXT, R.raw.coin7, 1);
        // 2
        //squareTapOne = soundPool.load(Constants.CURRENT_CONTEXT, R.raw.bookopen, 1);
        //squareTapTwo = soundPool.load(Constants.CURRENT_CONTEXT, R.raw.bookclose, 1);

        // 3
        //squareTapOne = soundPool.load(Constants.CURRENT_CONTEXT, R.raw.switch31, 1);
        //squareTapTwo = soundPool.load(Constants.CURRENT_CONTEXT, R.raw.switch32, 1);

        // 4
        //squareTapOne = soundPool.load(Constants.CURRENT_CONTEXT, R.raw.bookplace1, 1);
        //squareTapTwo = soundPool.load(Constants.CURRENT_CONTEXT, R.raw.bookclose, 1);


    }


    public void playCircleTap() {
        soundPool.play(circleTap, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void playSquareTapOne() {
        soundPool.play(squareTapOne, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void playSquareTapTwo() {
        soundPool.play(squareTapTwo, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void playStarTap() {
        soundPool.play(starTap, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void playArrowSwipe() {
        soundPool.play(arrowSwipe, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void playGameOver() {
        soundPool.play(gameOver, 1.0f, 1.0f, 1, 0, 1.0f);
    }
}
