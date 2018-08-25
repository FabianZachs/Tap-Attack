package com.thezs.fabianzachs.tapattack.MainMenu.Menu;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.thezs.fabianzachs.tapattack.ButtonOnTouchListener;
import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Database.MyDBHandler;
import com.thezs.fabianzachs.tapattack.R;

/**
 * Created by fabianzachs on 18/08/18.
 */

public class PercentUnlockedSection {

    private Activity activity;
    private TextView percentUnlockedText;
    private MyDBHandler myDBHandler;

    public PercentUnlockedSection(final Activity activity, MyDBHandler myDBHandler, final View view) {
        this.activity = activity;
        this.myDBHandler = myDBHandler;
        this.percentUnlockedText = view.findViewById(R.id.percent_unlocked_text);

        percentUnlockedText.setOnTouchListener(new ButtonOnTouchListener(activity, percentUnlockedText, new ButtonOnTouchListener.ButtonExecuteListener() {
            @Override
            public void buttonAction() {
                new StartMenuAnimation(view);
            }
        }));
    }

    public void updatePercentUnlockedText() {
        int numberOfUnlocked = myDBHandler.getNumberOfUnlockedItems() - Constants.NUMBER_OF_INITIALY_UNLOCKED_ITEMS;
        int numberOfItems = myDBHandler.getNumberOfItems();
        int percent =(int) (((float) numberOfUnlocked / numberOfItems) * 100);
        percentUnlockedText.setText(activity.getResources().getString(R.string.percentUnlockedText, percent));

    }
}
