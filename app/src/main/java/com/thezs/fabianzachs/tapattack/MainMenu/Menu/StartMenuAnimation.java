package com.thezs.fabianzachs.tapattack.MainMenu.Menu;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.R;

public class StartMenuAnimation {

    private static final int TITLE_TEXT_LEFT_MARGIN = 10;
    private static final int POINTS_AND_SHIELD_TOP_MARGIN= 7;

    public StartMenuAnimation(View view) {
        /*
        final TextView textView = view.findViewById(R.id.title_text1);

        final ViewTreeObserver layout = textView.getViewTreeObserver();
        layout.addOnGlobalLayoutListener (new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                textView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int width  = textView.getMeasuredWidth();
                int[] location = new int[2];
                textView.getLocationOnScreen(location);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)
                        textView.getLayoutParams();
                params.setMargins(-1 * width, 0, 0, 0);
                textView.setLayoutParams(params);

                Log.d("textviewwidth", "onGlobalLayout: " + textView.getWidth());
                ObjectAnimator animX = ObjectAnimator.ofFloat(textView,
                        View.TRANSLATION_X, -1* textView.getWidth(), textView.getWidth());
                animX.setDuration(1000);
                animX.start();

            }
        });
        */
        final TextView titleText1 = view.findViewById(R.id.title_text1);
        final TextView titleText2 = view.findViewById(R.id.title_text2);
        final LinearLayout pointsAndShieldSection = view.findViewById(R.id.points_and_shields_section);
        final TextView unlockPercentText = view.findViewById(R.id.percent_unlocked_text);
        final TextView storeText = view.findViewById(R.id.store_text);
        final TextView settingsText = view.findViewById(R.id.settings_text);
        final ImageView presentImage = view.findViewById(R.id.timed_points);
        final LinearLayout playGameSection = view.findViewById(R.id.main_play_game_section);


        titleText1.setX(-titleText1.getWidth());
        titleText2.setX(Constants.SCREEN_WIDTH);
        pointsAndShieldSection.setY(-pointsAndShieldSection.getHeight());
        unlockPercentText.setX(-unlockPercentText.getWidth());
        storeText.setX(-storeText.getWidth());
        settingsText.setX(Constants.SCREEN_WIDTH);
        presentImage.setY(Constants.SCREEN_HEIGHT);
        playGameSection.setAlpha(0  );


        ObjectAnimator titleText1Anim = ObjectAnimator.ofFloat(titleText1,
                View.TRANSLATION_X, -1* titleText1.getWidth(), TITLE_TEXT_LEFT_MARGIN);
        ObjectAnimator titleText2Anim = ObjectAnimator.ofFloat(titleText2,
                View.TRANSLATION_X, Constants.SCREEN_WIDTH, TITLE_TEXT_LEFT_MARGIN);
        ObjectAnimator pointsAndShieldAnim = ObjectAnimator.ofFloat(pointsAndShieldSection,
                View.TRANSLATION_Y, -pointsAndShieldSection.getHeight(), POINTS_AND_SHIELD_TOP_MARGIN);
        ObjectAnimator percentUnlockedAnim = ObjectAnimator.ofFloat(unlockPercentText,
                View.TRANSLATION_X, -unlockPercentText.getWidth(), TITLE_TEXT_LEFT_MARGIN);
        ObjectAnimator storeTextAnim = ObjectAnimator.ofFloat(storeText,
                View.TRANSLATION_X, -storeText.getWidth(), TITLE_TEXT_LEFT_MARGIN);
        ObjectAnimator settingsTextAnim = ObjectAnimator.ofFloat(settingsText,
                View.TRANSLATION_X, Constants.SCREEN_WIDTH, TITLE_TEXT_LEFT_MARGIN/*Constants.SCREEN_WIDTH - (TITLE_TEXT_LEFT_MARGIN + settingsText.getWidth())*/);
        ObjectAnimator presentImageAnim = ObjectAnimator.ofFloat(presentImage,
                View.TRANSLATION_Y, Constants.SCREEN_HEIGHT, -10/*Constants.SCREEN_WIDTH - (TITLE_TEXT_LEFT_MARGIN + settingsText.getWidth())*/);
        ObjectAnimator playGameSectionAnim = ObjectAnimator.ofFloat(playGameSection,
                View.ALPHA, 0, 255/*Constants.SCREEN_WIDTH - (TITLE_TEXT_LEFT_MARGIN + settingsText.getWidth())*/);

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

        LinearLayout parentLayout = view.findViewById(R.id.parent_layout);
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
                        View.TRANSLATION_X, -1* titleText1.getWidth(), TITLE_TEXT_LEFT_MARGIN);
                ObjectAnimator titleText2Anim = ObjectAnimator.ofFloat(titleText2,
                        View.TRANSLATION_X, Constants.SCREEN_WIDTH, TITLE_TEXT_LEFT_MARGIN);
                ObjectAnimator pointsAndShieldAnim = ObjectAnimator.ofFloat(pointsAndShieldSection,
                        View.TRANSLATION_Y, -pointsAndShieldSection.getHeight(), POINTS_AND_SHIELD_TOP_MARGIN);
                ObjectAnimator percentUnlockedAnim = ObjectAnimator.ofFloat(unlockPercentText,
                        View.TRANSLATION_X, -unlockPercentText.getWidth(), TITLE_TEXT_LEFT_MARGIN);
                ObjectAnimator storeTextAnim = ObjectAnimator.ofFloat(storeText,
                        View.TRANSLATION_X, -storeText.getWidth(), TITLE_TEXT_LEFT_MARGIN);
                ObjectAnimator settingsTextAnim = ObjectAnimator.ofFloat(settingsText,
                        View.TRANSLATION_X, Constants.SCREEN_WIDTH, TITLE_TEXT_LEFT_MARGIN/*Constants.SCREEN_WIDTH - (TITLE_TEXT_LEFT_MARGIN + settingsText.getWidth())*/);
                ObjectAnimator presentImageAnim = ObjectAnimator.ofFloat(presentImage,
                        View.TRANSLATION_Y, Constants.SCREEN_HEIGHT, -10/*Constants.SCREEN_WIDTH - (TITLE_TEXT_LEFT_MARGIN + settingsText.getWidth())*/);
                ObjectAnimator playGameSectionAnim = ObjectAnimator.ofFloat(playGameSection,
                        View.ALPHA, 0, 255/*Constants.SCREEN_WIDTH - (TITLE_TEXT_LEFT_MARGIN + settingsText.getWidth())*/);

                titleText1Anim.setDuration(300);
                titleText2Anim.setDuration(300);
                pointsAndShieldAnim.setDuration(300);
                percentUnlockedAnim.setDuration(300);
                storeTextAnim.setDuration(300);
                settingsTextAnim.setDuration(300);
                presentImageAnim.setDuration(300);
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
}
