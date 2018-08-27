package com.thezs.fabianzachs.tapattack.MainMenu.Menu;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.R;

class MenuAnimation {

    private static final int TITLE_TEXT_LEFT_MARGIN = 10;
    private static final int POINTS_AND_SHIELD_TOP_MARGIN= 7;

    private View menuView;

    MenuAnimation(View view) {
        this.menuView = view;
        startMenuAnimation();

    }

    private void startMenuAnimation() {
        // todo maybe use Constants.GAME_VIEW_HEIGHT GAME_VIEW_WIDTH
        final TextView titleText1 = menuView.findViewById(R.id.title_text1);
        final TextView titleText2 = menuView.findViewById(R.id.title_text2);
        final LinearLayout pointsAndShieldSection = menuView.findViewById(R.id.points_and_shields_section);
        final TextView unlockPercentText = menuView.findViewById(R.id.percent_unlocked_text);
        final TextView storeText = menuView.findViewById(R.id.store_text);
        final TextView settingsText = menuView.findViewById(R.id.settings_text);
        final ImageView presentImage = menuView.findViewById(R.id.timed_points);
        final LinearLayout playGameSection = menuView.findViewById(R.id.main_play_game_section);



        LinearLayout parentLayout = menuView.findViewById(R.id.parent_layout);
        ViewTreeObserver vto = parentLayout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                titleText1.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                titleText2.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                pointsAndShieldSection.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                unlockPercentText.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                storeText.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                settingsText.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                presentImage.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                playGameSection.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                titleText1.setX(-titleText1.getWidth());
                titleText2.setX(Constants.SCREEN_WIDTH);
                pointsAndShieldSection.setY(-pointsAndShieldSection.getHeight());
                unlockPercentText.setX(-unlockPercentText.getWidth());
                storeText.setX(-storeText.getWidth());
                settingsText.setX(Constants.SCREEN_WIDTH);
                presentImage.setY(Constants.SCREEN_HEIGHT);
                playGameSection.setAlpha(0);


                ObjectAnimator titleText1Anim = ObjectAnimator.ofFloat(titleText1,
                        View.TRANSLATION_X, 0);
                ObjectAnimator titleText2Anim = ObjectAnimator.ofFloat(titleText2,
                        View.TRANSLATION_X, 0);
                ObjectAnimator pointsAndShieldAnim = ObjectAnimator.ofFloat(pointsAndShieldSection,
                        View.TRANSLATION_Y, 0);
                ObjectAnimator percentUnlockedAnim = ObjectAnimator.ofFloat(unlockPercentText,
                        View.TRANSLATION_X, 0);
                ObjectAnimator storeTextAnim = ObjectAnimator.ofFloat(storeText,
                        View.TRANSLATION_X,  0);
                ObjectAnimator settingsTextAnim = ObjectAnimator.ofFloat(settingsText,
                        View.TRANSLATION_X, 0);
                ObjectAnimator presentImageAnim = ObjectAnimator.ofFloat(presentImage,
                        View.TRANSLATION_Y, 0);
                ObjectAnimator playGameSectionAnim = ObjectAnimator.ofFloat(playGameSection,
                        View.ALPHA, 255);

                titleText1Anim.setDuration(600);
                titleText2Anim.setDuration(600);
                pointsAndShieldAnim.setDuration(600);
                percentUnlockedAnim.setDuration(600);
                storeTextAnim.setDuration(600);
                settingsTextAnim.setDuration(600);
                presentImageAnim.setDuration(600);
                playGameSectionAnim.setDuration(30000);


                AnimatorSet setAnimation = new AnimatorSet();
                setAnimation.play(titleText1Anim);
                setAnimation.play(titleText2Anim).after(titleText1Anim);
                setAnimation.play(pointsAndShieldAnim).after(titleText2Anim);
                setAnimation.play(percentUnlockedAnim).after(titleText2Anim);
                setAnimation.play(storeTextAnim).after(titleText2Anim);
                setAnimation.play(settingsTextAnim).after(titleText2Anim);
                setAnimation.play(presentImageAnim).after(titleText2Anim);
                setAnimation.play(playGameSectionAnim).after(titleText2Anim);
                setAnimation.start();


            }
        });

    }

    public interface OnAnimationCompleteListener {
        void animationComplete();

    }

    public void closeMenuStartGameAnimation(final OnAnimationCompleteListener listener) {
        final RelativeLayout topSection = menuView.findViewById(R.id.top_section);
        final LinearLayout middleSection = menuView.findViewById(R.id.main_play_game_section);
        final RelativeLayout bottomSection = menuView.findViewById(R.id.bottom_fragment_changers);

        ObjectAnimator topAnimation = ObjectAnimator.ofFloat(topSection,
                View.TRANSLATION_Y, -topSection.getHeight());
        ObjectAnimator middleAnimation = ObjectAnimator.ofFloat(middleSection,
                View.TRANSLATION_Y, Constants.SCREEN_HEIGHT);
        ObjectAnimator bottomAnimation = ObjectAnimator.ofFloat(bottomSection,
                View.TRANSLATION_Y, Constants.SCREEN_HEIGHT);
        topAnimation.setDuration(600);
        middleAnimation.setDuration(600);
        bottomAnimation.setDuration(600);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                //todo add local listener on calling this  to tell animation over so displayGameFragment
                listener.animationComplete();
                // todo then put views all back
            }
        });
        animatorSet.play(topAnimation).with(middleAnimation).with(bottomAnimation);
        animatorSet.start();




        /*setAnimation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                // then we want to start game. when game starts similar stuff to this happens
            }
        });
*/
    }
}
