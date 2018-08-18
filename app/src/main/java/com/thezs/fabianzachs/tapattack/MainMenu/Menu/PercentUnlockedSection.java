package com.thezs.fabianzachs.tapattack.MainMenu.Menu;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.thezs.fabianzachs.tapattack.Database.MyDBHandler;
import com.thezs.fabianzachs.tapattack.R;

/**
 * Created by fabianzachs on 18/08/18.
 */

public class PercentUnlockedSection {

    private Activity activity;
    private TextView percentUnlockedText;
    private MyDBHandler myDBHandler;

    public PercentUnlockedSection(Activity activity, View view) {
        this.activity = activity;
        this.myDBHandler = new MyDBHandler(activity, null, null, 1);
        this.percentUnlockedText = view.findViewById(R.id.percent_unlocked_text);
        updatePercentUnlockedText();
    }

    public void updatePercentUnlockedText() {
        int numberOfUnlocked = myDBHandler.getNumberOfUnlockedItems();
        int numberOfItems = myDBHandler.getNumberOfItems();
        int percent =(int) (((float) numberOfUnlocked / numberOfItems) * 100);
        percentUnlockedText.setText(activity.getResources().getString(R.string.percentUnlockedText, percent));

    }
}
