package com.thezs.fabianzachs.tapattack.MainMenu.Store.BottomStoreComponents;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.MainMenu.Store.BottomStoreComponents.ItemSectionFragments.BackgroundSectionFragment;
import com.thezs.fabianzachs.tapattack.MainMenu.Store.BottomStoreComponents.ItemSectionFragments.GamemodeSectionFragment;
import com.thezs.fabianzachs.tapattack.MainMenu.Store.BottomStoreComponents.ItemSectionFragments.ShapeThemeSectionFragment;
import com.thezs.fabianzachs.tapattack.MainMenu.Store.BottomStoreComponents.ItemSectionFragments.ShapeTypeSectionFragment;
import com.thezs.fabianzachs.tapattack.MainMenu.Store.CustomViewPager;
import com.thezs.fabianzachs.tapattack.MainMenu.Store.SectionsPageAdapter;
import com.thezs.fabianzachs.tapattack.MainMenu.Store.StoreFragment;
import com.thezs.fabianzachs.tapattack.R;

/**
 * Created by fabianzachs on 16/08/18.
 */

public class MyStoreItemSectionViewPager {

    private final int NUMBER_OF_SECTIONS = 4;
    private StoreFragment storeFragment;
    private CustomViewPager viewPager;
    private SectionsPageAdapter fragmentAdapter;
    //private ViewPagerListener listener;
    private int currentlyDisplayedFragmentIndex;

    public MyStoreItemSectionViewPager(Context context, StoreFragment storeFragment, View view) {
        this.storeFragment = storeFragment;
        currentlyDisplayedFragmentIndex = 0;
        setupItemsSection(view);

    }

    public String getTAGOfCurrentlyDisplayedFragment() {
        return fragmentAdapter.getPageTitle(currentlyDisplayedFragmentIndex);
    }

    public int getCurrentRandomUnlockPrice() {
        return Constants.PRICE_BY_SECTION[currentlyDisplayedFragmentIndex];
    }

    public void setCurrentlyDisplayedFragment(int index) {
        viewPager.setCurrentItem(index);
    }

    public void randomUnlockForCurrentItemSection() {
        ItemSectionFragment currentFragment = (ItemSectionFragment) fragmentAdapter.getItem(currentlyDisplayedFragmentIndex);
        currentFragment.randomUnlock();
    }

    public void purchaseUnlockForCurrentItemSection() {
        ItemSectionFragment currentFragment = (ItemSectionFragment) fragmentAdapter.getItem(currentlyDisplayedFragmentIndex);
        currentFragment.purchaseUnlock();
    }


    /*
    public interface ViewPagerListener {
        void newItemSectionPageSelected(int fragmentIndex);
    }

    public void setViewPagerListener(ViewPagerListener listener) {
        this.listener = listener;
    }
    */




    private void setupItemsSection(View view) {
        viewPager = (CustomViewPager) view.findViewById(R.id.store_container);
        viewPager.setOffscreenPageLimit(NUMBER_OF_SECTIONS - 1);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ItemSectionFragment currentlyDisplayedFragment = (ItemSectionFragment)  fragmentAdapter.getItem(position);
                currentlyDisplayedFragment.setEqiupedItemToSelectedItem();
                currentlyDisplayedFragment.updateGridView();


                currentlyDisplayedFragment.notifyNewItemToDisplayFromThisSectionBecauseSectionChange();
                currentlyDisplayedFragmentIndex = position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        fragmentAdapter = new SectionsPageAdapter(storeFragment.getChildFragmentManager());
        fragmentAdapter.addFragment(new GamemodeSectionFragment(), Constants.GAME_MODE_TAG);
        fragmentAdapter.addFragment(new ShapeTypeSectionFragment(), Constants.SHAPE_TYPE_TAG);
        fragmentAdapter.addFragment(new ShapeThemeSectionFragment(), Constants.SHAPE_THEME_TAG);
        fragmentAdapter.addFragment(new BackgroundSectionFragment(), Constants.BACKGROUND_TAG);
        // to set the initially shown item connected to the first one shown in the viewpager (index 0 shown first)
        //((ItemSectionFragment)fragmentAdapter.getItem(0)).notifyNewItemToDisplayFromThisSectionBecauseSectionChange(fragmentAdapter.getPageTitle(0), ((ItemSectionFragment) fragmentAdapter.getItem(0)).getDEFAULT_SECTION_VALUE());
        viewPager.setAdapter(fragmentAdapter);

    }

    /*
    public void setItemSectionListeners(ItemSectionFragment.ItemSectionListener listener) {
        for (int i = 0; i<fragmentAdapter.getCount();i++) {
            ((ItemSectionFragment)fragmentAdapter.getItem(i)).setItemSectionListener(listener);
        }
    }
    */
}
