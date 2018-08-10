package com.thezs.fabianzachs.tapattack.MainMenu.Store;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.ads.AdView;
import com.thezs.fabianzachs.tapattack.MainMenu.CustomViewPager;
import com.thezs.fabianzachs.tapattack.MainMenu.MainMenuActivity;
import com.thezs.fabianzachs.tapattack.MainMenu.SectionsPageAdapter;
import com.thezs.fabianzachs.tapattack.MainMenu.Store.BottomNavigationViewHelper;
import com.thezs.fabianzachs.tapattack.R;
import com.thezs.fabianzachs.tapattack.MainMenu.Store.BackgroundSectionFragment;
import com.thezs.fabianzachs.tapattack.MainMenu.Store.GamemodeSectionFragment;
import com.thezs.fabianzachs.tapattack.MainMenu.Store.ShapeThemeSectionFragment;
import com.thezs.fabianzachs.tapattack.MainMenu.Store.ShapeTypeSectionFragment;
import com.thezs.fabianzachs.tapattack.helper;

public class StoreFragment extends Fragment {

    private AdView mAdView;
    private SectionsPageAdapter sectionsPageAdapter;
    private CustomViewPager viewPager;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.store_fragment, container, false);
        setupBottomNavigation(view);



        final ImageView backView = (ImageView) view.findViewById(R.id.store_back_image);

        backView.setOnTouchListener(new View.OnTouchListener() {
            boolean lostFocus = false;
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.d("eventdebug", "onTouch: " + motionEvent.toString());
                Log.d("eventdebug3", "onTouch: " + lostFocus);
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    Log.d("eventdebug3", "onTouch: C");
                    if (!lostFocus) {
                        final Animation myAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.scale_up);
                        backView.startAnimation(myAnim);
                        myAnim.setFillAfter(true);
                        ((MainMenuActivity)getActivity()).setViewPager(1);
                    }
                    lostFocus = false;
                    return true;
                }
                if (lostFocus && (motionEvent.getX() > backView.getLeft() || backView.getRight() > motionEvent.getX() || motionEvent.getY() > backView.getTop() || backView.getBottom() > motionEvent.getY())) {
                    lostFocus = false;
                    final Animation myAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.scale_down);
                    backView.startAnimation(myAnim);
                    myAnim.setFillAfter(true);
                    return true;
                }
                if (lostFocus) {
                    Log.d("eventdebug1", "onTouch: D");
                    return true;
                }

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN ) {
                    final Animation myAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.scale_down);
                    backView.startAnimation(myAnim);
                    myAnim.setFillAfter(true);
                    Log.d("eventdebug3", "onTouch: A");

                    return true;
                }
                if (motionEvent.getX() < backView.getLeft() || backView.getRight() <motionEvent.getX() || motionEvent.getY() < backView.getTop() || backView.getBottom() < motionEvent.getY()) {
                    lostFocus = true;
                    final Animation myAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.scale_up);
                    backView.startAnimation(myAnim);
                    myAnim.setFillAfter(true);
                    Log.d("eventdebug3", "onTouch: B");
                    Log.d("eventdebug3", "onTouch: B : " +motionEvent.getX());
                    return true;
                }

                return false;
            }
        });


        /*backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainMenuActivity)getActivity()).setViewPager(1);
            }
        });
        */
        //helper.setupButtonAnimation(getActivity(), view, backView);






        setupItemsSection(view);


        final Button randomUnlock = (Button) view.findViewById(R.id.random_unlock_text);
        randomUnlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //final Animation myAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.scale_down);
                //randomUnlock.startAnimation(myAnim);
            }
        });
        helper.setupButtonAnimation(getActivity(), view, randomUnlock);

        /*
        randomUnlock.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.d("motionaction", "onTouch: "+motionEvent.toString());
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN ) {
                    final Animation myAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.scale_down);
                    randomUnlock.startAnimation(myAnim);
                    myAnim.setFillAfter(true);

                    return true;
                }
                else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    final Animation myAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.scale_up);
                    randomUnlock.startAnimation(myAnim);
                    myAnim.setFillAfter(true);
                }
                return false;
            }
        });
        */



        return view;
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
