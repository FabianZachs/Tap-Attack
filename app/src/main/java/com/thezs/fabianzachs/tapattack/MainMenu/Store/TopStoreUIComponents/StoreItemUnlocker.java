package com.thezs.fabianzachs.tapattack.MainMenu.Store.TopStoreUIComponents;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.thezs.fabianzachs.tapattack.ButtonOnTouchListener;
import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Database.MyDBHandler;
import com.thezs.fabianzachs.tapattack.MainMenu.Store.StoreFragment;
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
    private StoreFragment storeFragment;
    // todo needs to handle purchase unlock button (^)
    // todo needs listener that store will implement to update points, gridview (for lockimage and selected), displayed item
    public StoreItemUnlocker(final StoreFragment storeFragment, final Activity activity, View view, final UnlockListener listener, MyDBHandler myDBHandler) {

        this.fraction = (TextView) view.findViewById(R.id.unlocked_fraction);
        this.randomUnlockText = view.findViewById(R.id.random_unlock_text);
        this.randomUnlockSection = view.findViewById(R.id.random_unlock_section);
        this.listener = listener;
        this.myDBHandler = myDBHandler;
        this.storeFragment = storeFragment;
        updateUnlockedFraction();
        updateRandomUnlockView();

        final RelativeLayout randomUnlockSection = view.findViewById(R.id.random_unlock_section);
                randomUnlockSection.setOnTouchListener(new ButtonOnTouchListener(activity, randomUnlockSection, new ButtonOnTouchListener.ButtonExecuteListener() {
            @Override
            public void buttonAction() {

                if (randomUnlockActive()) {

                    StyleableToast.makeText(activity,  "UNLOCKING...", R.style.successtoast).show();
                    listener.randomUnlockClick();
                }
            }
        }));

        RelativeLayout purchaseSection = view.findViewById(R.id.purchase_unlock_section);
        purchaseSection.setOnTouchListener(new ButtonOnTouchListener(activity, purchaseSection, new ButtonOnTouchListener.ButtonExecuteListener() {
            @Override
            public void buttonAction() {
                if (true/*handle for if purcase unlock should be run(maybe not enough points)*/) {
                    //listener.purchaseUnlockClick();
                    StyleableToast.makeText(activity,  "UNLOCKING PURCHASE...", R.style.successtoast).show();
                }
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
        /*
        if (randomUnlockSection.getVisibility()==View.VISIBLE)
            return true;
        if (enoughPointsForRandomUnlock())
            return true;
        return false;
        */


        return (randomUnlockSection.getVisibility()==View.VISIBLE && enoughPointsForRandomUnlock());
    }

    private boolean purchaseUnlockActive() {
        // todo similar to above
    }


    public void updateRandomUnlockView() {
        randomUnlockText.setText(activity.getResources().getString(R.string.randomUnlockText, storeFragment.getCurrentRandomUnlockPrice()));

        if (!enoughPointsForRandomUnlock())
            randomUnlockText.setTextColor(ContextCompat.getColor(activity, R.color.soundoff));

        if (randomUnlockTextVisible())
            randomUnlockSection.setVisibility(View.VISIBLE);
        else
            randomUnlockSection.setVisibility(View.INVISIBLE);
    }

    private boolean enoughPointsForRandomUnlock() {
        return storeFragment.getCurrentRandomUnlockPrice() <= storeFragment.getCurrentPoints();
    }

    private boolean randomUnlockTextVisible() {

        return !storeFragment.getCurrentlyDisplayedItemFragmentTAG.equals(Constants.GAME_MODE_TAG)
                &&  myDBHandler.getListOfLockedItems(storeFragment.getCurrentlyDisplayedItemFragmentTAG) != 0;

    }

    public interface UnlockListener {
        void randomUnlockClick();
        void purchaseUnlockClick();
    }

}
