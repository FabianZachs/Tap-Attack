package com.thezs.fabianzachs.tapattack.GameFragment;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.R;

public class GameUI {

    private View view;

    GameUI(View view) {
        this.view = view;
        setupUI();
    }

    private void setupUI() {

        final ImageView warningColorChangeButtonLeft = view.findViewById(R.id.warning_color_change_button_left);
        final RelativeLayout.LayoutParams colorChangeParamsLeft = new RelativeLayout.LayoutParams
                (Constants.SCREEN_WIDTH / 20, 3 * (Constants.SCREEN_HEIGHT / 4));
        colorChangeParamsLeft.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        colorChangeParamsLeft.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        colorChangeParamsLeft.bottomMargin = 20;
        warningColorChangeButtonLeft.setLayoutParams(colorChangeParamsLeft);


        final ImageView warningColorChangeButtonRight = view.findViewById(R.id.warning_color_change_button_right);
        RelativeLayout.LayoutParams colorChangeParamsRight = new RelativeLayout.LayoutParams
                (Constants.SCREEN_WIDTH / 20, 3 * (Constants.SCREEN_HEIGHT / 4));
        colorChangeParamsRight.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        colorChangeParamsRight.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        colorChangeParamsRight.bottomMargin = 20;
        warningColorChangeButtonRight.setLayoutParams(colorChangeParamsRight);

        final RelativeLayout parentLayout = view.findViewById(R.id.main_game_view);
        ViewTreeObserver vto = parentLayout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                warningColorChangeButtonLeft.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                Constants.WARNING_COLOR_CLICK_AREA_LEFT = new Rect(warningColorChangeButtonLeft.getLeft(),
                        warningColorChangeButtonLeft.getTop(), warningColorChangeButtonLeft.getRight(),
                        warningColorChangeButtonLeft.getBottom());

                Constants.WARNING_COLOR_CLICK_AREA_RIGHT = new Rect(warningColorChangeButtonRight.getLeft(),
                        warningColorChangeButtonRight.getTop(), warningColorChangeButtonRight.getRight(),
                        warningColorChangeButtonRight.getBottom());

            }
        });


        ImageView bottom = view.findViewById(R.id.bottom_image);

        RelativeLayout.LayoutParams bottomParams = new RelativeLayout.LayoutParams
                (Constants.SCREEN_WIDTH, 20);
        bottomParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        bottom.setLayoutParams(bottomParams);


        ImageView warningComponent = view.findViewById(R.id.warning_component);
        RelativeLayout.LayoutParams warningComponentParameters = new RelativeLayout.LayoutParams(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_WIDTH / 15);
        warningComponentParameters.addRule(RelativeLayout.CENTER_HORIZONTAL);
        warningComponentParameters.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        warningComponentParameters.topMargin = Constants.SCREEN_HEIGHT / 40;
        warningComponent.setLayoutParams(warningComponentParameters);
    }
}
