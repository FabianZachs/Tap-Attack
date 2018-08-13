package com.thezs.fabianzachs.tapattack.MainMenu.Store;

import android.content.Context;
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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.thezs.fabianzachs.tapattack.ButtonOnTouchListener;
import com.thezs.fabianzachs.tapattack.R;

public class StoreFragment extends Fragment implements /*GamemodeSectionFragment.GameModeSectionFragmentListener*/ItemSectionListener {

    private CustomViewPager viewPager;
    private SectionsPageAdapter  adapter;
    private StoreListener storeListener;
    private ImageView displayedItemImage;
    private TextView displayedItemTitle;
    private TextView displayedSectionTitle;


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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.store_fragment2, container, false);
        setupBottomNavigation(view);
        setupItemsSection(view);
        setupRandomUnlockSection(view);

        displayedItemTitle = (TextView) view.findViewById(R.id.item_title_text);
        displayedSectionTitle = (TextView) view.findViewById(R.id.section_title_text);
        displayedItemImage = (ImageView) view.findViewById(R.id.selected_item);

        Log.d("currentitem", "onNavigationItemSelected:create " +viewPager.getCurrentItem());

        final ImageView backButton= (ImageView) view.findViewById(R.id.store_back_image);
        backButton.setOnTouchListener(new ButtonOnTouchListener(getActivity(), backButton, new ButtonOnTouchListener.ButtonExecuteListener() {
            @Override
            public void buttonAction() {
               storeListener.storeFragmentToMenuFragment();
            }
        }));


        //final Button randomUnlock = (Button) view.findViewById(R.id.random_unlock_text);
        //randomUnlock.setOnTouchListener(new ButtonOnTouchListener(getActivity(), randomUnlock, "randomUnlock"));

        /* todo animations
        ImageView highlight = (ImageView) view.findViewById(R.id.item_highlight);
        Animation myAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.pulse);
        highlight.startAnimation(myAnim);
        */
        /*
        ImageView iv = (ImageView) view.findViewById(R.id.item_highlight);

        ObjectAnimator scaleDown = ObjectAnimator.ofPropertyValuesHolder(
                iv,
                PropertyValuesHolder.ofFloat("scaleX", 1.2f),
                PropertyValuesHolder.ofFloat("scaleY", 1.2f));
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
                PropertyValuesHolder.ofFloat("scaleX", .9f),
                PropertyValuesHolder.ofFloat("scaleY", .9f));
        scaleDownItem.setDuration(410);
        //scaleDown.setInterpolator(new FastOutSlowInInterpolator());

        scaleDownItem.setRepeatCount(ObjectAnimator.INFINITE);
        scaleDownItem.setRepeatMode(ObjectAnimator.REVERSE);
        scaleDownItem.start();
        */

        setupTopUI(0);
        return view;
    }

    @Override
    public void selectedItemChanged(Drawable itemImage, String itemTitle, int unlocked) {
        displayedItemImage.setImageDrawable(itemImage);
        displayedItemTitle.setText(itemTitle.toUpperCase());
        // todo show lock if locked
    }


    public interface StoreListener {
        void storeFragmentToMenuFragment();
    }

    public void setViewPager(int fragmentNumber) {
        viewPager.setCurrentItem(fragmentNumber);
    }



    private void setupTopUI(int fragmentPagePosition) {
        switch (fragmentPagePosition)  {
            case 0:
                if (adapter.getPageTitle(fragmentPagePosition) != "gamemode")
                    throw new IllegalArgumentException("Fragment title not matching current fragment");
                setTopGamemodeUI();
                break;
            case 1:
                if (adapter.getPageTitle(fragmentPagePosition) != "shapetype")
                    throw new IllegalArgumentException("Fragment title not matching current fragment");
                setTopShapethemeUI();
                break;
            case 2:
                if (adapter.getPageTitle(fragmentPagePosition) != "shapetheme")
                    throw new IllegalArgumentException("Fragment title not matching current fragment");
                setTopShapetypeUI();
                break;
            case 3:
                if (adapter.getPageTitle(fragmentPagePosition) != "background")
                    throw new IllegalArgumentException("Fragment title not matching current fragment");
                setTopBackgroundUI();
                break;
        }

    }

    private void setTopBackgroundUI() {
        displayedSectionTitle.setText("BACKGROUNDS");
    }

    private void setTopShapetypeUI() {
        displayedSectionTitle.setText("SHAPE TYPES");
    }

    private void setTopShapethemeUI() {
        displayedSectionTitle.setText("SHAPE THEME");
    }

    private void setTopGamemodeUI() {
        displayedSectionTitle.setText("GAME MODE");
        // todo buy button text
        // todo itemhighlight
        // todo selected item
        // todo section title
        // todo item name title
    }



    private void setupRandomUnlockSection(View view) {
        RelativeLayout unlockSction = (RelativeLayout) view.findViewById(R.id.random_unlock_section);
        unlockSction.setOnTouchListener(new ButtonOnTouchListener(getActivity(), unlockSction, new ButtonOnTouchListener.ButtonExecuteListener() {
            @Override
            public void buttonAction() {
                //randomUnlockClick();
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
                // todo setupUI elements according to which fragment
                ItemSectionFragment currentlyDisplayedFragment = (ItemSectionFragment)  adapter.getItem(position);
                currentlyDisplayedFragment.notifyNewItemToDisplayFromThisSection();
                //((ItemSectionFragment)adapter.getItem(position)).notifyNewItemToDisplayFromThisSection(adapter.getPageTitle(position), adapter.getItem(position));
                //Log.d("debugtime", "onPageSelected: page: " + position);
                //adapter.getRegeisteredFragment
                setupTopUI(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new SectionsPageAdapter(getChildFragmentManager());
        adapter.addFragment(new GamemodeSectionFragment(), "gamemode");
        adapter.addFragment(new ShapeTypeSectionFragment(), "shapetype");
        adapter.addFragment(new ShapeThemeSectionFragment(), "shapetheme");
        adapter.addFragment(new BackgroundSectionFragment(), "background");
        // to set the initially shown item connected to the first one shown in the viewpager (index 0 shown first)
        //((ItemSectionFragment)adapter.getItem(0)).notifyNewItemToDisplayFromThisSection(adapter.getPageTitle(0), ((ItemSectionFragment) adapter.getItem(0)).getDEFAULT_SECTION_VALUE());
        viewPager.setAdapter(adapter);
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
