package com.thezs.fabianzachs.tapattack.MainMenu.Store.BottomStoreComponents.ItemSectionFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.MainMenu.Store.BottomStoreComponents.ItemSectionFragment;
import com.thezs.fabianzachs.tapattack.R;

/**
 * Created by fabianzachs on 10/08/18.
 */

public class GamemodeSectionFragment extends ItemSectionFragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDEFAULT_SECTION_VALUE(Constants.GAMEMODES[0]);
        setSECTION(Constants.GAME_MODE_TAG);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.store_item_grid, container, false);

        super.setupItemGrid(view);
        super.notifyNewItemToDisplayFromThisSectionBecauseSectionChange();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        super.setListener(context);
    }


}
