package com.thezs.fabianzachs.tapattack.MainMenu.Store;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.thezs.fabianzachs.tapattack.ButtonOnTouchListener;
import com.thezs.fabianzachs.tapattack.Database.MyDBHandler;
import com.thezs.fabianzachs.tapattack.MainMenu.Store.BottomStoreComponents.ItemSectionFragment;
import com.thezs.fabianzachs.tapattack.MainMenu.Store.BottomStoreComponents.MyBottomNavigation;
import com.thezs.fabianzachs.tapattack.MainMenu.Store.BottomStoreComponents.MyStoreItemSectionViewPager;
import com.thezs.fabianzachs.tapattack.MainMenu.Store.TopStoreUIComponents.DisplayedItem;
import com.thezs.fabianzachs.tapattack.MainMenu.Store.TopStoreUIComponents.SectionTitle;
import com.thezs.fabianzachs.tapattack.MainMenu.Store.TopStoreUIComponents.StoreItemUnlocker2;
import com.thezs.fabianzachs.tapattack.MainMenu.Store.TopStoreUIComponents.StorePoints;
import com.thezs.fabianzachs.tapattack.MainMenu.Store.TopStoreUIComponents.StoreRewardVideoAd;
import com.thezs.fabianzachs.tapattack.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by fabianzachs on 16/08/18.
 */

final public class StoreFragment extends Fragment implements ItemSectionFragment.ItemSectionListener{

    private StoreListener storeListener;

    private SharedPreferences prefs;
    private MyDBHandler myDBHandler;

    private StorePoints storePoints;
    private StoreItemUnlocker2 storeItemUnlocker;
    private StoreRewardVideoAd customVideoAd;
    private DisplayedItem displayedItem;
    private SectionTitle sectionTitle;
    private MyBottomNavigation myBottomNavigation;
    private MyStoreItemSectionViewPager storeItemSectionviewPager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getActivity().getSharedPreferences("playerInfo", MODE_PRIVATE);
        prefs.edit().putInt("points", 17000).apply();
        myDBHandler = new MyDBHandler(getActivity(), null, null, 1);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.store_fragment2, container, false);
        this.storeItemSectionviewPager = new MyStoreItemSectionViewPager(getContext(),this,view);
        this.storePoints = new StorePoints(getActivity(), view, prefs);
        this.storeItemUnlocker = new StoreItemUnlocker2(getActivity(), myDBHandler, this, view);
        this.customVideoAd = new StoreRewardVideoAd(getActivity(), view);
        this.displayedItem = new DisplayedItem(view, prefs.edit());
        this.sectionTitle = new SectionTitle(view, this);
        this.myBottomNavigation = new MyBottomNavigation(getContext(), view);

        setListeners();

        final ImageView backButton= (ImageView) view.findViewById(R.id.store_back_image);
        backButton.setOnTouchListener(new ButtonOnTouchListener(getActivity(), backButton, new ButtonOnTouchListener.ButtonExecuteListener() {
            @Override
            public void buttonAction() {
                storeListener.storeFragmentToMenuFragment();
            }
        }));

        return view;
    }


    public void onShow() {
        storePoints.updateCurrentPoints();
        storeItemUnlocker.updateUnlockedFraction();
        storeItemUnlocker.updateRandomUnlockView();
        storeItemUnlocker.updatePurchaseUnlockView();
        displayedItem.startAnimation(getActivity());
        sectionTitle.updateSectionText();



        ItemSectionFragment currentlyDisplayedFragment = storeItemSectionviewPager.getCurrentlyDisplayedFragment();
        currentlyDisplayedFragment.setEqiupedItemToSelectedItem();
        currentlyDisplayedFragment.updateGridView();
        currentlyDisplayedFragment.notifyNewItemToDisplayFromThisSectionBecauseSectionChange();

    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof StoreListener) {
            storeListener = (StoreListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement StoreListener");
        }
    }

    private void setListeners() {
        customVideoAd.setRewardAdListener(new StoreRewardVideoAd.RewardAdListener() {
            @Override
            public void videoAdCompleted(int earnedPoints) {
                storePoints.addToPointsAndUpdateView(earnedPoints);
            }
        });

        storeItemUnlocker.setUnlockListener(new StoreItemUnlocker2.UnlockListener() {
            @Override
            public void randomUnlockClick() {
                storeItemSectionviewPager.randomUnlockForCurrentItemSection();
            }

            @Override
            public void purchaseUnlockClick() {
                storeItemSectionviewPager.purchaseUnlockForCurrentItemSection();

            }
        });


        myBottomNavigation.setBottomNavigationListener(new MyBottomNavigation.BottomNavigationListener() {
            @Override
            public void setViewPage(int fragmentIndex) {
                // handles displayed item inside
                storeItemSectionviewPager.setCurrentlyDisplayedFragment(fragmentIndex);

                storeItemUnlocker.updateRandomUnlockView();
                storeItemUnlocker.updatePurchaseUnlockView();
                storeItemUnlocker.updateUnlockedFraction();
                sectionTitle.updateSectionText();
            }
        });

        storePoints.setStorePointsListener(new StorePoints.StorePointsListener() {
            @Override
            public void pointsUpdated() {
                storeItemUnlocker.updateUnlockedFraction();
                storeItemUnlocker.updateRandomUnlockView();
                storeItemUnlocker.updatePurchaseUnlockView();
            }
        });
    }

    @Override
    public void selectedItemChanged(String section, Drawable itemImage, String itemTitle, int unlocked) {
        displayedItem.updateDisplayedItem(section, itemImage, itemTitle, unlocked);
        //storeItemUnlocker.updateRandomUnlockView();
        storeItemUnlocker.updatePurchaseUnlockView();

    }

    @Override
    public void itemUnlockComplete(final String typeOfUnlock) {
        switch (typeOfUnlock) {
            case "random":
               storePoints.addToPointsAndUpdateView(-getCurrentRandomUnlockPrice());
               storeItemUnlocker.updateRandomUnlockView();
               break;
            case "purchase":
                storePoints.addToPointsAndUpdateView(-getCurrentPurchaseUnlockPrice());
                storeItemUnlocker.updatePurchaseUnlockView();
                break;
            default: throw new RuntimeException("type of unlock not found");
        }
    }

    public int isCurrentSelectedItemUnlocked() {
        return storeItemSectionviewPager.isCurrentlySelectedItem().get_unlocked();
    }



    public interface StoreListener {
        void storeFragmentToMenuFragment();
        //void setupStoreVideoRewardAd();
        //void videoAdRequested();
    }

    public String getCurrentlyDisplayedItemFragmentTAG() {
        return storeItemSectionviewPager.getTAGOfCurrentlyDisplayedFragment();
    }

    public int getCurrentRandomUnlockPrice() {
        return storeItemSectionviewPager.getCurrentRandomUnlockPrice();
    }

    public int getCurrentPurchaseUnlockPrice() {
        return storeItemSectionviewPager.getCurrentPurchaseUnlockPrice();
    }

    public int getCurrentPoints() {
        return storePoints.getCurrentPoints();
    }

}
