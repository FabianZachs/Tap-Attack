package com.thezs.fabianzachs.tapattack.MainMenu.Store;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.R;

/**
 * Created by fabianzachs on 10/08/18.
 */

public class GamemodeSectionFragment extends ItemSectionFragment {

    //private String SECTION = "game mode";
    //public String DEFAULT_SECTION_VALUE = Constants.GAMEMODES[0];




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDEFAULT_SECTION_VALUE(Constants.GAMEMODES[0]);
        setSECTION("game mode");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.store_item_grid, container, false);
        Log.d("onviewcreateitem", "onCreateView: " + SECTION);

        super.setupItemGrid(view, SECTION, DEFAULT_SECTION_VALUE);
        super.setInitialDisplayedItemFromThisSection();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        super.setListener(context);
    }


}
