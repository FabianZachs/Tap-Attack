package com.thezs.fabianzachs.tapattack.MainMenu.Menu.PaidUnlockSection;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.thezs.fabianzachs.tapattack.ButtonOnTouchListener;
import com.thezs.fabianzachs.tapattack.R;

import java.util.ArrayList;

public class PaidPointsFragment extends PaidItemType {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.items.add(new PaidUnlockItem(1,5000, R.drawable.star1));
        this.items.add(new PaidUnlockItem(2,10000, R.drawable.star2));
        this.items.add(new PaidUnlockItem(3,20000, R.drawable.star3));
        this.items.add(new PaidUnlockItem(4,40000, R.drawable.star4));
        this.items.add(new PaidUnlockItem(5,80000, R.drawable.star5));
        adapter = new PaidUnlockAdapter("points", getActivity(), R.layout.list_purchase_skeleton, items);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        ((TextView) view.findViewById(R.id.title_text)).setText(getActivity().getResources().getString(R.string.morePoints));


        return view;

    }


}
