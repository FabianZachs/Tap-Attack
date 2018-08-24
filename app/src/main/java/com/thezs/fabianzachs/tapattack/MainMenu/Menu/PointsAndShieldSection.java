package com.thezs.fabianzachs.tapattack.MainMenu.Menu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thezs.fabianzachs.tapattack.ButtonOnTouchListener;
import com.thezs.fabianzachs.tapattack.R;

/**
 * Created by fabianzachs on 18/08/18.
 */

public class PointsAndShieldSection {

    private Activity activity;
    private SharedPreferences prefs;
    private LinearLayout pointsAndShieldSection;
    private TextView pointsText;
    private ImageView pointsImage;
    private TextView shieldsText;
    private MainMenuFragment2.MainMenuListener mainMenuListener;

    public PointsAndShieldSection(Activity activity, MainMenuFragment2.MainMenuListener mainMenuListener, View view, SharedPreferences prefs) {
        this.activity = activity;
        this.mainMenuListener = mainMenuListener;
        this.prefs = prefs;
        this.pointsAndShieldSection = view.findViewById(R.id.shields_and_points_section);
        this.pointsText = view.findViewById(R.id.points_text);
        //this.pointsImage = view.findViewById(R.id.more_points_sign);
        this.shieldsText = view.findViewById(R.id.shields_text);

        setupMorePointsSection();

    }

    public void updateDisplayedPointsAndShields() {
        pointsText.setText(activity.getResources().getString(R.string.currentPoints, prefs.getInt("points", 0)));
        shieldsText.setText(activity.getResources().getString(R.string.currentShields, prefs.getInt("shields", 0)));

    }


    public void startAnimatingMorePointsImg() {
        //YoYo.with(Techniques.Tada).duration(2000).repeat(100).playOn(pointsImage);
        // todo which of Bounce, Swing, Pulse, Flash, Tada
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupMorePointsSection() {
        pointsAndShieldSection.setOnTouchListener(new ButtonOnTouchListener(activity, pointsAndShieldSection, new ButtonOnTouchListener.ButtonExecuteListener() {
            @Override
            public void buttonAction() {
                mainMenuListener.mainMenuFragmentToMorePointsFragment();
            }
        }));
    }
}
