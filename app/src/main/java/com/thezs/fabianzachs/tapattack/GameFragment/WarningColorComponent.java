package com.thezs.fabianzachs.tapattack.GameFragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.thezs.fabianzachs.tapattack.ButtonOnTouchListener;
import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.R;

public class WarningColorComponent {

    private Integer[] colors;
    private int currentWarningColorIndex;
    private GradientDrawable warningDrawable;

    // todo needs Integer[] colors on construct
    // todo view to get warning color, left button, right buttton
    public WarningColorComponent(View view, Integer[] colors) {
        this.colors = colors;
        currentWarningColorIndex = 0;

        ImageView warningImage = view.findViewById(R.id.warning_component);
        warningDrawable = ((GradientDrawable) ((LayerDrawable) warningImage.getDrawable()).getDrawable(1));
        setupLeftWarningColorChanger(view);
        setupRightWarningColorChanger(view);

    }

    public Integer getCurrentWarningColor() {
        return null;
    }

    private void setNextWarningColor() {

    }

    private void setPreviousWarningColor() {

    }

    private void newWarningColorNotification() {
        // todo tell mediator
    }


    @SuppressLint("ClickableViewAccessibility")
    private void setupLeftWarningColorChanger(View view) {

        ImageView leftButton = view.findViewById(R.id.warning_color_change_button_left);
        Animation animation1 = AnimationUtils.loadAnimation(Constants.CURRENT_CONTEXT, R.anim.leftcomponent_x_slide_left);
        Animation animation2 = AnimationUtils.loadAnimation(Constants.CURRENT_CONTEXT, R.anim.leftcomponent_x_slide_right);
        leftButton.setOnTouchListener(new ButtonOnTouchListener((Activity) Constants.CURRENT_CONTEXT, leftButton,
                animation1, animation2, new ButtonOnTouchListener.ButtonExecuteListener() {
            @Override
            public void buttonAction() {

            }
        }));

    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupRightWarningColorChanger(View view) {
        ImageView rightButton = view.findViewById(R.id.warning_color_change_button_right);
        Animation animation1 = AnimationUtils.loadAnimation(Constants.CURRENT_CONTEXT, R.anim.rightcomponent_x_slide_right);
        Animation animation2 = AnimationUtils.loadAnimation(Constants.CURRENT_CONTEXT, R.anim.rightcomponent_x_slide_left);
        rightButton.setOnTouchListener(new ButtonOnTouchListener((Activity) Constants.CURRENT_CONTEXT, rightButton,
                animation1, animation2, new ButtonOnTouchListener.ButtonExecuteListener() {
            @Override
            public void buttonAction() {

            }
        }));
    }
}
