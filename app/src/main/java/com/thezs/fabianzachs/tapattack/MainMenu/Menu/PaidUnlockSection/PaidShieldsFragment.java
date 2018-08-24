package com.thezs.fabianzachs.tapattack.MainMenu.Menu.PaidUnlockSection;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thezs.fabianzachs.tapattack.R;

public class PaidShieldsFragment extends PaidItemType {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.items.add(new PaidUnlockItem(1,3, R.drawable.shield1));
        this.items.add(new PaidUnlockItem(2,6, R.drawable.shield2));
        this.items.add(new PaidUnlockItem(3,10, R.drawable.shield3));
        this.items.add(new PaidUnlockItem(4,20, R.drawable.shield4));
        this.items.add(new PaidUnlockItem(5,50, R.drawable.shield5));
        adapter = new PaidUnlockAdapter("shields", getActivity(), R.layout.list_purchase_skeleton, items);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        ((TextView) view.findViewById(R.id.title_text)).setText(getActivity().getResources().getString(R.string.shields));

        return view;

    }
}
