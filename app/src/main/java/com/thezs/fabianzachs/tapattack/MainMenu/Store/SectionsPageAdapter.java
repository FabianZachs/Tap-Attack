package com.thezs.fabianzachs.tapattack.MainMenu.Store;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabianzachs on 09/08/18.
 */

public class SectionsPageAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragments = new ArrayList<>();
    private final List<String> fragmentTitles = new ArrayList<>();


    public SectionsPageAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragmentToAdd, String title) {
        fragments.add(fragmentToAdd);
        fragmentTitles.add(title);
    }

    public String titleOfFragment(Fragment fragment) {
        return fragmentTitles.get(fragments.indexOf(fragment));
    }

    @Override
    public String getPageTitle(int position) {
        return fragmentTitles.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
