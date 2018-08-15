package com.thezs.fabianzachs.tapattack.MainMenu.Store;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.thezs.fabianzachs.tapattack.ButtonOnTouchListener;
import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Database.MyDBHandler;
import com.thezs.fabianzachs.tapattack.MyRewardVideoAd;
import com.thezs.fabianzachs.tapattack.R;

import static android.content.Context.MODE_PRIVATE;

public class StoreFragment extends Fragment implements /*GamemodeSectionFragment.GameModeSectionFragmentListener*/ItemSectionListener {

    private CustomViewPager viewPager;
    private SectionsPageAdapter fragmentAdapter;
    private StoreListener storeListener;
    private ImageView displayedItemImage;
    private ImageView lockImage;
    private TextView displayedItemTitle;
    private TextView displayedSectionTitle;
    private TextView currentPointsText;
    public SharedPreferences prefs;
    private MyDBHandler dbHandler;
    private View view; // todo refactor to use the global variable since its better than needing to keep all above references
    private int currentlyDisplayedFragmentIndex;


    @Override
    public void onAttach(Context context) {
        Log.d("lifecycle", "onAttach: ");
        super.onAttach(context);
        if (context instanceof StoreListener) {
            storeListener = (StoreListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement MyListFragment.OnItemSelectedListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d("lifecycle", "onCreate: ");
        super.onCreate(savedInstanceState);
        prefs = getActivity().getSharedPreferences("playerInfo", MODE_PRIVATE);
        dbHandler = new MyDBHandler(getActivity(), null, null, 1);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d("lifecycle", "onActivityCreated: ");
        super.onActivityCreated(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d("lifecycle", "onCreateView: ");
        this.view = inflater.inflate(R.layout.store_fragment2, container, false);
        displayedItemTitle = (TextView) view.findViewById(R.id.item_title_text);
        displayedSectionTitle = (TextView) view.findViewById(R.id.section_title_text);
        displayedItemImage = (ImageView) view.findViewById(R.id.selected_item);
        lockImage = (ImageView) view.findViewById(R.id.lock);
        currentPointsText = (TextView) view.findViewById(R.id.current_points_text);
        setupVideoAdClick(view);
        setupBottomNavigation(view);
        setupItemsSection(view);
        setupRandomUnlockSection();
        updateCurrentPointsText(); // todo add this to listener when item is unlocked and will this update after a game going back into store... listener to when store is shown

        // todo video for points section click


        final ImageView backButton= (ImageView) view.findViewById(R.id.store_back_image);
        backButton.setOnTouchListener(new ButtonOnTouchListener(getActivity(), backButton, new ButtonOnTouchListener.ButtonExecuteListener() {
            @Override
            public void buttonAction() {
               storeListener.storeFragmentToMenuFragment();
            }
        }));


        //final Button randomUnlock = (Button) view.findViewById(R.id.random_unlock_text);
        //randomUnlock.setOnTouchListener(new ButtonOnTouchListener(getActivity(), randomUnlock, "randomUnlock"));

        /*// todo animations
        ImageView highlight = (ImageView) view.findViewById(R.id.item_highlight);
        Animation myAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.pulse);
        highlight.startAnimation(myAnim);
        */

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

        ImageView selectedItem = (ImageView) view.findViewById(R.id.selected_item);
        ObjectAnimator scaleDownItem = ObjectAnimator.ofPropertyValuesHolder(
                selectedItem,
                PropertyValuesHolder.ofFloat("scaleX", 1.1f),
                PropertyValuesHolder.ofFloat("scaleY", 1.1f));
        scaleDownItem.setDuration(510);
        //scaleDown.setInterpolator(new FastOutSlowInInterpolator());

        scaleDownItem.setRepeatCount(ObjectAnimator.INFINITE);
        scaleDownItem.setRepeatMode(ObjectAnimator.REVERSE);
        scaleDownItem.start();



        currentlyDisplayedFragmentIndex = 0;
        setupTopUI(0);
        return view;
    }

    private void setupVideoAdClick(View view) {
        final MyRewardVideoAd rewardVideoAd = new MyRewardVideoAd(getContext(), prefs, (ImageView) view.findViewById(R.id.reward_video_image));
        LinearLayout rewardVideoAdSection = (LinearLayout) view.findViewById(R.id.reward_video_ad_section);
        rewardVideoAdSection.setOnTouchListener(new ButtonOnTouchListener(getActivity(), rewardVideoAdSection, new ButtonOnTouchListener.ButtonExecuteListener() {
            @Override
            public void buttonAction() {
                rewardVideoAd.playVideoAd();

            }
        }));

    }


    @Override
    public void selectedItemChanged(String section, Drawable itemImage, String itemTitle, int unlocked) {
        displayedItemImage.setImageDrawable(itemImage);
        displayedItemTitle.setText(itemTitle.toUpperCase());
        if (unlocked == 1) {
            SharedPreferences.Editor prefsEditor = prefs.edit();
            prefsEditor.putString(section, itemTitle);
            //Log.d("isthisright", "selectedItemChanged: " + fragmentAdapter.getPageTitle(currentlyDisplayedFragmentIndex));
            prefsEditor.apply();
            Log.d("isthisright", "selectedItemChanged: " +section);
            lockImage.setImageResource(android.R.color.transparent);
        }
            // todo then make it the equiped
        else
            lockImage.setImageResource(R.drawable.lockeditem);

    }

    private void updateCurrentPointsText() {
        currentPointsText.setText(Integer.toString(prefs.getInt("points", 0)));
    }

    public void setupLockedFraction(String category) {
        TextView fraction = (TextView) view.findViewById(R.id.unlocked_fraction);
        int numberUnlocked = dbHandler.getNumberOfItemsFromCategory(category) - dbHandler.getNumberOfLockedItems(category);
        fraction.setText( numberUnlocked + "/" + dbHandler.getNumberOfItemsFromCategory(category));
    }

    public void onShow() {
        Log.d("lifecycle", "onShow: ");
        ItemSectionFragment currentlyDisplayedFragment = (ItemSectionFragment)  fragmentAdapter.getItem(currentlyDisplayedFragmentIndex);

        currentlyDisplayedFragment.setSelectionToEquiped();
        currentlyDisplayedFragment.updateGridView();
        currentlyDisplayedFragment.notifyNewItemToDisplayFromThisSectionBecauseSectionChange();

    }


    public interface StoreListener {
        void storeFragmentToMenuFragment();
        //void setupStoreVideoRewardAd();
        //void videoAdRequested();
    }

    public void setViewPager(int fragmentNumber) {
        viewPager.setCurrentItem(fragmentNumber);
    }



    private void setupTopUI(int fragmentPagePosition) {
        switch (fragmentPagePosition)  {
            case 0:
                if (!fragmentAdapter.getPageTitle(fragmentPagePosition).equals(Constants.GAME_MODE_TAG))
                    throw new IllegalArgumentException("Fragment title not matching current fragment");
                setTopGamemodeUI();
                break;
            case 1:
                if (!fragmentAdapter.getPageTitle(fragmentPagePosition).equals(Constants.SHAPE_TYPE_TAG))
                    throw new IllegalArgumentException("Fragment title not matching current fragment");
                setTopShapetypeUI();
                break;
            case 2:
                if (!fragmentAdapter.getPageTitle(fragmentPagePosition).equals(Constants.SHAPE_THEME_TAG))
                    throw new IllegalArgumentException("Fragment title not matching current fragment");
                setTopShapethemeUI();
                break;
            case 3:
                if (!fragmentAdapter.getPageTitle(fragmentPagePosition).equals(Constants.BACKGROUND_TAG))
                    throw new IllegalArgumentException("Fragment title not matching current fragment");
                setTopBackgroundUI();
                break;
        }

    }

    private void setTopBackgroundUI() {
        displayedSectionTitle.setText("BACKGROUNDS");
        setupLockedFraction(Constants.BACKGROUND_TAG);
        updateUnlockSectionText(Constants.PRICE_BY_SECTION[3]);
    }


    private void setTopShapetypeUI() {
        displayedSectionTitle.setText("SHAPE TYPES");
        setupLockedFraction(Constants.SHAPE_TYPE_TAG);
        updateUnlockSectionText(Constants.SHAPE_TYPES_POINTS_COST);
        updateUnlockSectionText(Constants.PRICE_BY_SECTION[1]);
    }

    private void setTopShapethemeUI() {
        displayedSectionTitle.setText("SHAPE THEME");
        setupLockedFraction(Constants.SHAPE_THEME_TAG);
        updateUnlockSectionText(Constants.SHAPE_THEME_POINTS_COST);
        updateUnlockSectionText(Constants.PRICE_BY_SECTION[2]);
    }

    private void setTopGamemodeUI() {
        displayedSectionTitle.setText("GAME MODE");
        setupLockedFraction(Constants.GAME_MODE_TAG);
        updateUnlockSectionText(Constants.PRICE_BY_SECTION[0]);
        // todo buy button text
        // todo itemhighlight
        // todo selected item
        // todo section title
        // todo item name title
    }

    private void updateUnlockSectionText(int price) {
        TextView randomUnlockText = (TextView) view.findViewById(R.id.random_unlock_text);
        ImageView randomUnlockStar = (ImageView) view.findViewById(R.id.random_unlock_star);
        if (price == 0) {
            randomUnlockText.setVisibility(View.INVISIBLE);
            randomUnlockStar.setVisibility(View.INVISIBLE); // todo it will still be clickable even tho not visible handle that

        }
        else {
            randomUnlockText.setVisibility(View.VISIBLE);
            randomUnlockStar.setVisibility(View.VISIBLE);
        }
        randomUnlockText.setText("RANDOM UNLOCK\n " + price);
    }



    private void setupRandomUnlockSection() {
        RelativeLayout unlockSction = (RelativeLayout) view.findViewById(R.id.random_unlock_section);
        unlockSction.setOnTouchListener(new ButtonOnTouchListener(getActivity(), unlockSction, new ButtonOnTouchListener.ButtonExecuteListener() {
            @Override
            public void buttonAction() {
                //randomUnlockClick();
                //viewPager.getCurrentItem().;
                ItemSectionFragment currentFragment = (ItemSectionFragment) fragmentAdapter.getItem(currentlyDisplayedFragmentIndex);
                currentFragment.randomUnlock();
            }
        }));
    }


    private void setupItemsSection(View view) {
        viewPager = (CustomViewPager) view.findViewById(R.id.store_container);
        viewPager.setOffscreenPageLimit(3);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ItemSectionFragment currentlyDisplayedFragment = (ItemSectionFragment)  fragmentAdapter.getItem(position);

                currentlyDisplayedFragment.setSelectionToEquiped();
                currentlyDisplayedFragment.updateGridView();


                // todo reset grid to select currently equiped item
                currentlyDisplayedFragment.notifyNewItemToDisplayFromThisSectionBecauseSectionChange();
                currentlyDisplayedFragmentIndex = position;
                Log.d("isthisright", "onPageSelected: " + currentlyDisplayedFragment);
                //((ItemSectionFragment)fragmentAdapter.getItem(position)).notifyNewItemToDisplayFromThisSectionBecauseSectionChange(fragmentAdapter.getPageTitle(position), fragmentAdapter.getItem(position));
                //Log.d("debugtime", "onPageSelected: page: " + position);
                //fragmentAdapter.getRegeisteredFragment
                setupTopUI(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        fragmentAdapter = new SectionsPageAdapter(getChildFragmentManager());
        fragmentAdapter.addFragment(new GamemodeSectionFragment(), Constants.GAME_MODE_TAG);
        fragmentAdapter.addFragment(new ShapeTypeSectionFragment(), Constants.SHAPE_TYPE_TAG);
        fragmentAdapter.addFragment(new ShapeThemeSectionFragment(), Constants.SHAPE_THEME_TAG);
        fragmentAdapter.addFragment(new BackgroundSectionFragment(), Constants.BACKGROUND_TAG);
        // to set the initially shown item connected to the first one shown in the viewpager (index 0 shown first)
        //((ItemSectionFragment)fragmentAdapter.getItem(0)).notifyNewItemToDisplayFromThisSectionBecauseSectionChange(fragmentAdapter.getPageTitle(0), ((ItemSectionFragment) fragmentAdapter.getItem(0)).getDEFAULT_SECTION_VALUE());
        viewPager.setAdapter(fragmentAdapter);
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
                                //setCurrentItemFragmentIndex(0);
                                //viewPager.getCurrentItem();
                                //Log.d("currentitem", "onNavigationItemSelected: " +viewPager.getCurrentItem());
                                return true;
                            case R.id.ic_shape_type:
                                setViewPager(1);
                                //setCurrentItemFragmentIndex(1);
                                //Log.d("currentitem", "onNavigationItemSelected: " +viewPager.getCurrentItem());
                                return true;
                            case R.id.ic_theme:
                                setViewPager(2);
                                //setCurrentItemFragmentIndex(2);
                                //Log.d("currentitem", "onNavigationItemSelected: " +viewPager.getCurrentItem());
                                return true;
                            case R.id.ic_background:
                                setViewPager(3);
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
