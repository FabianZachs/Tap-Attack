package com.thezs.fabianzachs.tapattack.MainMenu.Store;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.CustomListView;
import com.thezs.fabianzachs.tapattack.Database.MyDBHandler;
import com.thezs.fabianzachs.tapattack.R;

/**
 * Created by fabianzachs on 10/08/18.
 */

public class ShapeThemeSectionFragment extends ItemSectionFragment {

    //private String SECTION = "shape theme";
    //private String DEFAULT_SECTION_VALUE = Constants.SHAPE_THEMES[0];

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDEFAULT_SECTION_VALUE(Constants.SHAPE_THEMES[0]);
        setSECTION("shape theme");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.store_item_grid, container, false);
        super.setupItemGrid(view, SECTION, DEFAULT_SECTION_VALUE); //todo does gridview know which item to highlight first
        //super.setInitialDisplayedItemFromThisSection(SECTION, DEFAULT_SECTION_VALUE);
        Log.d("onviewcreateitem", "onCreateView: " + SECTION);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        super.setListener(context);
    }
}
