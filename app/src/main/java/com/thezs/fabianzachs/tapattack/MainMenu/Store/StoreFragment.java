package com.thezs.fabianzachs.tapattack.MainMenu.Store;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.thezs.fabianzachs.tapattack.ButtonOnTouchListener;
import com.thezs.fabianzachs.tapattack.Database.MyDBHandler;
import com.thezs.fabianzachs.tapattack.MainMenu.Store.BottomStoreComponents.ItemSectionFragment;
import com.thezs.fabianzachs.tapattack.MainMenu.Store.BottomStoreComponents.MyBottomNavigation;
import com.thezs.fabianzachs.tapattack.MainMenu.Store.BottomStoreComponents.MyStoreItemSectionViewPager;
import com.thezs.fabianzachs.tapattack.MainMenu.Store.TopStoreUIComponents.DisplayedItem;
import com.thezs.fabianzachs.tapattack.MainMenu.Store.TopStoreUIComponents.SectionTitle;
import com.thezs.fabianzachs.tapattack.MainMenu.Store.TopStoreUIComponents.StoreItemUnlocker;
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
    private StoreItemUnlocker storeItemUnlocker;
    private StoreRewardVideoAd customVideoAd;
    private DisplayedItem displayedItem;
    private SectionTitle sectionTitle;
    private MyBottomNavigation myBottomNavigation;
    private MyStoreItemSectionViewPager storeItemSectionviewPager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getActivity().getSharedPreferences("playerInfo", MODE_PRIVATE);
        myDBHandler = new MyDBHandler(getActivity(), null, null, 1);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.store_fragment2, container, false);
        this.storePoints = new StorePoints(getActivity(), view, prefs);
        this.storeItemSectionviewPager = new MyStoreItemSectionViewPager(getContext(),this,view);
        this.storeItemUnlocker = new StoreItemUnlocker(this, getActivity(), view, myDBHandler);
        this.customVideoAd = new StoreRewardVideoAd(getActivity(), view);
        this.displayedItem = new DisplayedItem(view, prefs, prefs.edit());
        this.sectionTitle = new SectionTitle(view, this);
        this.myBottomNavigation = new MyBottomNavigation(getContext(), view);

        setListeners();

        // todo animations

        ImageView iv = (ImageView) view.findViewById(R.id.item_highlight);


        ObjectAnimator scaleDown = ObjectAnimator.ofPropertyValuesHolder(
                iv,
                PropertyValuesHolder.ofFloat("scaleX", 1.1f),
                PropertyValuesHolder.ofFloat("scaleY", 1.1f));
        scaleDown.setDuration(510);
        //scaleDown.setInterpolator(new FastOutSlowInInterpolator());

        scaleDown.setRepeatCount(ObjectAnimator.INFINITE);
        scaleDown.setRepeatMode(ObjectAnimator.REVERSE);
        scaleDown.start();

        ImageView star1 = (ImageView) view.findViewById(R.id.item_highlight1);
        Animation myAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_clockwise);
        star1.startAnimation(myAnim);

        ImageView star2 = (ImageView) view.findViewById(R.id.item_highlight2);
        Animation myAnim2 = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_anticlockwise);
        star2.startAnimation(myAnim2);

        final ImageView backButton= view.findViewById(R.id.store_back_image);
        backButton.setOnTouchListener(new ButtonOnTouchListener(getActivity(), backButton, new ButtonOnTouchListener.ButtonExecuteListener() {
            @Override
            public void buttonAction() {
                storeListener.storeFragmentToMenuFragment();
            }
        }));

        return view;
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

        storeItemUnlocker.setUnlockListener(new StoreItemUnlocker.UnlockListener() {
            @Override
            public void randomUnlockClick() {
                storeItemSectionviewPager.randomUnlockForCurrentItemSection();
            }

            @Override
            public void purchaseUnlockClick() {
                //storeItemSectionviewPager.purchaseUnlockForCurrentItemSection();
                storeItemSectionviewPager.purchaseUnlockForCurrentItemSection();

            }
        });

        /*
        storeItemSectionviewPager.setItemSectionListeners(new ItemSectionFragment.ItemSectionListener() {
            @Override
            public void selectedItemChanged(String section, Drawable itemImage, String itemTitle, int unlocked) {
                displayedItem.updateDisplayedItem(section, itemImage, itemTitle, unlocked);

            }

            @Override
            public void itemUnlockComplete() {
                storePoints.updateCurrentPoints();
                storeItemUnlocker.updateUnlockedFraction();
                storeItemUnlocker.updateRandomUnlockView();

            }
        });
        */

        myBottomNavigation.setBottomNavigationListener(new MyBottomNavigation.BottomNavigationListener() {
            @Override
            public void setViewPage(int fragmentIndex) {
                // handles displayed item inside
                storeItemSectionviewPager.setCurrentlyDisplayedFragment(fragmentIndex);

                // todo update top UI to currently selected itemSection
                storeItemUnlocker.updateRandomUnlockView();
                storeItemUnlocker.updatePurchaseUnlockView();
                storeItemUnlocker.updateUnlockedFraction();
                sectionTitle.setSectionText();
            }
        });

        storePoints.setStorePointsListener(new StorePoints.StorePointsListener() {
            @Override
            public void pointsUpdated() {
                storeItemUnlocker.updateUnlockedFraction();
                storeItemUnlocker.updateRandomUnlockView();
            }
        });
    }

    public void onShow() {
        ItemSectionFragment currentlyDisplayedFragment = storeItemSectionviewPager.getCurrentlyDisplayedFragment();
        currentlyDisplayedFragment.setEqiupedItemToSelectedItem();
        currentlyDisplayedFragment.updateGridView();
        currentlyDisplayedFragment.notifyNewItemToDisplayFromThisSectionBecauseSectionChange();

    }

    @Override
    public void selectedItemChanged(String section, Drawable itemImage, String itemTitle, int unlocked) {
        displayedItem.updateDisplayedItem(section, itemImage, itemTitle, unlocked);

    }

    @Override
    public void itemUnlockComplete(final String typeOfUnlock) {
        switch (typeOfUnlock) {
            case "random":
               storePoints.addToPointsAndUpdateView(-getCurrentRandomUnlockPrice());
               break;
            case "purchase":
                storePoints.addToPointsAndUpdateView(-getCurrentPurchaseUnlockPrice());
                break;
            default: throw new RuntimeException("type of unlock not found");
        }
    }

    public int currentSelectedItemUnlocked() {
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



    /*
    @Override
    public void setViewPage(int fragmentIndex) {
        // handles displayed item inside
        storeItemSectionviewPager.setCurrentlyDisplayedFragment(fragmentIndex);

        // todo update top UI to currently selected itemSection
        storeItemUnlocker.updateRandomUnlockView();
        storeItemUnlocker.updatePurchaseUnlockView();
        storeItemUnlocker.updateUnlockedFraction();
        sectionTitle.setSectionText(getCurrentlyDisplayedItemFragmentTAG());
    }
    */

    /*
    @Override
    public void selectedItemChanged(String section, Drawable itemImage, String itemTitle, int unlocked) {
        displayedItem.updateDisplayedItem(section, itemImage, itemTitle, unlocked);
    }

    @Override
    public void itemUnlockComplete() {
        storePoints.updateCurrentPoints();
        storeItemUnlocker.updateUnlockedFraction();
        storeItemUnlocker.updateRandomUnlockView();
    }
    */

    /*
    @Override
    public void randomUnlockClick() {
        // tell itemsectionfrag
        storeItemSectionviewPager.randomUnlockForCurrentItemSection();

    }

    @Override
    public void purchaseUnlockClick() {
        storeItemSectionviewPager.purchaseUnlockForCurrentItemSection();

    }
    */

    /*
    @Override
    public void videoAdCompleted(int earnedPoints) {
        storePoints.addToPointsAndUpdateView(earnedPoints);

    }
    */
}
