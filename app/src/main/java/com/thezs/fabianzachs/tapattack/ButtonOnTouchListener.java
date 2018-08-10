package com.thezs.fabianzachs.tapattack;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.thezs.fabianzachs.tapattack.MainMenu.MainMenuActivity;

/**
 * Created by fabianzachs on 11/08/18.
 */

public class ButtonOnTouchListener implements View.OnTouchListener {

    private String buttonIdentifier;
    private View button;
    private Activity activity;

    private boolean lostFocus = false;

    public ButtonOnTouchListener(Activity activity, View button, String buttonIdentifier) {
        this.activity = activity;
        this.button = button;
        this.buttonIdentifier = buttonIdentifier;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            final Animation myAnim = AnimationUtils.loadAnimation(activity, R.anim.scale_down);
            button.startAnimation(myAnim);
            myAnim.setFillAfter(true);
            return true;
        }

        else if (touchAwayFromView(motionEvent, button) && !lostFocus) {
            final Animation myAnim = AnimationUtils.loadAnimation(activity, R.anim.scale_up);
            button.startAnimation(myAnim);
            myAnim.setFillAfter(true);
            lostFocus = true;
            return true;

        }
        else if (!touchAwayFromView(motionEvent, button) && lostFocus) {
            final Animation myAnim = AnimationUtils.loadAnimation(activity, R.anim.scale_down);
            button.startAnimation(myAnim);
            myAnim.setFillAfter(true);
            lostFocus = false;
            return true;

        }
        else if (motionEvent.getAction() == MotionEvent.ACTION_UP && !lostFocus) {
            final Animation myAnim = AnimationUtils.loadAnimation(activity, R.anim.scale_up);
            button.startAnimation(myAnim);
            myAnim.setFillAfter(true);
            buttonAction();
            return true;

        }
        else if (motionEvent.getAction() == MotionEvent.ACTION_UP && lostFocus) {
            lostFocus = false;
            return true;
        }

        return false;
    }


    private boolean touchAwayFromView(MotionEvent motionEvent, View backView) {
        return (motionEvent.getX() < backView.getLeft() || backView.getRight() <motionEvent.getX()
                || motionEvent.getY() < backView.getTop() || backView.getBottom() < motionEvent.getY());
    }

    private void buttonAction() {
        switch (buttonIdentifier) {
            case "storeToMenu":
                ((MainMenuActivity)activity).setViewPager(1);
                break;
            case "randomUnlock":
                break;
        }

    }
}
