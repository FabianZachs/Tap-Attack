package com.thezs.fabianzachs.tapattack.MainMenu.Store.TopStoreUIComponents;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.thezs.fabianzachs.tapattack.R;
import com.thezs.fabianzachs.tapattack.helper;

/**
 * Created by fabianzachs on 15/08/18.
 */

public class DisplayedItem {

    private View view;
    private TextView displayedItemName;
    private ImageView displayedItemImage;
    private ImageView lockImage;
    private SharedPreferences.Editor prefsEditor;

    public DisplayedItem(View view, SharedPreferences.Editor prefsEditor) {
        this.view = view;

        this.prefsEditor = prefsEditor;
        this.displayedItemName= view.findViewById(R.id.item_title_text);
        this.displayedItemImage = view.findViewById(R.id.selected_item);
        this.lockImage = view.findViewById(R.id.lock);

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

    public void startAnimation(Activity activity) {
        displayedItemImage.startAnimation(AnimationUtils.loadAnimation(activity,
                R.anim.selected_item));
        view.findViewById(R.id.item_highlight).startAnimation(AnimationUtils.loadAnimation(activity,
                R.anim.selected_item));
        view.findViewById(R.id.item_highlight1).startAnimation(AnimationUtils.loadAnimation(activity,
                R.anim.rotate_clockwise));
        view.findViewById(R.id.item_highlight2).startAnimation(AnimationUtils.loadAnimation(activity,
                R.anim.rotate_anticlockwise));
    }
    public void stopAnimation() {
        displayedItemImage.clearAnimation();
        view.findViewById(R.id.item_highlight).clearAnimation();
        view.findViewById(R.id.item_highlight1).clearAnimation();
        view.findViewById(R.id.item_highlight2).clearAnimation();
    }

}
