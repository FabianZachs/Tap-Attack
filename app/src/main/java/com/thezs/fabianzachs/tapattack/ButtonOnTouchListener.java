package com.thezs.fabianzachs.tapattack;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
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
        //Log.d("buttonarea", "ButtonOnTouchListener: " + button.getLeft() + " " + button.getRight() );
        //Log.d("buttonarea", "ButtonOnTouchListener: " + button.getY() + " " + button.getBottom() );
        //Log.d("buttonarea", "ButtonOnTouchListener: " + location[0]);
        //Log.d("buttonarea", "ButtonOnTouchListener: " + x + ", " + (x+button.getWidth()) + "  " + y + ", " + (y+button.getHeight()));
        //Log.d("screenwidth", "ButtonOnTouchListener: " + Constants.SCREEN_WIDTH);
    }



    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            Log.d("buttontouch", "onTouch: A");
            final Animation myAnim = AnimationUtils.loadAnimation(activity, R.anim.scale_down);
            button.startAnimation(myAnim);
            myAnim.setFillAfter(true);
            return true;
        }

        else if (/*touchAwayFromView(motionEvent, button)*/ !isViewInBounds((int)motionEvent.getRawX(), (int) motionEvent.getRawY())&& !lostFocus) {
            Log.d("buttontouch", "onTouch: B");
            final Animation myAnim = AnimationUtils.loadAnimation(activity, R.anim.scale_up);
            button.startAnimation(myAnim);
            myAnim.setFillAfter(true);
            lostFocus = true;
            return true;

        }
        else if (/*!touchAwayFromView(motionEvent, button)*/ isViewInBounds((int)motionEvent.getRawX(), (int) motionEvent.getRawY())&& lostFocus) {
            Log.d("buttontouch", "onTouch: C");
            final Animation myAnim = AnimationUtils.loadAnimation(activity, R.anim.scale_down);
            button.startAnimation(myAnim);
            myAnim.setFillAfter(true);
            lostFocus = false;
            return true;

        }
        else if (motionEvent.getAction() == MotionEvent.ACTION_UP && !lostFocus) {
            Log.d("buttontouch", "onTouch: D");
            final Animation myAnim = AnimationUtils.loadAnimation(activity, R.anim.scale_up);
            button.startAnimation(myAnim);
            myAnim.setFillAfter(true);
            buttonAction();
            return true;

        }
        else if (motionEvent.getAction() == MotionEvent.ACTION_UP && lostFocus) {
            Log.d("buttontouch", "onTouch: E");
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


    private void buttonAction() {
        switch (buttonIdentifier) {
            case "fragmentToMenu":
                ((MainMenuActivity)activity).setViewPager(1);
                break;
            case "fragmentToSettings":
                ((MainMenuActivity)activity).setViewPager(3);
            case "randomUnlock":
                break;
        }

    }
    /*
    private boolean touchAwayFromView(MotionEvent motionEvent, View backView) {
        Log.d("touchaway", "touchAwayFromView: " + motionEvent.getRawX() + " " + motionEvent.getRawY());
        Log.d("toucharea", "touchAwayFromView: " + motionEvent.getRawX() + ", "+motionEvent.getRawY());
        //return (motionEvent.getRawX() < backView.getLeft() || backView.getRight() <motionEvent.getRawX()
        //        || motionEvent.getRawY() < backView.getTop() || backView.getBottom() < motionEvent.getRawY());
        return (motionEvent.getRawX() < x || (x+button.getWidth()) <motionEvent.getRawX()
                || motionEvent.getRawY() < y || (y+button.getHeight()) < motionEvent.getRawY());
    }
    */

}
