package com.thezs.fabianzachs.tapattack.MainMenu.Store;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.ads.AdView;
import com.thezs.fabianzachs.tapattack.ButtonOnTouchListener;
import com.thezs.fabianzachs.tapattack.MainMenu.CustomViewPager;
import com.thezs.fabianzachs.tapattack.MainMenu.SectionsPageAdapter;
import com.thezs.fabianzachs.tapattack.R;

public class StoreFragment extends Fragment {

    private AdView mAdView;
    private SectionsPageAdapter sectionsPageAdapter;
    private CustomViewPager viewPager;

    private StoreListener storeListener;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.store_fragment, container, false);
        setupBottomNavigation(view);



        final ImageView backButton= (ImageView) view.findViewById(R.id.store_back_image);
        backButton.setOnTouchListener(new ButtonOnTouchListener(getActivity(), backButton, "fragmentToMenu", new ButtonOnTouchListener.ButtonExecuteListener() {
            @Override
            public void buttonAction1() {
               storeListener.changeToMenuFragment();
            }
        }));


        setupItemsSection(view);

        final Button randomUnlock = (Button) view.findViewById(R.id.random_unlock_text);
        //randomUnlock.setOnTouchListener(new ButtonOnTouchListener(getActivity(), randomUnlock, "randomUnlock"));




        return view;
    }


    public interface StoreListener {
        void changeToMenuFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof StoreListener) {
            storeListener = (StoreListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement MyListFragment.OnItemSelectedListener");
        }

    }

    public void backButtonClick() {
        storeListener.changeToMenuFragment();
    }

    public void setViewPager(int fragmentNumber) {
        viewPager.setCurrentItem(fragmentNumber);
    }

    private void setupItemsSection(View view) {
        sectionsPageAdapter = new SectionsPageAdapter(getActivity().getSupportFragmentManager());
        viewPager = (CustomViewPager) view.findViewById(R.id.store_container);
        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getChildFragmentManager());
        adapter.addFragment(new GamemodeSectionFragment(), "gamemode");
        adapter.addFragment(new ShapeTypeSectionFragment(), "shapetype");
        adapter.addFragment(new ShapeThemeSectionFragment(), "gamemode");
        adapter.addFragment(new BackgroundSectionFragment(), "gamemode");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();


    }


    private void setupBottomNavigation(View view) {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) view.findViewById(R.id.bottom_nav_bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.ic_gamemode:
                                setViewPager(0);
                                return true;
                            case R.id.ic_shape_type:
                                setViewPager(1);
                                return true;
                            case R.id.ic_theme:
                                setViewPager(2);
                                return true;
                            case R.id.ic_background:
                                setViewPager(3);
                                return true;
                        }
                        return false;
                    }
                }
        );
    }

}
