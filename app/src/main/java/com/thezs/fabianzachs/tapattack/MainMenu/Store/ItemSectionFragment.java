package com.thezs.fabianzachs.tapattack.MainMenu.Store;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thezs.fabianzachs.tapattack.Database.MyDBHandler;
import com.thezs.fabianzachs.tapattack.R;

/**
 * Created by fabianzachs on 12/08/18.
 */

public abstract class ItemSectionFragment extends Fragment {
    protected SharedPreferences prefs;
    protected MyDBHandler dbHandler;
    public ItemSectionListener listener;

    protected void setListener(Context context) {
        if (getParentFragment() instanceof ItemSectionListener) {
            listener = (ItemSectionListener) getParentFragment();
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement GamemodeSectionFragment.GameModeSectionFragmentListener");
        }
    }
}
