package com.thezs.fabianzachs.tapattack.MainMenu.Menu;

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.thezs.fabianzachs.tapattack.R;

/**
 * Created by fabianzachs on 18/08/18.
 */

public class PointsSection {

    private Activity activity;
    private SharedPreferences prefs;
    private TextView pointsText;
    private ImageView pointsImage;

    public PointsSection(Activity activity, View view, SharedPreferences prefs) {
        this.activity = activity;
        this.prefs = prefs;
        this.pointsText = view.findViewById(R.id.points_text);
        this.pointsImage = view.findViewById(R.id.more_points_sign);

        updateDisplayedPoints();
        startAnimatingMorePointsImg();

    }

    public void updateDisplayedPoints() {
        pointsText.setText(activity.getResources().getString(R.string.currentPoints, prefs.getInt("points", 0)));
    }

    private void startAnimatingMorePointsImg() {
        YoYo.with(Techniques.Tada).duration(2000).repeat(100).playOn(pointsImage);
        // todo which of Bounce, Swing, Pulse, Flash, Tada
    }
}
