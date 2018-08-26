package com.thezs.fabianzachs.tapattack.MainMenu.Menu;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.thezs.fabianzachs.tapattack.ButtonOnTouchListener;
import com.thezs.fabianzachs.tapattack.Database.MyDBHandler;
import com.thezs.fabianzachs.tapattack.R;

import java.awt.font.TextAttribute;

/**
 * Created by fabianzachs on 18/08/18.
 */

public class MainMenuFragment2 extends Fragment {

    private MainMenuListener mainMenuListener;


    public interface MainMenuListener{
        void mainMenuFragmentToSettingsFragment();
        void mainMenuFragmentToStoreFragment();
        void mainMenuFragmentToMorePointsFragment();
        void playGameClick();
    }

    private View view;
    private SharedPreferences prefs;
    private MyDBHandler myDBHandler;
    private PointsAndShieldSection pointsSection;
    private PercentUnlockedSection percentUnlockedSection;
    private PlayGameSection playGameSection;
    private EquipedShieldSection equipedShieldSection;
    private TimedPresent timedPresent;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.prefs = getActivity().getSharedPreferences("playerInfo", Context.MODE_PRIVATE);
        this.myDBHandler = new MyDBHandler(getActivity(), null, null, 1);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.main_menu_fragment2, container, false);
        this.pointsSection = new PointsAndShieldSection(getActivity(), mainMenuListener, view, prefs);
        this.percentUnlockedSection = new PercentUnlockedSection(getActivity(), myDBHandler, view);
        this.playGameSection = new PlayGameSection(getActivity(),view, prefs, myDBHandler);
        this.equipedShieldSection = new EquipedShieldSection(getActivity(), view, prefs);
        this.timedPresent = new TimedPresent();

        setupFragmentChangingButtons();

        setListeners();


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onShow();
    }

    public void onShow() {
        pointsSection.updateDisplayedPointsAndShields();
        pointsSection.startAnimatingMorePointsImg();
        percentUnlockedSection.updatePercentUnlockedText();
        playGameSection.updatePlayGameSection();
        playGameSection.startAnimation();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainMenuListener) {
            mainMenuListener = (MainMenuListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement MyListFragment.OnItemSelectedListener");
        }
    }

    private void setListeners() {
        playGameSection.setListener(new PlayGameSection.PlayGameListener() {
            @Override
            public void playButtonPress() {
                mainMenuListener.playGameClick();
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupFragmentChangingButtons() {
        TextView storeText = view.findViewById(R.id.store_text);
        storeText.setOnTouchListener(new ButtonOnTouchListener(getActivity(), storeText, new ButtonOnTouchListener.ButtonExecuteListener() {
            @Override
            public void buttonAction() {
                mainMenuListener.mainMenuFragmentToStoreFragment();
            }
        }));

        TextView settingsText = view.findViewById(R.id.settings_text);
        settingsText.setOnTouchListener(new ButtonOnTouchListener(getActivity(), settingsText, new ButtonOnTouchListener.ButtonExecuteListener() {
            @Override
            public void buttonAction() {
                mainMenuListener.mainMenuFragmentToSettingsFragment();
            }
        }));
    }


}
