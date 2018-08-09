package com.thezs.fabianzachs.tapattack;

import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdView;

public class StoreFragment extends Fragment {

    AdView mAdView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.store_fragment, container, false);
        View backView = (View) view.findViewById(R.id.go_back);
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainMenuActivity)getActivity()).setViewPager(1);
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        helper.bannerAdSetup(getActivity(), mAdView);


        BottomNavigationView bottomNavigationView = (BottomNavigationView) getView().findViewById(R.id.bottom_nav_bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
    }
}
