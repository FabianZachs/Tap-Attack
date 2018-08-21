package com.thezs.fabianzachs.tapattack.MainMenu.Store.TopStoreUIComponents;

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.thezs.fabianzachs.tapattack.R;

/**
 * Created by fabianzachs on 15/08/18.
 */

public class StorePoints {

    private Activity activity;
    private TextView pointsText;
    private SharedPreferences prefs;
    private StorePointsListener listener;


    public interface StorePointsListener{
        void pointsUpdated();
    }

    public void setStorePointsListener(StorePointsListener listener) {
        this.listener = listener;

    }

    public StorePoints(Activity activity, View view, SharedPreferences prefs) {
        this.activity = activity;
        this.prefs = prefs;
        this.pointsText = view.findViewById(R.id.current_points_text);
    }

    public int getCurrentPoints() {
        return prefs.getInt("points", 0);
    }

    public void updateCurrentPoints() {

        Animation updatePointsText = AnimationUtils.loadAnimation(activity, R.anim.slow_scale_up);
        pointsText.startAnimation(updatePointsText);

        pointsText.setText(activity.getResources().getString(R.string.currentPoints, prefs.getInt("points", 0)));
        if (listener != null)
            listener.pointsUpdated();
    }

    public void addToPointsAndUpdateView(int addPoints) {
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putInt("points", prefs.getInt("points", 0 ) + addPoints);
        prefsEditor.apply();
        updateCurrentPoints();
    }

}
