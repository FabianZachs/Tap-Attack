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

    private boolean lostFocus = false;

    public ButtonOnTouchListener(Activity activity, View button, ButtonExecuteListener buttonOnTouchListener) {
        this.activity = activity;
        this.button = button;
        this.buttonExecuteListener = buttonOnTouchListener;
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
            //buttonAction();
            buttonExecuteListener.buttonAction();
            return true;

        }
        else if (motionEvent.getAction() == MotionEvent.ACTION_UP && lostFocus) {
            Log.d("buttontouch", "onTouch: E");
            lostFocus = false;
            return true;
        }

        return false;
    }



    public interface ButtonExecuteListener {
        void buttonAction();
    }



    Rect outRect = new Rect();
    int[] location = new int[2];

    private boolean isViewInBounds(int x, int y){
        button.getDrawingRect(outRect);
        button.getLocationOnScreen(location);
        outRect.offset(location[0], location[1]);
        return outRect.contains(x, y);
    }


    /*
    private void buttonAction() {
        FragmentTransaction transaction;
        switch (buttonIdentifier) {
            case "fragmentToMenu":
                //((MainMenuActivity)activity).setViewPager(1);

                android.support.v4.app.Fragment fragment =((MainMenuActivity)activity).getSupportFragmentManager().findFragmentByTag("store");
                if (fragment instanceof StoreFragment) {
                    Log.d("backbuttonclick", "buttonAction: ");
                    ((StoreFragment) fragment).backButtonClick();
                    Log.d("backbuttonclick", "buttonAction: ");
                }
                break;
            case "fragmentToSettings":
                ((MainMenuActivity)activity).setViewPager(2);
                break;
            case "fragmentToStore":
                //((MainMenuActivity)activity).setViewPager(3);
                // Create fragment and give it an argument specifying the article it should show
                StoreFragment storeFragment = new StoreFragment();

                transaction = ((MainMenuActivity)activity).getSupportFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack so the user can navigate back
                transaction.replace(R.id.main_fragment, storeFragment);
                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();
                break;
            case "randomUnlock":
                break;
            case "play":
                Intent intent = new Intent(activity, MainGameActivity.class);
                intent.putExtra("gamemode", "classic");
                activity.startActivityForResult(intent, 1);
                break;
            case "musicButton":
                ImageView buttonImg = (ImageView) button;
                if (prefs.getInt("music", 1) == 1) {
                    buttonImg.setImageResource(R.drawable.offbutton);
                    prefsEditor.putInt("music", 0);
                    prefsEditor.apply();
                }
                else {
                    buttonImg.setImageResource(R.drawable.onbutton);
                    prefsEditor.putInt("music", 1);
                    prefsEditor.apply();
                }
                break;
        }

    }
    */

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
