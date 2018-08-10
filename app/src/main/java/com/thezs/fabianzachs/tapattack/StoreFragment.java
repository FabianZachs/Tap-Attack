package com.thezs.fabianzachs.tapattack;

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
import android.widget.ImageView;

import com.google.android.gms.ads.AdView;
import com.thezs.fabianzachs.tapattack.Store.BackgroundSectionFragment;
import com.thezs.fabianzachs.tapattack.Store.GamemodeSectionFragment;
import com.thezs.fabianzachs.tapattack.Store.ShapeThemeSectionFragment;
import com.thezs.fabianzachs.tapattack.Store.ShapeTypeSectionFragment;

public class StoreFragment extends Fragment {

    private AdView mAdView;
    private SectionsPageAdapter sectionsPageAdapter;
    private ViewPager viewPager;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.store_fragment, container, false);
        setupBottomNavigation(view);
        ImageView backView = (ImageView) view.findViewById(R.id.store_back_image);
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainMenuActivity)getActivity()).setViewPager(1);
            }
        });

        setupItemsSection(view);
        return view;
    }

    public void setViewPager(int fragmentNumber) {
        viewPager.setCurrentItem(fragmentNumber);
    }

    private void setupItemsSection(View view) {
        sectionsPageAdapter = new SectionsPageAdapter(getActivity().getSupportFragmentManager());
        viewPager = (ViewPager) view.findViewById(R.id.store_container);
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
        helper.bannerAdSetup(getActivity(), mAdView);


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
                                //FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                //fragmentTransaction.replace(R.id.flContainer, fragment1).commit();
                                return true;
                            case R.id.ic_shape_type:
                                setViewPager(1);
                                //FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                //fragmentTransaction.replace(R.id.flContainer, fragment2).commit();
                                return true;
                            case R.id.ic_theme:
                                setViewPager(2);
                                //FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                //fragmentTransaction.replace(R.id.flContainer, fragment3).commit();
                                return true;
                            case R.id.ic_background:
                                setViewPager(3);
                                //FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                //fragmentTransaction.replace(R.id.flContainer, fragment3).commit();
                                return true;
                        }
                        return false;
                    }
                }
        );
    }

}
