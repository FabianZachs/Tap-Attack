package com.thezs.fabianzachs.tapattack;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by fabianzachs on 11/08/18.
 */

public class ButtonOnTouchListener implements View.OnTouchListener {

    private View button;
    private Activity activity;
    ButtonExecuteListener buttonExecuteListener;
    private Animation animation1;
    private Animation animation2;

    private boolean lostFocus = false;

    public ButtonOnTouchListener(Activity activity, View button, ButtonExecuteListener buttonOnTouchListener) {
        this.activity = activity;
        this.button = button;
        this.buttonExecuteListener = buttonOnTouchListener;
        animation1 = AnimationUtils.loadAnimation(activity, R.anim.scale_down);
        animation1.setFillAfter(true);
        animation2 = AnimationUtils.loadAnimation(activity, R.anim.scale_up);
        animation2.setFillAfter(true);
    }

    public ButtonOnTouchListener(Activity activity, View button, Animation animation1, Animation animation2, ButtonExecuteListener buttonOnTouchListener) {
        this.activity = activity;
        this.button = button;
        this.buttonExecuteListener = buttonOnTouchListener;
        this.animation1 = animation1;
        animation1.setFillAfter(true);
        this.animation2 = animation2;
        animation2.setFillAfter(true);

    }

    public interface ButtonExecuteListener {
        void buttonAction();
    }



    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            //final Animation myAnim = AnimationUtils.loadAnimation(activity, R.anim.scale_down);
            //button.startAnimation(myAnim);
            //myAnim.setFillAfter(true);
            button.startAnimation(animation1);
            return true;
        }

        else if (/*touchAwayFromView(motionEvent, button)*/ !isViewInBounds((int)motionEvent.getRawX(), (int) motionEvent.getRawY())&& !lostFocus) {
            //final Animation myAnim = AnimationUtils.loadAnimation(activity, R.anim.scale_up);
            //button.startAnimation(myAnim);
            //myAnim.setFillAfter(true);
            button.startAnimation(animation2);
            lostFocus = true;
            return true;

        }
        else if (/*!touchAwayFromView(motionEvent, button)*/ isViewInBounds((int)motionEvent.getRawX(), (int) motionEvent.getRawY())&& lostFocus) {
            //final Animation myAnim = AnimationUtils.loadAnimation(activity, R.anim.scale_down);
            //button.startAnimation(myAnim);
            //myAnim.setFillAfter(true);
            button.startAnimation(animation1);
            lostFocus = false;
            return true;

        }
        else if (motionEvent.getAction() == MotionEvent.ACTION_UP && !lostFocus) {
            //final Animation myAnim = AnimationUtils.loadAnimation(activity, R.anim.scale_up);
            //button.startAnimation(myAnim);
            //myAnim.setFillAfter(true);
            button.startAnimation(animation2);
            buttonExecuteListener.buttonAction();
            return true;

        }
        else if (motionEvent.getAction() == MotionEvent.ACTION_UP && lostFocus) {
            lostFocus = false;
            return true;
        }

        return false;
    }






    Rect outRect = new Rect();
    int[] location = new int[2];

    private boolean isViewInBounds(int x, int y){
        button.getDrawingRect(outRect);
        button.getLocationOnScreen(location);
        outRect.offset(location[0], location[1]);
        return outRect.contains(x, y);
    }

}
