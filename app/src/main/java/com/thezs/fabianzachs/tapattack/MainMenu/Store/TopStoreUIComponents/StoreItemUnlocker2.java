package com.thezs.fabianzachs.tapattack.MainMenu.Store.TopStoreUIComponents;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.thezs.fabianzachs.tapattack.ButtonOnTouchListener;
import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Database.MyDBHandler;
import com.thezs.fabianzachs.tapattack.MainMenu.Store.StoreFragment;
import com.thezs.fabianzachs.tapattack.R;

/**
 * Created by fabianzachs on 18/08/18.
 */

public class StoreItemUnlocker2 {

    public interface UnlockListener {
        void randomUnlockClick();
        void purchaseUnlockClick();
    }

    public void setUnlockListener(UnlockListener listener) {
        this.listener = listener;
    }

    private int WHITE = 0xffffffff;
    private int RED = 0xd93b3bff;

    private Activity activity;
    private UnlockListener listener;
    private StoreFragment storeFragment;
    private MyDBHandler myDBHandler;
    private RelativeLayout randomUnlockSection;
    private RelativeLayout purchaseUnlockSection;
    private TextView unlockFraction;


    public StoreItemUnlocker2(Activity activity, MyDBHandler myDBHandler, StoreFragment storeFragment, View view) {
        this.activity = activity;
        this.myDBHandler = myDBHandler;
        this.storeFragment = storeFragment;
        this.randomUnlockSection = view.findViewById(R.id.random_unlock_section);
        this.purchaseUnlockSection = view.findViewById(R.id.purchase_unlock_section);
        this.unlockFraction =  view.findViewById(R.id.unlocked_fraction);

        updateUnlockedFraction();
        //updateRandomUnlockView();
        //updatePurchaseUnlockView();

        setupUnlockButtonClicks();

    }




    public void updateUnlockedFraction() {
        String section = storeFragment.getCurrentlyDisplayedItemFragmentTAG();
        int numberUnlocked = myDBHandler.getNumberOfItemsFromCategory(section) - myDBHandler.getNumberOfLockedItems(section);
        int numberItems = myDBHandler.getNumberOfItemsFromCategory(section);
        unlockFraction.setText(activity.getResources().getString(R.string.itemsUnlockedFraction, numberUnlocked,numberItems));
    }


    public void updateRandomUnlockView() {
        Log.d("randomunlockupdate", "updateRandomUnlockView: updated");
        TextView randomUnlockText = randomUnlockSection.findViewById(R.id.random_unlock_text);

        randomUnlockText.setText(activity.getResources()
                .getString(R.string.randomUnlockText, storeFragment.getCurrentRandomUnlockPrice()));
        randomUnlockText.setTextColor(getRandomUnlockTextColor());

        if (isRandomUnlockVisible()) {
            Log.d("randomunlockupdate", "updateRandomUnlockView: visible");
            randomUnlockSection.setVisibility(View.VISIBLE);
        }
        else {
            //Log.d("randomunlockupdate", "updateRandomUnlockView: invisible " + randomUnlockSection.getVisibility());
            randomUnlockSection.setVisibility(View.INVISIBLE);
        }
    }


    public void updatePurchaseUnlockView() {
        TextView purchaseUnlockText = purchaseUnlockSection.findViewById(R.id.purchase_unlock_text);

        purchaseUnlockText.setText(activity.getResources()
                .getString(R.string.purchaseUnlockText, storeFragment.getCurrentPurchaseUnlockPrice()));
        purchaseUnlockText.setTextColor(getPurchaseUnlockTextColor());

        if (isPurchaseUnlockVisible()) {
            purchaseUnlockSection.setVisibility(View.VISIBLE);
        } else
            purchaseUnlockSection.setVisibility(View.INVISIBLE);
    }

    private void setupUnlockButtonClicks() {

        randomUnlockSection.setOnTouchListener(new ButtonOnTouchListener(activity, randomUnlockSection, new ButtonOnTouchListener.ButtonExecuteListener() {
            @Override
            public void buttonAction() {
                if (isRandomUnlockVisible() && enoughPointsForRandomUnlock())
                    listener.randomUnlockClick();
            }
        }));
    }


    private int getRandomUnlockTextColor() {
        return enoughPointsForRandomUnlock() ? WHITE : RED;
    }

    private boolean enoughPointsForRandomUnlock() {
        return storeFragment.getCurrentRandomUnlockPrice() <= storeFragment.getCurrentPoints();
    }

    private boolean isRandomUnlockVisible() {
        return !allUnlocked() && !storeFragment.getCurrentlyDisplayedItemFragmentTAG().equals(Constants.GAME_MODE_TAG);
    }

    private boolean allUnlocked() {
        return myDBHandler.getListOfLockedItems(storeFragment.getCurrentlyDisplayedItemFragmentTAG()).length == 0;
    }

    private int getPurchaseUnlockTextColor() {
        return enoughPointsForPurchaseUnlock() ? WHITE : RED;
    }

    private boolean enoughPointsForPurchaseUnlock() {
        return storeFragment.getCurrentPurchaseUnlockPrice() <= storeFragment.getCurrentPoints();
    }

    private boolean isPurchaseUnlockVisible() {
        return (storeFragment.isCurrentSelectedItemUnlocked() != 1)
                && !storeFragment.getCurrentlyDisplayedItemFragmentTAG().equals(Constants.GAME_MODE_TAG);
    }



}
