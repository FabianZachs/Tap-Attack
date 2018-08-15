package com.thezs.fabianzachs.tapattack.MainMenu.Store.BottomStoreComponents;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.R;

/**
 * Created by fabianzachs on 15/08/18.
 */

public class MyBottomNavigation extends BottomNavigationView {

    private BottomNavigationListener listener;

    public MyBottomNavigation(Context context, View view, BottomNavigationListener listener) {
        super(context);
        this.listener = listener;
        setupBottomNavigation(view);

    }

    public interface BottomNavigationListener {
        void setViewPage(int fragmentIndex);
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
                                listener.setViewPage(0);
                                //setViewPager(0);
                                //setCurrentItemFragmentIndex(0);
                                //viewPager.getCurrentItem();
                                //Log.d("currentitem", "onNavigationItemSelected: " +viewPager.getCurrentItem());
                                return true;
                            case R.id.ic_shape_type:
                                listener.setViewPage(1);
                                //setViewPager(1);
                                //setCurrentItemFragmentIndex(1);
                                //Log.d("currentitem", "onNavigationItemSelected: " +viewPager.getCurrentItem());
                                return true;
                            case R.id.ic_theme:
                                listener.setViewPage(2);
                                //setViewPager(2);
                                //setCurrentItemFragmentIndex(2);
                                //Log.d("currentitem", "onNavigationItemSelected: " +viewPager.getCurrentItem());
                                return true;
                            case R.id.ic_background:
                                listener.setViewPage(3);
                                //setViewPager(3);
                                //setCurrentItemFragmentIndex(3);
                                //Log.d("currentitem", "onNavigationItemSelected: " +viewPager.getCurrentItem());
                                return true;
                        }
                        return false;
                    }
                }
        );
    }

}
