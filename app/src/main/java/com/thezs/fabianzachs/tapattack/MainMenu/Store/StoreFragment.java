package com.thezs.fabianzachs.tapattack.MainMenu.Store;

import android.annotation.SuppressLint;
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
import android.widget.Button;
import android.widget.ImageView;

import com.thezs.fabianzachs.tapattack.ButtonOnTouchListener;
import com.thezs.fabianzachs.tapattack.R;

public class StoreFragment extends Fragment implements GamemodeSectionFragment.GameModeSectionFragmentListener {

    private CustomViewPager viewPager;
    private SectionsPageAdapter  adapter;
    private StoreListener storeListener;
    private int currentItemFragmentIndex = 0;
    private ImageView displayedItemImage;


    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.store_fragment, container, false);
        Log.d("storecycle", "onCreateView: ");
        setupBottomNavigation(view);
        setupItemsSection(view);
        displayedItemImage = (ImageView) view.findViewById(R.id.selected_item);

        Log.d("currentitem", "onNavigationItemSelected:create " +viewPager.getCurrentItem());

        final ImageView backButton= (ImageView) view.findViewById(R.id.store_back_image);
        backButton.setOnTouchListener(new ButtonOnTouchListener(getActivity(), backButton, new ButtonOnTouchListener.ButtonExecuteListener() {
            @Override
            public void buttonAction() {
               storeListener.storeFragmentToMenuFragment();
            }
        }));


        final Button randomUnlock = (Button) view.findViewById(R.id.random_unlock_text);
        //randomUnlock.setOnTouchListener(new ButtonOnTouchListener(getActivity(), randomUnlock, "randomUnlock"));

        setupTopUI(0);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("storecycle", "onCreate: ");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("storecycle", "onActivitycreated: ");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("storecycle", "onAttach: ");
        if (context instanceof StoreListener) {
            storeListener = (StoreListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement MyListFragment.OnItemSelectedListener");
        }
    }

    public void setupTopUI(int position) {
        switch (position)  {
            case 0:
                if (adapter.getPageTitle(position) != "gamemode")
                    throw new IllegalArgumentException("Fragment title not matching current fragment");
                setTopGamemodeUI();
                break;
            case 1:
                if (adapter.getPageTitle(position) != "shapetype")
                    throw new IllegalArgumentException("Fragment title not matching current fragment");
                break;
            case 2:
                if (adapter.getPageTitle(position) != "shapetheme")
                    throw new IllegalArgumentException("Fragment title not matching current fragment");
                break;
            case 3:
                if (adapter.getPageTitle(position) != "background")
                    throw new IllegalArgumentException("Fragment title not matching current fragment");
                break;
        }

    }

    private void setTopGamemodeUI() {
        // todo buy button text
        // todo itemhighlight
        // todo selected item
        // todo section title
        // todo item name title
    }

    @Override
    public void selectedItemChanged(Drawable itemImage) {
        displayedItemImage.setImageDrawable(itemImage);

    }


    public interface StoreListener {
        void storeFragmentToMenuFragment();
    }


    public void setViewPager(int fragmentNumber) {
        viewPager.setCurrentItem(fragmentNumber);
    }


    private void setupItemsSection(View view) {
        viewPager = (CustomViewPager) view.findViewById(R.id.store_container);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // todo setupUI elements according to which fragment
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
