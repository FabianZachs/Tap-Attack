package com.thezs.fabianzachs.tapattack.GameFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.R;

public class SoundEffectsManager {

    public SoundEffects soundEffects;

    public SoundEffectsManager() {
        SharedPreferences prefs = Constants.CURRENT_CONTEXT.getSharedPreferences("playerInfo", Context.MODE_PRIVATE);
        if (prefs.getInt("fx", 1) == 1) {
            this.soundEffects = new SoundEffectsOn();
        }
        else {
            this.soundEffects = new SoundEffectsOff();
        }
    }

    public interface SoundEffects {
        void playCircleTap();
        void playSquareTapOne();
        void playSquareTapTwo();
        void playStarTap();
        void playArrowSwipe();
        void playGameOver();

    }

    private class SoundEffectsOn implements SoundEffects {

        private int MAX_STREAMS = 4;

        private AudioAttributes audioAttributes;

        private SoundPool soundPool;
        private int circleTap;
        private int squareTapOne;
        private int squareTapTwo;
        private int arrowSwipe;
        private int starTap;
        private int gameOver;

        private SoundEffectsOn() {

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

            squareTapOne = soundPool.load(Constants.CURRENT_CONTEXT, R.raw.squaretaponesound, 1);
            squareTapTwo = soundPool.load(Constants.CURRENT_CONTEXT, R.raw.squaretaptwosound, 1);

            arrowSwipe = soundPool.load(Constants.CURRENT_CONTEXT, R.raw.swosh08, 1);

            starTap = soundPool.load(Constants.CURRENT_CONTEXT, R.raw.coin2, 1);

            gameOver = soundPool.load(Constants.CURRENT_CONTEXT, R.raw.dm25, 1);
        }

        @Override
        public void playCircleTap() {
            soundPool.play(circleTap, 1.0f, 1.0f, 1, 0, 1.0f);
        }

        @Override
        public void playSquareTapOne() {
            soundPool.play(squareTapOne, 1.0f, 1.0f, 1, 0, 1.0f);
        }

        @Override
        public void playSquareTapTwo() {
            soundPool.play(squareTapTwo, 1.0f, 1.0f, 1, 0, 1.0f);
        }

        @Override
        public void playStarTap() {
            soundPool.play(starTap, 1.0f, 1.0f, 1, 0, 1.0f);
        }

        @Override
        public void playArrowSwipe() {
            soundPool.play(arrowSwipe, 1.0f, 1.0f, 1, 0, 1.0f);
        }

        @Override
        public void playGameOver() {
            soundPool.play(gameOver, 1.0f, 1.0f, 1, 0, 1.0f);
        }
    }

    public class SoundEffectsOff implements SoundEffects {

        @Override
        public void playCircleTap() {
        }

        @Override
        public void playSquareTapOne() {
        }

        @Override
        public void playSquareTapTwo() {
        }

        @Override
        public void playStarTap() {
        }

        @Override
        public void playArrowSwipe() {
        }

        @Override
        public void playGameOver() {
        }
    }
}
