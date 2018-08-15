package com.thezs.fabianzachs.tapattack.MainMenu.Store.TopStoreUIComponents;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.thezs.fabianzachs.tapattack.R;

/**
 * Created by fabianzachs on 15/08/18.
 */

public class DisplayedItem {

    private TextView displayedItemName;
    private ImageView displayedItemImage;
    private ImageView lockImage;
    private SharedPreferences prefs;
    private SharedPreferences.Editor prefsEditor;

    public DisplayedItem(View view, SharedPreferences prefs, SharedPreferences.Editor prefsEditor) {

        this.prefs = prefs;
        this.prefsEditor = prefsEditor;
        this.displayedItemName= (TextView) view.findViewById(R.id.item_title_text);
        this.displayedItemImage = (ImageView) view.findViewById(R.id.selected_item);
        this.lockImage = (ImageView) view.findViewById(R.id.lock);
    }

    public void updateDisplayedItem(String section, Drawable itemImage, String itemName, int unlocked) {
        displayedItemImage.setImageDrawable(itemImage);
        displayedItemName.setText(itemName.toUpperCase());


        if (unlocked == 1) {
            prefsEditor.putString(section, itemName);
            prefsEditor.apply();
            lockImage.setImageResource(android.R.color.transparent);
        }
        else
            lockImage.setImageResource(R.drawable.lockeditem);
    }

}
