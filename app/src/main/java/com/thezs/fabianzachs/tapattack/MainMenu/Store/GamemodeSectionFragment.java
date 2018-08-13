package com.thezs.fabianzachs.tapattack.MainMenu.Store;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Database.MyDBHandler;
import com.thezs.fabianzachs.tapattack.R;
import com.thezs.fabianzachs.tapattack.helper;

/**
 * Created by fabianzachs on 10/08/18.
 */

public class GamemodeSectionFragment extends ItemSectionFragment {

    private String SECTION = "game mode";



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.store_item_grid, container, false);

        Log.d("onviewcreateitem", "onCreateView: " + SECTION);
        super.setupItemGrid(view, SECTION);
        super.setInitialSelectedItem(SECTION, Constants.GAMEMODES[0]);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        super.setListener(context);
    }


}
