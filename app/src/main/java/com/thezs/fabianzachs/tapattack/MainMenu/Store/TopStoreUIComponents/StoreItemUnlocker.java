package com.thezs.fabianzachs.tapattack.MainMenu.Store.TopStoreUIComponents;

import android.app.Activity;
import android.view.View;
import android.widget.RelativeLayout;

import com.thezs.fabianzachs.tapattack.ButtonOnTouchListener;
import com.thezs.fabianzachs.tapattack.MainMenu.Store.BottomStoreComponents.ItemSectionFragment;
import com.thezs.fabianzachs.tapattack.R;

/**
 * Created by fabianzachs on 15/08/18.
 */

public class StoreItemUnlocker {

    // todo needs randomUnlock method
    // todo needs purchaseUnlock method
    // todo needs to handle random unlock button (text value for unlock, red text if not enough points)
    // todo needs to handle purchase unlock button (^)
    // todo needs to update x/y unlocked view
    // todo needs listener that store will implement to update points, gridview (for lockimage and selected), displayed item
    public StoreItemUnlocker(Activity activity, View view) {

        RelativeLayout randomUnlockSection = (RelativeLayout) view.findViewById(R.id.random_unlock_section);
        randomUnlockSection.setOnTouchListener(new ButtonOnTouchListener(activity, randomUnlockSection, new ButtonOnTouchListener.ButtonExecuteListener() {
            @Override
            public void buttonAction() {
                //randomUnlockClick();
                //viewPager.getCurrentItem().;
                //ItemSectionFragment currentFragment = (ItemSectionFragment) fragmentAdapter.getItem(currentlyDisplayedFragmentIndex);
                //currentFragment.randomUnlock();
                // todo either listener which tells storefragment to tell currentFragment.randomUnlock() or call a method in here
            }
        }));

        RelativeLayout purchaseSection = view.findViewById(R.id.purchase_unlock_section);
        purchaseSection.setOnTouchListener(new ButtonOnTouchListener(activity, purchaseSection, new ButtonOnTouchListener.ButtonExecuteListener() {
            @Override
            public void buttonAction() {
                // todo similar idea as above
            }
        }));
    }

    public void updateUnlockedFraction(String section) {
        // todo get myDB and use similar logic as used before
    }

}
