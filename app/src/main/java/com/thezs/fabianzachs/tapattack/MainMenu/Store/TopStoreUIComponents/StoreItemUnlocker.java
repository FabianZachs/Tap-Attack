package com.thezs.fabianzachs.tapattack.MainMenu.Store.TopStoreUIComponents;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.thezs.fabianzachs.tapattack.ButtonOnTouchListener;
import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Database.MyDBHandler;
import com.thezs.fabianzachs.tapattack.MainMenu.Store.StoreFragment;
import com.thezs.fabianzachs.tapattack.MainMenu.Store.StoreFragmentOLD;
import com.thezs.fabianzachs.tapattack.R;

/**
 * Created by fabianzachs on 15/08/18.
 */

public class StoreItemUnlocker {

    private Activity activity;
    private UnlockListener listener;
    private MyDBHandler myDBHandler;
    private TextView fraction;
    private TextView randomUnlockText;
    private RelativeLayout randomUnlockSection;
    private RelativeLayout purchaseUnlockSection;
    private TextView purchaseUnlockText;
    private StoreFragment storeFragment;
    // todo needs to handle purchase unlock button (^)
    // todo needs listener that store will implement to update points, gridview (for lockimage and selected), displayed item

    public interface UnlockListener {
        void randomUnlockClick();
        void purchaseUnlockClick();
    }

    public void setUnlockListener(UnlockListener listener) {
        this.listener = listener;
    }

    public StoreItemUnlocker(final StoreFragment storeFragment, final Activity activity, View view, MyDBHandler myDBHandler) {

        this.activity = activity;
        this.fraction = (TextView) view.findViewById(R.id.unlocked_fraction);
        this.randomUnlockText = view.findViewById(R.id.random_unlock_text);
        this.randomUnlockSection = view.findViewById(R.id.random_unlock_section);
        this.purchaseUnlockSection = view.findViewById(R.id.purchase_unlock_section);
        this.purchaseUnlockText = view.findViewById(R.id.purchase_unlock_text);
        this.myDBHandler = myDBHandler;
        this.storeFragment = storeFragment;
        updateUnlockedFraction();
        updateRandomUnlockView();
        updatePurchaseUnlockView();

        randomUnlockSection.setOnTouchListener(new ButtonOnTouchListener(activity, randomUnlockSection, new ButtonOnTouchListener.ButtonExecuteListener() {
            @Override
            public void buttonAction() {



                if (randomUnlockActive()) {
                    listener.randomUnlockClick();
                }
                else //if (!enoughPointsForRandomUnlock() || allUnlocked()) {
                    YoYo.with(Techniques.Shake).duration(800).repeat(0).playOn(randomUnlockSection);
                //}
            }
        }));

        purchaseUnlockSection.setOnTouchListener(new ButtonOnTouchListener(activity, purchaseUnlockSection, new ButtonOnTouchListener.ButtonExecuteListener() {
            @Override
            public void buttonAction() {
                if (purchaseUnlockActive()) {
                    listener.purchaseUnlockClick();
                }
                else
                    YoYo.with(Techniques.Shake).duration(800).repeat(0).playOn(purchaseUnlockSection);

            }
        }));
    }


    public void updateUnlockedFraction() {
        String section = storeFragment.getCurrentlyDisplayedItemFragmentTAG();
        int numberUnlocked = myDBHandler.getNumberOfItemsFromCategory(section) - myDBHandler.getNumberOfLockedItems(section);
        int numberItems = myDBHandler.getNumberOfItemsFromCategory(section);
        fraction.setText(activity.getResources().getString(R.string.itemsUnlockedFraction, numberUnlocked,numberItems));
    }

    private boolean randomUnlockActive() {
        //return (randomUnlockSection.getVisibility()==View.VISIBLE && enoughPointsForRandomUnlock());
        return (randomUnlockTextVisible() && enoughPointsForRandomUnlock());
    }

    private boolean purchaseUnlockActive() {
        //return (purchaseUnlockSection.getVisibility()==View.VISIBLE && enoughPointsForPurchaseUnlock());
        Log.d("unlockeditem", "purchaseUnlockActive: "+storeFragment.currentSelectedItemUnlocked());
        return (purchaseUnlockTextVisible() && enoughPointsForPurchaseUnlock() && storeFragment.currentSelectedItemUnlocked()!=1);
    }


    public void updateRandomUnlockView() {
        randomUnlockText.setText(activity.getResources().getString(R.string.randomUnlockText, storeFragment.getCurrentRandomUnlockPrice()));

        if (!enoughPointsForRandomUnlock())
            randomUnlockText.setTextColor(ContextCompat.getColor(activity, R.color.soundoff));
        else randomUnlockText.setTextColor(0xffffffff);

        if (randomUnlockTextVisible()) {
            Log.d("shouldbegone", "visible");
            randomUnlockSection.setVisibility(View.VISIBLE);

        }
        else {

            Log.d("shouldbegone", "invisible");
            randomUnlockSection.setVisibility(View.INVISIBLE);
        }
    }

    public void updatePurchaseUnlockView() {
        purchaseUnlockText.setText(activity.getResources().getString(R.string.purchaseUnlockText, storeFragment.getCurrentPurchaseUnlockPrice()));

        if (!enoughPointsForPurchaseUnlock())
            purchaseUnlockText.setTextColor(ContextCompat.getColor(activity, R.color.soundoff));
        else purchaseUnlockText.setTextColor(0xffffffff);

        if (purchaseUnlockTextVisible()) {
            purchaseUnlockSection.setVisibility(View.VISIBLE);

        }
        else {

            Log.d("shouldbegone", "invisible");
            purchaseUnlockSection.setVisibility(View.INVISIBLE);
        }
    }

    private boolean enoughPointsForRandomUnlock() {
        return storeFragment.getCurrentRandomUnlockPrice() <= storeFragment.getCurrentPoints();
    }

    private boolean enoughPointsForPurchaseUnlock() {
        return storeFragment.getCurrentPurchaseUnlockPrice() <= storeFragment.getCurrentPoints();
    }

    private boolean randomUnlockTextVisible() {
        return !storeFragment.getCurrentlyDisplayedItemFragmentTAG().equals(Constants.GAME_MODE_TAG)
                &&  !allUnlocked();
    }

    private boolean purchaseUnlockTextVisible() {
        return !storeFragment.getCurrentlyDisplayedItemFragmentTAG().equals(Constants.GAME_MODE_TAG)
                &&  !allUnlocked();
    }

    private boolean allUnlocked() {
        return myDBHandler.getListOfLockedItems(storeFragment.getCurrentlyDisplayedItemFragmentTAG()).length == 0;
    }


}
