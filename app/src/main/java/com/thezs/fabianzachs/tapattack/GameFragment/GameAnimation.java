package com.thezs.fabianzachs.tapattack.GameFragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.R;

public class GameAnimation {

    private View gameView;

    public GameAnimation(View view) {
        this.gameView = view;
    }

    public void startGameAnimation() {

        final ImageView leftWarningBar = gameView.findViewById(R.id.warning_color_change_button_left);
        final ImageView rightWarningBar = gameView.findViewById(R.id.warning_color_change_button_right);
        final ImageView warningBar = gameView.findViewById(R.id.warning_component);
        final ImageView bottomRect = gameView.findViewById(R.id.bottom_image);


        /*
        leftWarningBar.setX(-leftWarningBar.getWidth());
        rightWarningBar.setX(Constants.SCREEN_WIDTH);
        warningBar.setY(warningBar.getHeight());

        ObjectAnimator leftWarningAnim = ObjectAnimator.ofFloat(leftWarningBar,
                View.TRANSLATION_X, 0);
        ObjectAnimator rightWarningAnim = ObjectAnimator.ofFloat(rightWarningBar,
                View.TRANSLATION_X, 0);
        ObjectAnimator warningComponentAnim = ObjectAnimator.ofFloat(warningBar,
                View.TRANSLATION_Y, 0);

        leftWarningAnim.setDuration(2600);
        rightWarningAnim.setDuration(2600);
        warningComponentAnim.setDuration(2600);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(leftWarningAnim).with(rightWarningAnim).with(warningComponentAnim);
        animatorSet.start();
*/

        RelativeLayout parentLayout = gameView.findViewById(R.id.main_game_view);
        ViewTreeObserver vto = parentLayout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                leftWarningBar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                rightWarningBar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                warningBar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                bottomRect.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                leftWarningBar.setX(-leftWarningBar.getWidth());
                rightWarningBar.setX(Constants.SCREEN_WIDTH);
                warningBar.setY(-warningBar.getHeight());
                bottomRect.setY(Constants.SCREEN_HEIGHT);

                ObjectAnimator leftWarningAnim = ObjectAnimator.ofFloat(leftWarningBar,
                        View.TRANSLATION_X, 0);
                ObjectAnimator rightWarningAnim = ObjectAnimator.ofFloat(rightWarningBar,
                        View.TRANSLATION_X, 0);
                ObjectAnimator warningComponentAnim = ObjectAnimator.ofFloat(warningBar,
                        View.TRANSLATION_Y, 0);
                ObjectAnimator bottomREctAnim = ObjectAnimator.ofFloat(bottomRect,
                        View.TRANSLATION_Y, 0);

                leftWarningAnim.setDuration(400);
                rightWarningAnim.setDuration(400);
                warningComponentAnim.setDuration(400);
                bottomREctAnim.setDuration(400);

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(leftWarningAnim).with(rightWarningAnim).with(warningComponentAnim).with(bottomREctAnim);
                animatorSet.start();


            }
        });



    }
}
