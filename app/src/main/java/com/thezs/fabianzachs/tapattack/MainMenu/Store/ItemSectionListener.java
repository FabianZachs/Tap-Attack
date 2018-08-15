package com.thezs.fabianzachs.tapattack.MainMenu.Store;

import android.graphics.drawable.Drawable;

/**
 * Created by fabianzachs on 13/08/18.
 */

interface ItemSectionListener {
    void selectedItemChanged(String section, Drawable itemImage, String itemTitle ,int unlocked);
}
