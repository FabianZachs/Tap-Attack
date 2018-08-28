package com.thezs.fabianzachs.tapattack.GameFragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.R;
import com.thezs.fabianzachs.tapattack.helper;

public class GameUI {

    private View view;

    public GameUI(Activity activity, View view, boolean warningUI) {
        this.view = view;
        if (warningUI)
            setupWarningColorUI(activity);
        else setupNonWarningUI(activity);

        setupBottomBar(activity);
    }

    private void setupNonWarningUI(Activity activity) {
        final ImageView warningColorChangeButtonLeft = view.findViewById(R.id.warning_color_change_button_left);
        warningColorChangeButtonLeft.setVisibility(View.GONE);
        final ImageView warningColorChangeButtonRight = view.findViewById(R.id.warning_color_change_button_right);
        warningColorChangeButtonRight.setVisibility(View.GONE);
        ImageView warningComponent = view.findViewById(R.id.warning_component);
        warningComponent.setVisibility(View.GONE);


    }

    private void setupWarningColorUI(Activity activity) {
        SharedPreferences prefs = activity.getSharedPreferences("playerInfo", Context.MODE_PRIVATE);
        int[] UIComponentColors = getCurrentWarningColorHolderColors(prefs.getString(Constants.BACKGROUND_TAG, Constants.BACKGROUNDS[0]));

        final ImageView warningColorChangeButtonLeft = view.findViewById(R.id.warning_color_change_button_left);
        final RelativeLayout.LayoutParams colorChangeParamsLeft = new RelativeLayout.LayoutParams
                (Constants.SCREEN_WIDTH / 15, 3 * (Constants.SCREEN_HEIGHT / 4));
        colorChangeParamsLeft.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        colorChangeParamsLeft.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        colorChangeParamsLeft.bottomMargin = 20;
        warningColorChangeButtonLeft.setLayoutParams(colorChangeParamsLeft);
        ((GradientDrawable) warningColorChangeButtonLeft.getDrawable()).setColors(UIComponentColors);
        Log.d("viewcrap", "onGlobalLayout: " + warningColorChangeButtonLeft.getLeft() + " " + warningColorChangeButtonLeft.getRight());


        final ImageView warningColorChangeButtonRight = view.findViewById(R.id.warning_color_change_button_right);
        RelativeLayout.LayoutParams colorChangeParamsRight = new RelativeLayout.LayoutParams
                (Constants.SCREEN_WIDTH / 15, 3 * (Constants.SCREEN_HEIGHT / 4));
        colorChangeParamsRight.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        colorChangeParamsRight.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        colorChangeParamsRight.bottomMargin = 20;
        warningColorChangeButtonRight.setLayoutParams(colorChangeParamsRight);
        ((GradientDrawable) warningColorChangeButtonRight.getDrawable()).setColors(UIComponentColors);



        ImageView warningComponent = view.findViewById(R.id.warning_component);
        RelativeLayout.LayoutParams warningComponentParameters = new RelativeLayout.LayoutParams(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_WIDTH / 15);
        warningComponentParameters.addRule(RelativeLayout.CENTER_HORIZONTAL);
        warningComponentParameters.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        warningComponentParameters.topMargin = Constants.SCREEN_HEIGHT / 60;
        warningComponent.setLayoutParams(warningComponentParameters);
        ((GradientDrawable) ((LayerDrawable) warningComponent.getDrawable()).getDrawable(0)).setColors(UIComponentColors);
    }

    private void setupBottomBar(Activity activity) {
        final ImageView bottom = view.findViewById(R.id.bottom_image);
        RelativeLayout.LayoutParams bottomParams = new RelativeLayout.LayoutParams
                (Constants.SCREEN_WIDTH, 20);
        bottomParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        bottom.setLayoutParams(bottomParams);

        SharedPreferences prefs = activity.getSharedPreferences("playerInfo", Context.MODE_PRIVATE);
        int[] UIComponentColors = getCurrentWarningColorHolderColors(prefs.getString(Constants.BACKGROUND_TAG, Constants.BACKGROUNDS[0]));
        if (prefs.getBoolean("shieldEnabled", true)) {
            ((GradientDrawable)bottom.getDrawable()).setColors(new int[] {UIComponentColors[0], activity.getResources().getColor(R.color.gameshieldcolor), UIComponentColors[0]});
        }
        else
            ((GradientDrawable)bottom.getDrawable()).setColors(new int[] {UIComponentColors[0], activity.getResources().getColor(R.color.gamenonshieldcolor), UIComponentColors[0]});



    }

    private int[] getCurrentWarningColorHolderColors(String currentBackgroundName) {
        int index = helper.getIndexOf(Constants.BACKGROUNDS, currentBackgroundName);
        return new int[] {Constants.BACKGROUND_WARNINGCOLOR_1[index], Constants.BACKGROUND_WARNINGCOLOR_2[index]};
    }
}
