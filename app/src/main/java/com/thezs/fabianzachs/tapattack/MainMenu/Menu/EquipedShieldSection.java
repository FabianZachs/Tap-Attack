package com.thezs.fabianzachs.tapattack.MainMenu.Menu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.thezs.fabianzachs.tapattack.ButtonOnTouchListener;
import com.thezs.fabianzachs.tapattack.R;

public class EquipedShieldSection {

    private SharedPreferences prefs;
    private ImageView equipedShieldImage;

    @SuppressLint("ClickableViewAccessibility")
    public EquipedShieldSection(Activity activity, View view, final SharedPreferences prefs) {
        this.prefs = prefs;
        this.equipedShieldImage = view.findViewById(R.id.equiped_shield);

        if (prefs.getInt("powerups", 0) < 1) {
            disableShields();
        }

        updateShieldImage();

        equipedShieldImage.setOnTouchListener(new ButtonOnTouchListener(activity, equipedShieldImage, new ButtonOnTouchListener.ButtonExecuteListener() {
            @Override
            public void buttonAction() {
                if (prefs.getInt("powerups", 0) < 1)
                    disableShields();
                else if (prefs.getBoolean("shieldEnabled", true))
                    disableShields();
                else enableShields();
            }
        }));
    }

    private void updateShieldImage() {
        if (prefs.getInt("powerups", 0) < 1) {
            disableShields();
        }
        else if (prefs.getBoolean("shieldEnabled", true))
            colorImage();
        else greyScaleImage();

    }

    private void disableShields() {
        greyScaleImage();
        prefs.edit().putBoolean("shieldEnabled", false).apply();
    }

    private void enableShields() {
        colorImage();
        prefs.edit().putBoolean("shieldEnabled", true).apply();
    }

    private void greyScaleImage() {
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
        equipedShieldImage.setColorFilter(filter);
    }

    private void colorImage() {
        equipedShieldImage.setColorFilter(null);
        equipedShieldImage.setImageAlpha(255);
    }



}
