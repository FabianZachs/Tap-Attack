package com.thezs.fabianzachs.tapattack.MainMenu.Menu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.thezs.fabianzachs.tapattack.ButtonOnTouchListener;
import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Database.MyDBHandler;
import com.thezs.fabianzachs.tapattack.R;
import com.thezs.fabianzachs.tapattack.helper;

import org.w3c.dom.Text;

/**
 * Created by fabianzachs on 19/08/18.
 */

public class PlayGameSection {

    public interface PlayGameListener {
        void playButtonPress();
    }

    public void setListener(PlayGameListener listener) {
        this.listener = listener;
    }

    private Activity activity;
    private PlayGameListener listener;
    private SharedPreferences prefs;
    private MyDBHandler myDBHandler;
    private ImageView playButton;
    private TextView gamemodeText;
    private TextView gamemodeHighscore;

    public PlayGameSection(Activity activity, View view, SharedPreferences prefs, MyDBHandler myDBHandler) {
        this.activity = activity;
        this.prefs = prefs;
        this.myDBHandler = myDBHandler;

        this.playButton = view.findViewById(R.id.play_button);
        this.gamemodeText = view.findViewById(R.id.gamemode_title);
        this.gamemodeHighscore = view.findViewById(R.id.highscore_text);

        setOnTouch();

    }

    public void updatePlayGameSection() {
        playButton.setImageResource(helper.getResourceId(activity, myDBHandler.getCurrentGamemodeFile()));
        final String gamemodeName = prefs.getString(Constants.GAME_MODE_TAG, Constants.GAMEMODES[0]);
        gamemodeText.setText(gamemodeName.toUpperCase());

        // todo no highscore for tutorial, pass/fail for story and actual highscore
        if (gamemodeName.equals(Constants.GAMEMODES[0]))
            gamemodeHighscore.setText("");
        else {
            int highscore = prefs.getInt(gamemodeName+"highscore", 0);
            gamemodeHighscore.setText(activity.getResources().getString(R.string.highscoreText, highscore));
        }
    }

    public void startAnimation() {
        Animation animation = AnimationUtils.loadAnimation(activity, R.anim.selected_item);
        playButton.startAnimation(animation);
    }

    public void stopAnimation() {
        playButton.clearAnimation();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setOnTouch() {
        playButton.setOnTouchListener(new ButtonOnTouchListener(activity, playButton, new ButtonOnTouchListener.ButtonExecuteListener() {
            @Override
            public void buttonAction() {
                listener.playButtonPress();
            }
        }));
    }
}
